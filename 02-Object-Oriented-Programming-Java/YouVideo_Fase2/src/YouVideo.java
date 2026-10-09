import Content.ContentGetters;
import Content.Podcast;
import Content.PodcastGetters;
import Content.Show;
import Author.*;
import Exceptions.*;
import Subtitles.Subtitle;
import Videos.*;

import java.time.LocalDate;
import java.util.Iterator;

/**
 * This is the core interface of the YouVideo system class.
 * It provides centralized access to all operations regarding videos, podcasts, shows, authors, and tags.
 */

public interface YouVideo {

    /**
     * Creates a publishable video with a unique id.
     *
     * @param id            The id of the video.
     * @param duration      The time of the video in minutes.
     * @param url           The url where the video is stored.
     * @param publisherName The name of the creator/publisher of the video.
     * @param title         The title of the video.
     * @param code          The language ISO code of the video.
     * @throws InvalidCodeException        if the language code is not a valid ISO code.
     * @throws InvalidDurationException    if the duration is <= 0.
     * @throws VideoAlreadyExistsException if a video with this ID already exists.
     */
    void createPublishableVideo(String id, int duration, String url, String publisherName, String title, String code)
            throws InvalidCodeException, InvalidDurationException, VideoAlreadyExistsException;

    /**
     * Creates a premium publishable video with a unique id and subtitles.
     *
     * @param id                   The id of the video.
     * @param duration             The time of the video in minutes.
     * @param url                  The url where the video is stored.
     * @param publisherName        The name of the creator/publisher of the video.
     * @param title                The title of the video.
     * @param code                 The language ISO code of the video.
     * @param subtitleUrl          The url where the subtitles are stored.
     * @param subtitleLanguageCode The language ISO code of the subtitles.
     * @throws InvalidCodeException        if any of the language codes is not a valid ISO code.
     * @throws InvalidDurationException    if the duration is <= 0.
     * @throws VideoAlreadyExistsException if a video with this ID already exists.
     */
    void createPublishablePremiumVideo(String id, int duration, String url, String publisherName, String title,
                                       String code, String subtitleUrl, String subtitleLanguageCode)
            throws InvalidCodeException, InvalidDurationException, VideoAlreadyExistsException,
            InvalidSubtitleLanguageException;

    /**
     * Checks if a video with the specified ID already exists in the system.
     *
     * @param id The id of the video to check.
     * @return true if the video exists, false otherwise.
     */
    boolean isExistingVideo(String id);

    /**
     * Adds a new subtitle to an existing Premium Video.
     *
     * @param videoId          The id of the premium video.
     * @param subtitleUrl      The url where the subtitle file is stored.
     * @param subtitleLanguage The language ISO code for the subtitle.
     * @throws VideoDoesNotExistException if the video doesn't exist.
     * @throws VideoIsNotPremiumException if the video exists but is not a premium video.
     */
    void addSubtitle(String videoId, String subtitleUrl, String subtitleLanguage)
            throws VideoDoesNotExistException, VideoIsNotPremiumException, InvalidCodeException;

    /**
     * Retrieves a video by its unique identifier.
     *
     * @param videoId The id of the video.
     * @return The video object corresponding to the ID.
     * @throws VideoDoesNotExistException if the video doesn't exist.
     */
    Video getVideo(String videoId) throws VideoDoesNotExistException;

    /**
     * Returns an iterator over the subtitles of a specified Premium Video.
     *
     * @param video The premium video object.
     * @return An iterator of Subtitle objects.
     * @throws VideoIsNotPremiumException if the video is not an instance of PremiumVideo.
     */
    Iterator<Subtitle> subtitleIterator(Video video) throws VideoIsNotPremiumException;

    /**
     * Creates a new podcast without any episodes.
     *
     * @param title  The title of the podcast.
     * @param author The name of the podcast's author.
     * @param code   The language ISO code of the podcast.
     * @throws PodcastAlreadyExistsException if a podcast with this title already exists.
     * @throws PodcastAlreadyExistsException if the podcast already exists
     */
    void createPodcast(String title, String author, String code)
            throws AuthorNotExistsException, PodcastAlreadyExistsException;

    /**
     * Adds a new episode to an existing podcast.
     *
     * @param title    The title of the podcast.
     * @param id       The unique id of the episode.
     * @param duration The duration of the episode in minutes.
     * @param url      The url where the episode is stored.
     * @param date     The release date of the episode.
     * @throws EpisodeAlreadyExistsException if an episode with this ID already exists.
     * @throws PodcastDoesNotExistException  if the podcast doesn't exist.
     * @throws DateIsEarlierException        if the episode date is earlier than the latest episode's date.
     */
    void addEpisode(String title, String id, int duration, String url, LocalDate date)
            throws EpisodeAlreadyExistsException, PodcastDoesNotExistException, DateIsEarlierException,
            InvalidDurationException;

    /**
     * Retrieves a podcast by its title.
     *
     * @param title The title of the podcast.
     * @return The Podcast object.
     * @throws PodcastDoesNotExistException if the podcast doesn't exist.
     */
    Podcast getPodcast(String title) throws PodcastDoesNotExistException;

    /**
     * Retrieves an author by their name.
     *
     * @param name The name of the author.
     * @return The Author object.
     * @throws AuthorNotExistsException if the author is not registered in the system.
     */
    Author getAuthor(String name) throws AuthorNotExistsException;

    /**
     * Returns an iterator over all podcasts created by a specific author.
     *
     * @param authorName The name of the author.
     * @return An iterator of PodcastGetters (Read-Only podcast views).
     * @throws AuthorHasNoPodcastsException if the author has not published any podcasts.
     */
    Iterator<PodcastGetters> listAuthorPodcasts(String authorName)
            throws AuthorHasNoPodcastsException, AuthorNotExistsException;

    /**
     * Removes a podcast and all its episodes from the system.
     *
     * @param title The title of the podcast to be removed.
     * @throws PodcastDoesNotExistException if the podcast doesn't exist.
     */
    void removePodcast(String title) throws PodcastDoesNotExistException, AuthorNotExistsException;

    /**
     * Creates a new Show referencing an existing publishable video.
     *
     * @param authorName       The name of the show's author.
     * @param videoId          The ID of the referenced video.
     * @param transmissionDate The broadcast date of the show.
     * @throws VideoDoesNotExistException if the referenced video does not exist.
     * @throws ShowAlreadyExistsException if a show referencing this video already exists.
     */
    void createShow(String authorName, String videoId, LocalDate transmissionDate)
            throws VideoDoesNotExistException, ShowAlreadyExistsException,
            AuthorNotExistsException;

    /**
     * Retrieves a Show by its title (which matches the title of its referenced video).
     *
     * @param showTitle The title of the show to retrieve.
     * @return The Show object.
     * @throws ShowDoesNotExistException if the show doesn't exist.
     */
    Show getShow(String showTitle) throws ShowDoesNotExistException;

    /**
     * Removes a show from the system without deleting the underlying referenced video.
     *
     * @param showTitle The title of the show to remove.
     * @throws ShowDoesNotExistException if the show doesn't exist.
     */
    void removeShow(String showTitle) throws ShowDoesNotExistException, AuthorNotExistsException;

    /**
     * Checks if a show with the specified title exists in the system.
     *
     * @param showTitle The title of the show.
     * @return true if the show exists, false otherwise.
     */
    boolean isExistingShow(String showTitle);

    /**
     * Removes a publishable video from the system.
     *
     * @param videoId The identifier of the video to remove.
     * @throws VideoIsEpisodeException    if the video is part of a podcast (an episode).
     * @throws VideoIsShowException       if the video is currently referenced by an active show.
     * @throws VideoDoesNotExistException if the video doesn't exist.
     */
    void removeVideo(String videoId)
            throws VideoIsEpisodeException, VideoIsShowException, VideoDoesNotExistException;

    /**
     * Gets all episodes from a specific podcast.
     *
     * @param title The title of the podcast.
     * @return An iterator of Episode objects.
     * @throws PodcastDoesNotExistException if the podcast doesn't exist.
     * @throws NoEpisodesException          if the podcast has no episodes.
     */
    Iterator<Episode> getPodcastEpisodes(String title) throws PodcastDoesNotExistException, NoEpisodesException;

    /**
     * Checks if there is at least one author in the system with productivity greater than zero.
     *
     * @return true if there are productive authors, false otherwise.
     */
    boolean hasProductiveAuthors();

    /**
     * Returns an iterator over all authors, sorted by their productivity (number of contents).
     *
     * @return An iterator of AuthorGetters (Read-Only author views).
     */
    Iterator<AuthorGetters> getAuthorsByProductivity();

    /**
     * Adds a categorization tag to an existing Show or Podcast.
     *
     * @param title   The title of the Show or Podcast.
     * @param tagName The text of the tag to be added.
     * @throws TitleDoesNotExistsException   if neither a Show nor a Podcast with this title exists.
     * @throws TitleIsAlreadyTaggedException if the content already possesses this tag.
     */
    void addTag(String title, String tagName) throws TitleDoesNotExistsException, TitleIsAlreadyTaggedException,
            PodcastDoesNotExistException, ShowDoesNotExistException;

    /**
     * Removes a categorization tag from an existing Show or Podcast.
     *
     * @param title   The title of the Show or Podcast.
     * @param tagName The text of the tag to be removed.
     * @throws TitleDoesNotExistsException if neither a Show nor a Podcast with this title exists.
     * @throws TitleIsNotTaggedException   if the content does not possess this tag.
     */
    void removeTag(String title, String tagName) throws TitleDoesNotExistsException, TitleIsNotTaggedException,
            PodcastDoesNotExistException, ShowDoesNotExistException;

    /**
     * Returns an iterator over content filtered by a specific tag and type, ordered as requested.
     *
     * @param tagName     The tag to filter by.
     * @param contentType The type of content to filter ("ALL", "PODCAST", or "SHOW").
     * @param order       The ordering of the results ("ASC" or "DES").
     * @return An ordered iterator of ContentGetters (Read-Only content views).
     * @throws TagDoesNotExistException   if the specified tag does not exist in the system.
     * @throws InvalidParametersException if the content type or order parameters are malformed.
     */
    Iterator<ContentGetters> contentIterator(String tagName, String contentType, String order)
            throws TagDoesNotExistException, InvalidParametersException;
}