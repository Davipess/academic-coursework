import Podcast.Podcast;
import Show.Show;
import Author.*;
import Subtitles.Subtitle;
import Videos.*;
import dataStructures.Iterator;

/**
 * This is the system interface of the program where all the operations are done
 */
public interface YouVideo {
    /**
     * Checks if a certain language code is invalid.
     *
     * @param code The code of the language to check
     * @return true if the code given is invalid
     */
    boolean isInvalidCode(String code);

    /**
     * Checks if the duration of the video is invalid.
     *
     * @param duration the time of the video to check
     * @return true if the duration given is invalid
     */
    boolean isInvalidDuration(int duration);

    /**
     * Creates a publishable video with a unique id.
     *
     * @param id            The id of the video.
     * @param duration      the time of the video.
     * @param url           the url of where the video is stored.
     * @param publisherName the name of the creator/publisher of the video.
     * @param title         the title of the video.
     * @param code          the code of the language of the video.
     * @pre !isInvalidCode(); !isInvalidDuration(); !isExistingVideo();
     */
    void createPublishableVideo
    (String id, int duration, String url, String publisherName, String title, String code);

    /**
     * Creates a premium publishable video with a unique id.
     *
     * @param id                   The id of the video.
     * @param duration             the time of the video.
     * @param url                  the url of where the video is stored.
     * @param publisherName        the name of the creator/publisher of the video.
     * @param title                the title of the video.
     * @param code                 the code of the language of the video.
     * @param subtitleUrl          the url of where the initial subtitle is stored in.
     * @param subtitleLanguageCode the code for a specific language subtitle.
     * @pre !isInvalidCode(); !isInvalidDuration(); !isExistingVideo()
     */
    void createPublishablePremiumVideo
    (String id, int duration, String url, String publisherName, String title, String code,
     String subtitleUrl, String subtitleLanguageCode);

    /**
     * Checks if a video exists by searching for its videoId.
     *
     * @param videoId the id of the video.
     * @return true if the video exists.
     */
    boolean isExistingVideo(String videoId);

    /**
     * Checks if a specific video is a premium video.
     *
     * @param videoId the id of the video.
     * @return true if it's a premium video
     */
    boolean isPremiumVideo(String videoId);

    /**
     * Creates a subtitle with a specific language for an existing video.
     *
     * @param videoId          the id of the video where to create the subtitle.
     * @param subtitleUrl      the url of where the initial subtitle is stored in.
     * @param subtitleLanguage the code for a specific language subtitle.
     * @pre isExistingVideo(); isPremiumVideo(); isValidLanguageCode()
     */
    void addSubtitle(String videoId, String subtitleUrl, String subtitleLanguage);

    /**
     * Gets all the information of a specific video that is searched for with its videoId.
     *
     * @param videoId the id of the video.
     * @pre isExistingVideo()
     */
    Video getVideo(String videoId);

    /**
     * Returns an iterator of subtitles from a certain premium video
     * @param videoId the id of the video with subtitles
     * @return the iterator of an array of subtitles
     * @pre isPremiumVideo()
     */
    Iterator<Subtitle> subtitleIterator(String videoId);

    /**
     * Checks if exists a podcast with a given title
     * @param title the title
     * @return true if there is a podcast with the title given and false otherwise
     */
    boolean isExistingPodcast(String title);

    /**
     * Creates a new podcast with no episodes.
     *
     * @param title  the title of the podcast.
     * @param author the name of the author/creator of the podcast.
     * @param code   the language code that identifies the podcast's language.
     * @pre isValidCode(); isTitleUnique()
     */
    void createPodcast(String title, String author, String code);

    /**
     * Checks if a certain episode exists
     * @param id the episode id
     * @return true if exists and false if it doesn't
     */
    boolean isExistingEpisode(String id);

    /**
     * Checks if an episode date is earlier than the latest existing episode in a certain podcast.
     *
     * @param podcast the podcast to be checked.
     * @param date the release date to check.
     * @return true if the episode date is earlier than the last episode date in the podcast.
     */
    boolean isDateEarlier(String podcast,String date);

    /**
     * Adds an episode to an existing podcast.
     *
     * @param title    the title of the podcast.
     * @param id       the unique id of the episode.
     * @param duration the duration of the episode in minutes.
     * @param url      the url of where the episode is stored.
     * @param date     the release date of the episode.
     * @pre !isTitleUnique(); isIdUnique(); isValidDuration(); !isDateEarlier()
     */
    void addEpisode(String title, String id, int duration, String url, String date);

    /**
     * Presents podcast data from its title.
     *
     * @param title the title of the podcast to retrieve.
     * @pre !isTitleUnique() && isExistingPodcast()
     */
    Podcast getPodcast(String title);

    /**
     * Checks if a podcast has any episodes available.
     *
     * @param title the title of the podcast.
     * @return true if the podcast has at least one episode.
     */
    boolean hasEpisodes(String title);

    /**
     * Checks if a specific author has any podcasts in the system.
     *
     * @param authorName the name of the author.
     * @return true if the author has at least one podcast.
     */
    boolean hasAuthorPodcasts(String authorName);

    /**
     * Searches for an author with the given name
     * @param name the name of the author
     * @return the author with that name
     */
    Author getAuthor(String name);

    /**
     * Lists all podcasts created by a given author, ordered by insertion.
     *
     * @param author a author object.
     * @pre hasAuthorPodcasts()
     */
    Iterator<Podcast> listAuthorPodcasts(Author author);

    /**
     * Removes a podcast and all of its episodes from the system.
     *
     * @param title the title of the podcast to remove.
     * @pre isExistingPodcast()
     */
    void removePodcast(String title);

    /**
     * Creates a new show using an existing publishable video.
     *
     * @param author           the author of the show.
     * @param videoId          the id of the publishable video to be shown.
     * @param transmissionDate the date the show will be broadcasted.
     * @pre isExistingVideo() || isExistingEpisode(); isShow()
     */
    void createShow(String author, String videoId, String transmissionDate);

    /**
     * Presents a show's data using its title.
     *
     * @param showTitle the title of the show to retrieve.
     * @return the show
     * @pre isExistingShow()
     */
    Show getShow(String showTitle);

    /**
     * Removes a show from the system without affecting the referenced video.
     *
     * @param showTitle the title of the show to remove.
     * @pre isExistingShow()
     */
    void removeShow(String showTitle);

    /**
     * Checks if a video is currently being referenced by an existing show.
     *
     * @param videoId the identifier of the video.
     * @return true if the video is used in a show.
     */
    boolean isShow(String videoId);

    /**
     * Checks if there is a show with the same title as the one given
     * @param showTitle the title
     * @return true if there is a show with the same title as the one given and false otherwise
     */
    boolean isExistingShow(String showTitle);

    /**
     * Removes a publishable video from the system using its identifier.
     *
     * @param videoId the identifier of the video to remove.
     * @pre !isExistingEpisode(); isExistingVideo(); !isInShow()
     */
    void removeVideo(String videoId);

    /**
     * Gets all episodes from a certain podcast given its title
     * @param title the title of the podcast
     * @return an iterator of episodes from the specific podcast
     * @pre isExistingPodcast(); hasEpisodes()
     */
    Iterator<Episode> getPodcastEpisodes(String title);
}
