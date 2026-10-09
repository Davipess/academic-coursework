import Collections.*;
import Content.*;
import Exceptions.*;
import Author.*;
import Subtitles.Subtitle;
import Videos.*;

import java.time.LocalDate;
import java.util.Iterator;

/**
 * This class implements the YouVideo interface and all the methods in it
 */
public class YouVideoClass implements YouVideo {
    private final PublishableCollection publishables;
    private final PodcastCollection podcasts;
    private final AuthorCollection authors;
    private final ShowCollection shows;
    private final TagCollection tags;

    public YouVideoClass() {
        publishables = new PublishableCollectionClass();
        podcasts = new PodcastCollectionClass();
        authors = new AuthorCollectionClass();
        shows = new ShowCollectionClass();
        tags = new TagCollectionClass();
    }


    @Override
    public void createPublishableVideo
            (String id, int duration, String url, String publisherName, String title, String code) throws
            InvalidDurationException, InvalidCodeException, VideoAlreadyExistsException {
        publishables.createPublishable(id, duration, url, publisherName, title, code);
    }

    @Override
    public void createPublishablePremiumVideo
            (String id, int duration, String url, String publisherName, String title, String code,
             String subtitleUrl, String subtitleLanguageCode) throws InvalidDurationException, InvalidCodeException,
            VideoAlreadyExistsException, InvalidSubtitleLanguageException {
        publishables.createPremium(id, duration, url, publisherName, title, code, subtitleUrl, subtitleLanguageCode);

    }

    @Override
    public boolean isExistingVideo(String id) {
        return publishables.isExistingVideo(id);
    }

    @Override
    public void addSubtitle(String videoId, String subtitleUrl, String subtitleLanguage) throws InvalidCodeException,
            VideoIsNotPremiumException, VideoDoesNotExistException {
        publishables.addSubtitle(videoId, subtitleUrl, subtitleLanguage);
    }

    @Override
    public Video getVideo(String videoId) throws VideoDoesNotExistException {
        return publishables.getVideo(videoId);
    }


    @Override
    public Iterator<Subtitle> subtitleIterator(Video video) throws VideoIsNotPremiumException {
        if (!(video instanceof PremiumVideo))
            throw new VideoIsNotPremiumException();
        return ((PremiumVideo) video).getSubtitles();
    }

    @Override
    public void createPodcast(String title, String authorName, String code)
            throws AuthorNotExistsException, PodcastAlreadyExistsException {
        if (!authors.isAuthorExistant(authorName)) {
            authors.addAuthor(authorName);
        }
        authors.getAuthor(authorName).addPodcast(podcasts.createPodcast(title, authorName, code));
    }

    @Override
    public void addEpisode(String title, String id, int duration, String url, LocalDate date)
            throws InvalidDurationException, EpisodeAlreadyExistsException,
            DateIsEarlierException, PodcastDoesNotExistException {
        if (isExistingVideo(id))
            throw new EpisodeAlreadyExistsException();
        podcasts.addEpisode(title, id, duration, url, date);
    }

    @Override
    public Podcast getPodcast(String title) throws PodcastDoesNotExistException {
        return podcasts.getPodcast(title);
    }

    @Override
    public Iterator<Episode> getPodcastEpisodes(String title) throws PodcastDoesNotExistException, NoEpisodesException {
        return podcasts.getPodcastEpisodes(title);
    }

    @Override
    public Author getAuthor(String name) throws AuthorNotExistsException {
        return authors.getAuthor(name);
    }

    @Override
    public Iterator<PodcastGetters> listAuthorPodcasts(String authorName) throws AuthorNotExistsException,
            AuthorHasNoPodcastsException {
        Author author = getAuthor(authorName);
        return authors.podcastIterator(author);
    }

    @Override
    public void removePodcast(String title) throws AuthorNotExistsException, PodcastDoesNotExistException {
        Podcast podcast = podcasts.getPodcast(title);
        Author author = authors.getAuthor(podcast.getAuthor());
        podcasts.removePodcast(podcast);
        author.removePodcast(podcast);
    }

    @Override
    public void createShow(String authorName, String videoId, LocalDate transmissionDate)
            throws VideoDoesNotExistException, ShowAlreadyExistsException,
            AuthorNotExistsException {
        if (!isExistingVideo(videoId))
            throw new VideoDoesNotExistException();
        Video video = getVideo(videoId);
        if (video instanceof Publishable) {
            if (isExistingShow(((Publishable) video).getTitle())) {
                throw new ShowAlreadyExistsException();
            }
            if (!authors.isAuthorExistant(authorName)) {
                authors.addAuthor(authorName);
            }
            Author author = authors.getAuthor(authorName);
            String name = author.getName();
            Show show = shows.createShow(name, transmissionDate, video);
            authors.addShowToAuthor(show);
        }
    }

    @Override
    public Show getShow(String showTitle) throws ShowDoesNotExistException {
        return shows.getShow(showTitle);
    }

    @Override
    public void removeShow(String showTitle) throws AuthorNotExistsException, ShowDoesNotExistException {
        Show show = getShow(showTitle);
        shows.removeShow(show);
        authors.removeShowFromAuthor(show);
    }

    @Override
    public boolean isExistingShow(String showTitle) {
        return shows.isExistingShow(showTitle);
    }

    @Override
    public void removeVideo(String videoId) throws VideoIsEpisodeException, VideoIsShowException,
            VideoDoesNotExistException {
        if (podcasts.isExistingEpisode(videoId))
            throw new VideoIsEpisodeException();
        Video video = getVideo(videoId);
        if (video instanceof Publishable) {
            if (shows.isExistingShow(((Publishable) video).getTitle()))
                throw new VideoIsShowException();
            publishables.removeVideo(video);
        }
    }

    @Override
    public boolean hasProductiveAuthors() {
        return authors.hasProductiveAuthors();
    }

    @Override
    public Iterator<AuthorGetters> getAuthorsByProductivity() {
        return authors.getAuthorsByProductivity();
    }

    @Override
    public void addTag(String title, String tagName) throws TitleDoesNotExistsException, TitleIsAlreadyTaggedException,
            PodcastDoesNotExistException, ShowDoesNotExistException {
        boolean existsShow = shows.isExistingShow(title);
        boolean existsPodcast = podcasts.isExistingPodcast(title);

        if (!existsShow && !existsPodcast) {
            throw new TitleDoesNotExistsException();
        }

        boolean addedAny = false;

        if (existsShow) {
            Show show = shows.getShow(title);
            if (show instanceof Content) {
                if (!show.hasTag(tagName)) {
                    tags.addTag(tagName, (Content) show);
                    addedAny = true;
                }
            }

        }

        if (existsPodcast) {
            Podcast pod = podcasts.getPodcast(title);
            if (pod instanceof Content) {
                if (!pod.hasTag(tagName)) {
                    tags.addTag(tagName, (Content) pod);
                    addedAny = true;
                }
            }
        }

        if (!addedAny) {
            throw new TitleIsAlreadyTaggedException();
        }
    }

    @Override
    public void removeTag(String title, String tagName) throws TitleDoesNotExistsException, TitleIsNotTaggedException,
            PodcastDoesNotExistException, ShowDoesNotExistException {
        boolean existsShow = shows.isExistingShow(title);
        boolean existsPodcast = podcasts.isExistingPodcast(title);

        if (!existsShow && !existsPodcast) {
            throw new TitleDoesNotExistsException();
        }

        boolean removedAny = false;

        if (existsShow) {
            Show show = shows.getShow(title);
            if (show instanceof Content) {
                if (show.hasTag(tagName)) {
                    tags.removeTag(tagName, (Content) show);
                    removedAny = true;
                }
            }
        }

        if (existsPodcast) {
            Podcast pod = podcasts.getPodcast(title);
            if (pod instanceof Content) {
                if (pod.hasTag(tagName)) {
                    tags.removeTag(tagName, (Content) pod);
                    removedAny = true;
                }
            }

        }

        if (!removedAny) {
            throw new TitleIsNotTaggedException();
        }
    }

    @Override
    public Iterator<ContentGetters> contentIterator(String tagName, String contentType, String order)
            throws InvalidParametersException, TagDoesNotExistException {
        return tags.contentIterator(tagName, contentType, order);
    }
}
