import Collections.*;
import Podcast.Podcast;
import Show.Show;
import Author.*;
import Subtitles.Subtitle;
import Videos.*;
import dataStructures.*;

/**
 * This class implements the YouVideo interface and all the methods in it
 */
public class YouVideoClass implements YouVideo {
    private final PublishableCollection publishables;
    private final PodcastCollection podcasts;
    private final AuthorCollection authors;
    private final ShowCollection shows;

    public YouVideoClass() {
        publishables = new PublishableCollectionClass();
        podcasts = new PodcastCollectionClass();
        authors = new AuthorCollectionClass();
        shows = new ShowCollectionClass();
    }

    @Override
    public boolean isInvalidCode(String code) {
        return !publishables.isValidCode(code);
    }

    @Override
    public boolean isInvalidDuration(int duration) {
        return !publishables.isValidDuration(duration);
    }


    @Override
    public void createPublishableVideo
            (String id, int duration, String url, String publisherName, String title, String code) {
        publishables.createPublishable(id, duration, url, publisherName, title, code);

    }

    @Override
    public void createPublishablePremiumVideo
            (String id, int duration, String url, String publisherName, String title, String code,
             String subtitleUrl, String subtitleLanguageCode) {
        publishables.createPremium(id, duration, url, publisherName, title, code, subtitleUrl, subtitleLanguageCode);

    }

    @Override
    public boolean isExistingVideo(String videoId) {
        return publishables.isExistingVideo(videoId);
    }

    @Override
    public boolean isPremiumVideo(String videoId) {
        return publishables.isPremiumVideo(videoId);
    }

    @Override
    public void addSubtitle(String videoId, String subtitleUrl, String subtitleLanguage) {
        publishables.addSubtitle(videoId, subtitleUrl, subtitleLanguage);
    }

    @Override
    public Video getVideo(String videoId) {
        return publishables.getVideo(videoId);
    }


    @Override
    public Iterator<Subtitle> subtitleIterator(String videoId) {
        Video video = getVideo(videoId);
        if (video instanceof PremiumVideo premiumVideo) {
            return premiumVideo.getSubtitles();
        }
        return null;
    }

    @Override
    public boolean isExistingPodcast(String title) {
        return podcasts.isExistingPodcast(title);
    }

    @Override
    public void createPodcast(String title, String author, String code) {
        if (!authors.isAuthorExistant(author)) {
            authors.addAuthor(author);
        }
        podcasts.createPodcast(title, author, code);
        authors.getAuthor(author).addPodcast(podcasts.getPodcast(title));
    }

    @Override
    public boolean isExistingEpisode(String id) {
        return podcasts.isExistingEpisode(id);
    }

    @Override
    public boolean isDateEarlier(String podcast, String date) {
        return podcasts.isDateEarlier(podcast, date);
    }


    @Override
    public void addEpisode(String title, String id, int duration, String url, String date) {
        podcasts.getPodcast(title).addEpisode(id, duration, url, date);
    }

    @Override
    public Podcast getPodcast(String title) {
        return podcasts.getPodcast(title);
    }

    @Override
    public boolean hasEpisodes(String title) {
        return podcasts.hasEpisodes(title);
    }

    @Override
    public Iterator<Episode> getPodcastEpisodes(String title) {
        return podcasts.getPodcastEpisodes(title);
    }

    @Override
    public boolean hasAuthorPodcasts(String authorName) {
        return authors.authorHasPodcasts(authorName);
    }

    @Override
    public Author getAuthor(String name) {
        return authors.getAuthor(name);
    }

    @Override
    public Iterator<Podcast> listAuthorPodcasts(Author author) {
        return authors.podcastIterator(author);
    }

    @Override
    public void removePodcast(String title) {
        Podcast podcast = podcasts.getPodcast(title);
        podcasts.removePodcast(podcast);
        Author author = authors.getAuthor(podcast.getAuthor());
        author.removePodcast(podcast);
    }

    @Override
    public void createShow(String authorName, String videoId, String transmissionDate) {
        Video video = getVideo(videoId);
        if (video instanceof Publishable) {
            if (!authors.isAuthorExistant(authorName)) {
                authors.addAuthor(authorName);
            }
            shows.createShow(authors.getAuthor(authorName).getName(),transmissionDate, video);
            authors.addShowToAuthor
                    (authors.getAuthor(authorName).getName(), transmissionDate, video);
        }
    }

    @Override
    public Show getShow(String showTitle) {
        return shows.getShow(showTitle);
    }

    @Override
    public void removeShow(String showTitle) {
        shows.removeShow(showTitle);
        authors.removeShowFromAuthor(shows.getShow(showTitle));
    }

    @Override
    public boolean isShow(String videoId) {
        Video video = getVideo(videoId);
        return shows.isShow(video);
    }

    @Override
    public boolean isExistingShow(String showTitle) {
        return shows.isExistingShow(showTitle);
    }

    @Override
    public void removeVideo(String videoId) {
        publishables.removeVideo(videoId);
    }
}
