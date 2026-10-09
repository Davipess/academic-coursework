import Author.AuthorGetters;
import Content.ContentGetters;
import Content.PodcastGetters;
import Content.ShowGetters;
import Content.Tag;
import Subtitles.Subtitle;
import Exceptions.*;
import Videos.*;

import java.time.LocalDate;
import java.util.Locale;
import java.util.Scanner;
import java.util.Iterator;

/**
 * Authors: Francisco Lourenço (74259) and David Figueiredo (74167)
 * This is the main class for a platform called YouVideo.
 * This platform is a simplified version of a streaming platform.
 * This platform is capable of managing different types of visual media such videos, podcasts and shows.
 */

public class Main {

    // Output Messages
    private static final String UNKNOWN_COMMAND_MSG =
            "Unknown command. Type help to see available commands.";
    private static final String BYE_MSG = "Bye!";
    private static final String INVALID_LANGUAGE_MSG = "Invalid language type.";
    private static final String INVALID_VALUE_MSG = "Invalid value.";
    private static final String VIDEO_ID_ALREADY_EXISTS_MSG = "Video with this ID already exists.";
    private static final String CREATED_PUBLISHABLE_MSG = "Video %s created successfully.\n";
    private static final String CREATE_PREMIUM_MSG = "PREMIUM Video %s created successfully.\n";
    private static final String INVALID_SUBTITLE_LANGUAGE_TYPE_MSG =
            "Invalid language type in subtitle.";
    private static final String VIDEO_NON_EXISTENT_MSG = "Video does not exist.";
    private static final String OP_REQUIRES_PREMIUM_MSG =
            "This operation requires a Premium video.";
    private static final String SUBTITLE_ADDED_MSG = "Subtitle added successfully.";
    private static final String PUBLISHABLE_VIDEO_NON_EXISTENT_MSG =
            "Publishable Video %s does not exist.\n";
    private static final String LIST_SUBTITLES = "Subtitles for video %s:\n";
    private static final String SUBTITLE_LAYOUT_MSG = "- %s (%s)\n";
    private static final String NO_PREMIUM_VIDEO_MSG = "No Premium Video with ID.";
    private static final String PODCATS_CREATED_MSG = "Podcast created successfully.";
    private static final String PODCAST_ALREADY_EXISTS_MSG =
            "Podcast with this title already exists.";
    private static final String PODCAST_NON_EXISTENT_MSG = "Podcast does not exist.";
    private static final String EPISODE_ID_ALREADY_EXISTS_MSG =
            "Episode ID already exists in the system.";
    private static final String EPISODE_DATE_IS_EARLY_MSG =
            "Episode date must be >= than latest episode date.";
    private static final String EPISODE_ADDED_MSG = "Episode added successfully.";
    private static final String PODCAST_LAYOUT_MSG = "Podcast: %s Author: %s Language: %s\n";
    private static final String LATEST_DATE_MSG = "Latest episode date: %s\n";
    private static final String TAGS_HEADER = "Tags:";
    private static final String NO_EPISODES_AVAILABLE_MSG =
            "No episodes available for this podcast.";
    private static final String NO_PODCASTS_FOR_AUTHOR_MSG = "No podcasts found for this author.";
    private static final String AUTHOR_PODCASTS_HEADER_MSG = "Podcasts by author %s:\n";
    private static final String VIDEO_FOR_SHOW_NON_EXISTENT_MSG = "Video for show does not exist.";
    private static final String SHOW_ALREADY_EXISTS_MSG = "Show with this title already exists.";
    private static final String SHOW_CREATED_MSG = "Show created successfully.";
    private static final String SHOW_NON_EXISTENT_MSG = "Show does not exist.";
    private static final String VIDEO_IS_EPISODE_MSG =
            "Cannot remove: video is an episode of a podcast.";
    private static final String VIDEO_IS_IN_SHOW_MSG = "Cannot remove: video is used in a show.";
    private static final String VIDEO_REMOVED_MSG = "Video removed successfully.";
    private static final String EPISODES_HEADER_MSG = "Episodes for podcast %s:\n";
    private static final String EPISODE_LAYOUT = "Episode %s: %d min Date: %s\nURL: %s\n";
    private static final String GET_VIDEO_LAYOUT =
            "Video %s %d Title: %s\nFile: %s Publisher: %s Language: %S\n";
    private static final String GET_PREMIUM_VIDEO_LAYOUT =
            "PREMIUM Video %s %d Title: %s\nFile: %s Publisher: %s Language: %S\n";
    private static final String PODCAST_REMOVED_MSG = "Podcast removed successfully.";
    private static final String AUTHOR_SHOWS_HEADER = "Shows by author %s:\n";
    private static final String SHOW_LAYOUT = "Show Date: %s Author: %s\nVideo: %s\n";
    private static final String SHOW_LAYOUT_2 = "Date: %s Show: %s Duration: %d Language: %S\n";
    private static final String NO_SHOWS_FROM_AUTHOR_MSG = "No shows found for this author.";
    private static final String SHOW_REMOVED_MSG = "Show removed successfully.";
    private static final String NO_PRODUCTIVE_AUTHORS_MSG = "No productive authors.";
    private static final String AUTHORS_PRODUCTIVITY_HEADER = "Authors productivity:";
    private static final String PRODUCTIVITY_LAYOUT = "%s with %d contributions.\n";
    private static final String TITLE_NOT_EXISTS_MSG = "Title does not exist.";
    private static final String TITLE_IS_ALREADY_TAGGED_MSG = "Title is already tagged with %s.\n";
    private static final String TAG_ADDED_MSG = "Tag added successfully.";
    private static final String TITLE_IS_NOT_TAGGED_MSG = "Title is not tagged with %s.\n";
    private static final String TAGGED_REMOVED_MSG = "Tag removed successfully.";
    private static final String INVALID_TAGGED_PARAMETERS_MSG = "Invalid tagged parameters.";
    private static final String NO_CONTENT_WITH_TAG_MSG = "No content tagged with %s.\n";
    private static final String TAGGED_HEADER = "Content tagged with %s in %s:\n";
    private static final String CONTENT_LAYOUT = "%s Title: %s Author: %s\n";

    //Order type enum
    private enum Orders {
        ASC("Ascending order"),
        DES("Descending order");

        private final String label;

        Orders(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }
    }

    //Tagged content type enum
    private enum Types {
        PODCAST("Podcast"),
        SHOW("Show");

        private final String label;

        Types(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        YouVideo yv = new YouVideoClass();
        executeCommand(sc, yv);
        sc.close();
    }

    private static Commands readCommand(Scanner sc) {
        try {
            String str = sc.next().trim().toUpperCase();
            return Commands.valueOf(str);
        } catch (Exception e) {
            return Commands.UNKNOWN;
        }
    }

    private static void executeCommand(Scanner sc, YouVideo yv) {
        Commands cmd;
        do {
            cmd = readCommand(sc);
            switch (cmd) {
                case CREATEPUBLISHABLE -> setCreatePublishable(sc, yv);
                case CREATEPREMIUM -> setCreatePremium(sc, yv);
                case ADDSUBTITLE -> setAddSubtitle(sc, yv);
                case GETVIDEO -> setGetVideo(sc, yv);
                case SUBTITLES -> setSubtitles(sc, yv);
                case CREATEPODCAST -> setCreatePodcast(sc, yv);
                case ADDEPISODE -> setAddEpisode(sc, yv);
                case GETPODCAST -> setGetPodcast(sc, yv);
                case EPISODES -> setEpisodes(sc, yv);
                case AUTHORPODCASTS -> setAuthorPodcasts(sc, yv);
                case REMOVEPODCAST -> setRemovePodcast(sc, yv);
                case CREATESHOW -> setCreateShow(sc, yv);
                case GETSHOW -> setGetShow(sc, yv);
                case AUTHORSHOWS -> setAuthorShows(sc, yv);
                case REMOVESHOW -> setRemoveShow(sc, yv);
                case REMOVEVIDEO -> setRemoveVideo(sc, yv);
                case AUTHORSPRODUCTIVITY -> setAuthorProductivity(yv);
                case ADDTAG -> setAddTag(sc, yv);
                case REMOVETAG -> setRemoveTag(sc, yv);
                case TAGGED -> setTagged(sc, yv);
                case HELP -> setHelp();
                case EXIT -> setExit();
                case UNKNOWN -> setUnknown();
            }
        } while (!cmd.equals(Commands.EXIT));
    }

    private static void setCreatePublishable(Scanner sc, YouVideo yv) {
        String id = sc.next().trim();
        int duration = sc.nextInt();
        String url = sc.nextLine().trim();
        String publisher = sc.nextLine();
        String title = sc.nextLine();
        String code = sc.nextLine().toLowerCase();

        try {
            yv.createPublishableVideo(id, duration, url, publisher, title, code);
            System.out.printf(CREATED_PUBLISHABLE_MSG, id);
        } catch (InvalidCodeException e) {
            System.out.println(INVALID_LANGUAGE_MSG);
        } catch (InvalidDurationException e) {
            System.out.println(INVALID_VALUE_MSG);
        } catch (VideoAlreadyExistsException e) {
            System.out.println(VIDEO_ID_ALREADY_EXISTS_MSG);
        }
    }

    private static void setCreatePremium(Scanner sc, YouVideo yv) {
        String id = sc.next().trim();
        int duration = sc.nextInt();
        String url = sc.nextLine().trim();
        String publisher = sc.nextLine();
        String title = sc.nextLine();
        String code = sc.nextLine().toLowerCase();
        String subtitleUrl = sc.nextLine();
        String subtitleCode = sc.nextLine().toLowerCase();

        try {
            yv.createPublishablePremiumVideo(id, duration, url, publisher, title, code, subtitleUrl, subtitleCode);
            System.out.printf(CREATE_PREMIUM_MSG, id);
        } catch (InvalidCodeException e) {
            System.out.println(INVALID_LANGUAGE_MSG);
        } catch (InvalidSubtitleLanguageException e) {
            System.out.println(INVALID_SUBTITLE_LANGUAGE_TYPE_MSG);
        } catch (InvalidDurationException e) {
            System.out.println(INVALID_VALUE_MSG);
        } catch (VideoAlreadyExistsException e) {
            System.out.println(VIDEO_ID_ALREADY_EXISTS_MSG);
        }
    }

    private static void setAddSubtitle(Scanner sc, YouVideo yv) {
        String id = sc.next().trim();
        String url = sc.nextLine().trim();
        String subCode = sc.nextLine();
        try {
            yv.addSubtitle(id, url, subCode);
            System.out.println(SUBTITLE_ADDED_MSG);
        } catch (InvalidCodeException e) {
            System.out.println(INVALID_SUBTITLE_LANGUAGE_TYPE_MSG);
        } catch (VideoDoesNotExistException e) {
            System.out.println(VIDEO_NON_EXISTENT_MSG);
        } catch (VideoIsNotPremiumException e) {
            System.out.println(OP_REQUIRES_PREMIUM_MSG);
        }
    }

    private static void setGetVideo(Scanner sc, YouVideo yv) {
        String id = sc.nextLine().trim();

        try {
            Video video = yv.getVideo(id);
            if (video instanceof Publishable publishable) {
                Locale loc = Locale.of(publishable.getCode());
                String languageName = loc.getDisplayLanguage(Locale.UK);
                String layout = (video instanceof PremiumVideo) ? GET_PREMIUM_VIDEO_LAYOUT : GET_VIDEO_LAYOUT;
                System.out.printf(layout, video.getId(), video.getDuration(), publishable.getTitle(), video.getUrl(), publishable.getPublisher(),
                        languageName);
            }
        } catch (VideoDoesNotExistException e) {
            System.out.printf(PUBLISHABLE_VIDEO_NON_EXISTENT_MSG, id);
        }
    }

    private static void setSubtitles(Scanner sc, YouVideo yv) {
        String id = sc.next().trim();
        try {
            Video v = yv.getVideo(id);
            Iterator<Subtitle> it = yv.subtitleIterator(v);
            if (v instanceof Publishable) {
                System.out.printf(LIST_SUBTITLES, ((Publishable) v).getTitle());
                while (it.hasNext()) {
                    Subtitle sub = it.next();
                    Locale loc = Locale.of(sub.code());
                    System.out.printf(SUBTITLE_LAYOUT_MSG, sub.url(), loc.getDisplayLanguage(Locale.UK).toUpperCase());
                }
            }
        } catch (VideoIsNotPremiumException | VideoDoesNotExistException e) {
            System.out.println(NO_PREMIUM_VIDEO_MSG);
        }
    }

    private static void setCreatePodcast(Scanner sc, YouVideo yv) {
        String title = sc.nextLine().trim();
        String author = sc.nextLine();
        String code = sc.nextLine();
        try {
            yv.createPodcast(title, author, code);
            System.out.println(PODCATS_CREATED_MSG);
        } catch (PodcastAlreadyExistsException e) {
            System.out.println(PODCAST_ALREADY_EXISTS_MSG);
        } catch (AuthorNotExistsException ignored) {
        }
    }

    private static void setAddEpisode(Scanner sc, YouVideo yv) {
        String podcast = sc.nextLine().trim();
        String id = sc.next();
        int duration = sc.nextInt();
        String url = sc.nextLine().trim();
        LocalDate date = LocalDate.parse(sc.nextLine());
        try {
            yv.addEpisode(podcast, id, duration, url, date);
            System.out.println(EPISODE_ADDED_MSG);
        } catch (InvalidDurationException e) {
            System.out.println(INVALID_VALUE_MSG);
        } catch (PodcastDoesNotExistException e) {
            System.out.println(PODCAST_NON_EXISTENT_MSG);
        } catch (EpisodeAlreadyExistsException e) {
            System.out.println(EPISODE_ID_ALREADY_EXISTS_MSG);
        } catch (DateIsEarlierException e) {
            System.out.println(EPISODE_DATE_IS_EARLY_MSG);
        }
    }

    private static void setGetPodcast(Scanner sc, YouVideo yv) {
        String title = sc.nextLine().trim();
        try {
            PodcastGetters pod = yv.getPodcast(title);
            System.out.printf(PODCAST_LAYOUT_MSG, pod.getTitle(), pod.getAuthor(), pod.getCode());
            if (pod.hasEpisodes())
                System.out.printf(LATEST_DATE_MSG, pod.getLastEpisode().getDate());
            if (pod.isTagged()) {
                System.out.println(TAGS_HEADER);
                Iterator<Tag> it = pod.getTags();
                while (it.hasNext()) {
                    Tag tag = it.next();
                    System.out.println(tag.name());
                }
            }
        } catch (PodcastDoesNotExistException e) {
            System.out.println(PODCAST_NON_EXISTENT_MSG);
        }
    }

    private static void setEpisodes(Scanner sc, YouVideo yv) {
        String title = sc.nextLine().trim();
        try {
            Iterator<Episode> it = yv.getPodcastEpisodes(title);
            System.out.printf(EPISODES_HEADER_MSG, title);
            while (it.hasNext()) {
                Episode ep = it.next();
                System.out.printf(EPISODE_LAYOUT, ep.getId(), ep.getDuration(), ep.getDate(), ep.getUrl());
            }
        } catch (PodcastDoesNotExistException e) {
            System.out.println(PODCAST_NON_EXISTENT_MSG);
        } catch (NoEpisodesException e) {
            System.out.println(NO_EPISODES_AVAILABLE_MSG);
        }
    }

    private static void setAuthorPodcasts(Scanner sc, YouVideo yv) {
        String authorName = sc.nextLine().trim();
        try {
            Iterator<PodcastGetters> it = yv.listAuthorPodcasts(authorName);
            AuthorGetters author = yv.getAuthor(authorName);
            System.out.printf(AUTHOR_PODCASTS_HEADER_MSG, authorName);
            while (it.hasNext()) {
                PodcastGetters pod = it.next();
                System.out.printf(PODCAST_LAYOUT_MSG, pod.getTitle(), author.getName(), pod.getCode());
            }

        } catch (AuthorHasNoPodcastsException | AuthorNotExistsException e) {
            System.out.println(NO_PODCASTS_FOR_AUTHOR_MSG);
        }
    }

    private static void setRemovePodcast(Scanner sc, YouVideo yv) {
        String title = sc.nextLine().trim();
        try {
            yv.removePodcast(title);
            System.out.println(PODCAST_REMOVED_MSG);
        } catch (PodcastDoesNotExistException | AuthorNotExistsException e) {
            System.out.println(PODCAST_NON_EXISTENT_MSG);
        }
    }

    private static void setCreateShow(Scanner sc, YouVideo yv) {
        String author = sc.nextLine().trim();
        String id = sc.next().trim();
        LocalDate date = LocalDate.parse(sc.nextLine().trim());
        try {
            yv.createShow(author, id, date);
            System.out.println(SHOW_CREATED_MSG);
        } catch (VideoDoesNotExistException | AuthorNotExistsException e) {
            System.out.println(VIDEO_FOR_SHOW_NON_EXISTENT_MSG);
        } catch (ShowAlreadyExistsException e) {
            System.out.println(SHOW_ALREADY_EXISTS_MSG);
        }
    }

    private static void setGetShow(Scanner sc, YouVideo yv) {
        String title = sc.nextLine().trim();
        try {
            ShowGetters show = yv.getShow(title);
            System.out.printf(SHOW_LAYOUT, show.getTransmissionDate(), show.getAuthor(), show.getTitle());
            Iterator<Tag> it = show.getTags();
            if (show.isTagged()) {
                System.out.println(TAGS_HEADER);
                while (it.hasNext()) {
                    Tag tag = it.next();
                    System.out.println(tag.name());
                }
            }
        } catch (ShowDoesNotExistException e) {
            System.out.println(SHOW_NON_EXISTENT_MSG);
        }
    }

    private static void setAuthorShows(Scanner sc, YouVideo yv) {
        String authorName = sc.nextLine().trim();
        try {
            AuthorGetters author = yv.getAuthor(authorName);
            if (!author.hasShows())
                System.out.println(NO_SHOWS_FROM_AUTHOR_MSG);
            else {
                Iterator<ShowGetters> it = author.showsIterator();
                System.out.printf(AUTHOR_SHOWS_HEADER, authorName);
                while (it.hasNext()) {
                    ShowGetters show = it.next();
                    System.out.printf(SHOW_LAYOUT_2,
                            show.getTransmissionDate(), show.getTitle(),
                            show.getVideo().getDuration(), show.getLanguageCode());
                }
            }
        } catch (AuthorNotExistsException e) {
            System.out.println(NO_SHOWS_FROM_AUTHOR_MSG);
        }

    }

    private static void setRemoveShow(Scanner sc, YouVideo yv) {
        String title = sc.nextLine().trim();
        try {
            yv.removeShow(title);
            System.out.println(SHOW_REMOVED_MSG);
        } catch (ShowDoesNotExistException | AuthorNotExistsException e) {
            System.out.println(SHOW_NON_EXISTENT_MSG);
        }
    }

    private static void setRemoveVideo(Scanner sc, YouVideo yv) {
        String id = sc.nextLine().trim();
        try {
            yv.removeVideo(id);
            System.out.println(VIDEO_REMOVED_MSG);
        } catch (VideoIsEpisodeException e) {
            System.out.println(VIDEO_IS_EPISODE_MSG);
        } catch (VideoDoesNotExistException e) {
            System.out.println(VIDEO_NON_EXISTENT_MSG);
        } catch (VideoIsShowException e) {
            System.out.println(VIDEO_IS_IN_SHOW_MSG);
        }
    }

    private static void setAuthorProductivity(YouVideo yv) {
        if (!yv.hasProductiveAuthors()) {
            System.out.println(NO_PRODUCTIVE_AUTHORS_MSG);
        } else {
            System.out.println(AUTHORS_PRODUCTIVITY_HEADER);
            Iterator<AuthorGetters> it = yv.getAuthorsByProductivity();
            while (it.hasNext()) {
                AuthorGetters author = it.next();
                System.out.printf
                        (PRODUCTIVITY_LAYOUT, author.getName(), author.getProductivity());
            }
        }
    }

    private static void setAddTag(Scanner sc, YouVideo yv) {
        String title = sc.nextLine().trim();
        String tag = sc.nextLine().trim();

        try {
            yv.addTag(title, tag);
            System.out.println(TAG_ADDED_MSG);
        } catch (TitleDoesNotExistsException | PodcastDoesNotExistException | ShowDoesNotExistException e) {
            System.out.println(TITLE_NOT_EXISTS_MSG);
        } catch (TitleIsAlreadyTaggedException e) {
            System.out.printf(TITLE_IS_ALREADY_TAGGED_MSG, tag);
        }
    }

    private static void setRemoveTag(Scanner sc, YouVideo yv) {
        String title = sc.nextLine().trim();
        String tag = sc.nextLine().trim();

        try {
            yv.removeTag(title, tag);
            System.out.println(TAGGED_REMOVED_MSG);
        } catch (TitleDoesNotExistsException | PodcastDoesNotExistException | ShowDoesNotExistException e) {
            System.out.println(TITLE_NOT_EXISTS_MSG);
        } catch (TitleIsNotTaggedException e) {
            System.out.printf(TITLE_IS_NOT_TAGGED_MSG, tag);
        }
    }

    private static void setTagged(Scanner sc, YouVideo yv) {
        String tagName = sc.next().trim();
        String contentType = sc.next().trim();
        String order = sc.nextLine().trim();

        try {
            Iterator<ContentGetters> it = yv.contentIterator(tagName, contentType, order);
            System.out.printf(TAGGED_HEADER, tagName, Orders.valueOf(order.toUpperCase()).getLabel());
            while (it.hasNext()) {
                ContentGetters content = it.next();
                String type = (content instanceof PodcastGetters) ? Types.PODCAST.getLabel() : Types.SHOW.getLabel();
                System.out.printf(CONTENT_LAYOUT, type, content.getTitle(), content.getAuthor());
            }
        } catch (InvalidParametersException e) {
            System.out.println(INVALID_TAGGED_PARAMETERS_MSG);
        } catch (TagDoesNotExistException e) {
            System.out.printf(NO_CONTENT_WITH_TAG_MSG, tagName);
        }
    }

    private static void setHelp() {
        for (Commands cmd : Commands.values()) {
            if (!cmd.equals(Commands.UNKNOWN))
                System.out.println(cmd.getDescription());
        }
    }

    private static void setExit() {
        System.out.println(BYE_MSG);
    }

    private static void setUnknown() {
        System.out.println(UNKNOWN_COMMAND_MSG);
    }
}