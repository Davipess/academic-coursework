import Author.Author;
import Podcast.Podcast;
import Show.*;
import Subtitles.Subtitle;
import Videos.*;
import dataStructures.Iterator;

import java.util.Locale;
import java.util.Scanner;

/**
 * Authors: Francisco Lourenço (74259) and David Figueiredo (74167)
 * This is the main class for a platform called YouVideo. This platform is a simplified version of
 * a streaming platform. This platform is capable of managing different types of visual media such
 * videos, podcasts and shows.
 */
public class Main {
    //Commands
    private static final String CREATE_PUBLISHABLE = "createpublishable";
    private static final String CREATE_PREMIUM = "createpremium";
    private static final String ADD_SUBTITLE = "addsubtitle";
    private static final String GET_VIDEO = "getvideo";
    private static final String SUBTITLES = "subtitles";
    private static final String CREATE_PODCAST = "createpodcast";
    private static final String ADD_EPISODE = "addepisode";
    private static final String GET_PODCAST = "getpodcast";
    private static final String EPISODES = "episodes";
    private static final String AUTHOR_PODCASTS = "authorpodcasts";
    private static final String REMOVE_PODCAST = "removepodcast";
    private static final String CREATE_SHOW = "createshow";
    private static final String GET_SHOW = "getshow";
    private static final String REMOVE_SHOW = "removeshow";
    private static final String REMOVE_VIDEO = "removevideo";
    private static final String HELP = "help";
    private static final String EXIT = "exit";

    //Output messages and layouts
    private static final String UNKNOWN_COMMAND_MSG = "Unknown command. Type help to see " +
            "available commands.";
    private static final String BYE_MSG = "Bye!";
    private static final String HELP_MSG = """
            createpublishable - creates a new publishable video
            createpremium - creates a new publishable Premium video
            addsubtitle - adds subtitle to Premium video
            getvideo - presents publishable video data from its id
            subtitles - Lists Premium video subtitles
            createpodcast - creates a new podcast with no episodes
            addepisode - adds an episode to a podcast
            getpodcast - presents podcast data from its title
            episodes - List podcast episodes
            authorpodcasts - List all podcasts of an author
            removepodcast - removes a podcast
            createshow - creates show using an existing publishable video
            getshow - presents show data from its title
            removeshow - removes a show
            removevideo - removes a publishable video
            help - shows the available commands
            exit - terminates the execution of the program""";
    private static final String INVALID_LANGUAGE_MSG = "Invalid language type.";
    private static final String INVALID_VALUE_MSG = "Invalid value.";
    private static final String VIDEO_ID_ALREADY_EXISTS_MSG = "Video with this ID already exists.";
    private static final String CREATED_PUBLISHABLE_MSG = "Video %s created successfully.\n";
    private static final String CREATE_PREMIUM_MSG = "PREMIUM Video %s created successfully.\n";
    private static final String INVALID_SUBTITLE_LANGUAGE_TYPE_MSG = "Invalid language type in " +
            "subtitle.";
    private static final String VIDEO_NON_EXISTENT_MSG = "Video does not exist.";
    private static final String OP_REQUIRES_PREMIUM_MSG = "This operation requires a Premium " +
            "video.";
    private static final String SUBTITLE_ADDED_MSG = "Subtitle added successfully.";
    private static final String PUBLISHABLE_VIDEO_NON_EXISTENT_MSG = "Publishable Video %s does " +
            "not exist.\n";
    private static final String LIST_SUBTITLES = "Subtitles for video %s:\n";
    private static final String SUBTITLE_LAYOUT_MSG = "- %s (%s)\n";
    private static final String NO_PREMIUM_VIDEO_MSG = "No Premium Video with ID.";
    private static final String PODCATS_CREATED_MSG = "Podcast created successfully.";
    private static final String PODCAST_ALREADY_EXISTS_MSG = "Podcast with this title already " +
            "exists.";
    private static final String PODCAST_NON_EXISTENT_MSG = "Podcast does not exist.";
    private static final String EPISODE_ID_ALREADY_EXISTS_MSG = "Episode ID already exists in the " +
            "system.";
    private static final String EPISODE_DATE_IS_EARLY_MSG = "Episode date must be >= than latest" +
            " episode date.";
    private static final String EPISODE_ADDED_MSG = "Episode added successfully.";
    private static final String PODCAST_LAYOUT_MSG = "Podcast: %s Author: %s Language: %s\n";
    private static final String LATEST_DATE_MSG = "Latest episode date: %s\n";
    private static final String NO_EPISODES_AVAILABLE_MSG = "No episodes available for this" +
            " podcast.";
    private static final String NO_PODCASTS_FOR_AUTHOR_MSG = "No podcasts found for this author.";
    private static final String AUTHOR_PODCASTS_HEADER_MSG = "Podcasts by author %s:\n";
    private static final String VIDEO_FOR_SHOW_NON_EXISTENT_MSG = "Video for show does not exist.";
    private static final String SHOW_ALREADY_EXISTS_MSG = "Show with this title already exists.";
    private static final String SHOW_CREATED_MSG = "Show created successfully.";
    private static final String SHOW_NON_EXISTENT_MSG = "Show does not exist.";
    private static final String VIDEO_IS_EPISODE_MSG = "Cannot remove: video is an episode of a" +
            " podcast.";
    private static final String VIDEO_IS_IN_SHOW_MSG = "Cannot remove: video is used in a show.";
    private static final String VIDEO_REMOVED_MSG = "Video removed successfully.";
    private static final String EPISODES_HEADER_MSG = "Episodes for podcast %s:\n";
    private static final String EPISODE_LAYOUT = "Episode %s: %d min Date: %s\nURL: %s\n";
    private static final String GET_VIDEO_LAYOUT = """
            Video %s %d Title: %s
            File:\
             %s Publisher: %s Language: %S
            """;
    private static final String GET_PREMIUM_VIDEO_LAYOUT = """
            PREMIUM Video %s %d Title:\
             %s
            File: %s Publisher: %s Language: %S
            """;
    private static final String PODCAST_REMOVED_MSG = "Podcast removed successfully.";
    private static final String SHOW_LAYOUT = """
            Show Date: %s Author: %s
            Video: %s
            """;
    private static final String SHOW_REMOVED_MSG = "Show removed successfully.";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        YouVideo yv = new YouVideoClass();
        executeCommand(sc, yv);
        sc.close();
    }

    /**
     * Command manager that executes commands based on each case.
     *
     * @param sc Scanner that reads the user input
     * @param yv System class
     */
    private static void executeCommand(Scanner sc, YouVideo yv) {
        String cmd;
        do {
            cmd = sc.next().toLowerCase();
            switch (cmd) {
                case REMOVE_VIDEO -> setRemoveVideo(sc, yv);
                case REMOVE_SHOW -> setRemoveShow(sc, yv);
                case GET_SHOW -> setGetShow(sc, yv);
                case CREATE_SHOW -> setCreateShow(sc, yv);
                case REMOVE_PODCAST -> setRemovePodcast(sc, yv);
                case AUTHOR_PODCASTS -> setAuthorPodcasts(sc, yv);
                case EPISODES -> setEpisodes(sc, yv);
                case GET_PODCAST -> setGetPodcast(sc, yv);
                case ADD_EPISODE -> setAddEpisode(sc, yv);
                case CREATE_PODCAST -> setCreatePodcast(sc, yv);
                case SUBTITLES -> setSubtitles(sc, yv);
                case CREATE_PUBLISHABLE -> setCreatePublishable(sc, yv);
                case CREATE_PREMIUM -> setCreatePremium(sc, yv);
                case ADD_SUBTITLE -> setAddSubtitle(sc, yv);
                case GET_VIDEO -> setGetVideo(sc, yv);
                case HELP -> setHelp(sc);
                case EXIT -> setExit();
                default -> setDefault();
            }
        } while (!cmd.equals(EXIT));
    }


    /**
     * Sets the command "createpublishable".
     *
     * @param sc: scanner that reads the input
     * @param yv: YouVideo where the video is created
     */
    private static void setCreatePublishable(Scanner sc, YouVideo yv) {
        String id = sc.next().trim();
        int duration = sc.nextInt();
        String url = sc.nextLine().trim();
        String publisher = sc.nextLine();
        String title = sc.nextLine();
        String code = sc.nextLine().toLowerCase();
        if (yv.isInvalidCode(code))
            System.out.println(INVALID_LANGUAGE_MSG);
        else if (yv.isInvalidDuration(duration))
            System.out.println(INVALID_VALUE_MSG);
        else if (yv.isExistingVideo(id))
            System.out.println(VIDEO_ID_ALREADY_EXISTS_MSG);
        else {
            yv.createPublishableVideo(id, duration, url, publisher, title, code);
            System.out.printf(CREATED_PUBLISHABLE_MSG, id);
        }
    }

    /**
     * Sets the command "createpremium"
     *
     * @param sc scanner that reads the input
     * @param yv Youvideo where the video is created
     */
    private static void setCreatePremium(Scanner sc, YouVideo yv) {
        String id = sc.next().trim();
        int duration = sc.nextInt();
        String url = sc.nextLine().trim();
        String publisher = sc.nextLine();
        String title = sc.nextLine();
        String code = sc.nextLine().toLowerCase();
        String subtitleUrl = sc.nextLine();
        String subtitleCode = sc.nextLine().toLowerCase();

        if (yv.isInvalidCode(code))
            System.out.println(INVALID_LANGUAGE_MSG);
        else if (yv.isInvalidCode(subtitleCode))
            System.out.println(INVALID_SUBTITLE_LANGUAGE_TYPE_MSG);
        else if (yv.isInvalidDuration(duration))
            System.out.println(INVALID_VALUE_MSG);
        else if (yv.isExistingVideo(id))
            System.out.println(VIDEO_ID_ALREADY_EXISTS_MSG);
        else {
            yv.createPublishablePremiumVideo
                    (id, duration, url, publisher, title, code, subtitleUrl, subtitleCode);
            System.out.printf(CREATE_PREMIUM_MSG, id);
        }
    }

    /**
     * Sets the command "addsubtitle"
     *
     * @param sc the scanner that reads the input
     * @param yv YouVideo where the video is created
     */
    private static void setAddSubtitle(Scanner sc, YouVideo yv) {
        String id = sc.next().trim();
        String url = sc.nextLine().trim();
        String subCode = sc.nextLine();
        if (yv.isInvalidCode(subCode))
            System.out.println(INVALID_SUBTITLE_LANGUAGE_TYPE_MSG);
        else if (!yv.isExistingVideo(id))
            System.out.println(VIDEO_NON_EXISTENT_MSG);
        else if (!yv.isPremiumVideo(id))
            System.out.println(OP_REQUIRES_PREMIUM_MSG);
        else {
            yv.addSubtitle(id, url, subCode);
            System.out.println(SUBTITLE_ADDED_MSG);
        }
    }

    /**
     * Sets the command "getVideo"
     *
     * @param sc the scanner that reads the input
     * @param yv YouVideo where the video is created
     */
    private static void setGetVideo(Scanner sc, YouVideo yv) {
        String id = sc.next().trim();
        sc.nextLine();

        if (!yv.isExistingVideo(id) || yv.isExistingEpisode(id)) {
            System.out.printf(PUBLISHABLE_VIDEO_NON_EXISTENT_MSG, id);
        } else {
            Video v = yv.getVideo(id);
            if (v instanceof Publishable) {
                Locale loc = Locale.of(((Publishable) v).getCode());
                String languageName = loc.getDisplayLanguage(Locale.UK);
                if (yv.isPremiumVideo(id)) {
                    System.out.printf(GET_PREMIUM_VIDEO_LAYOUT,
                            v.getId(), v.getDuration(), ((Publishable) v).getTitle(),
                            v.getUrl(), ((Publishable) v).getPublisher(), languageName);
                } else {
                    System.out.printf(GET_VIDEO_LAYOUT,
                            v.getId(), v.getDuration(), ((Publishable) v).getTitle(),
                            v.getUrl(), ((Publishable) v).getPublisher(), languageName);
                }
            }
        }
    }

    /**
     * Sets the command "subtitles".
     *
     * @param sc: scanner that reads the input
     * @param yv: YouVideo where the video is created
     */
    public static void setSubtitles(Scanner sc, YouVideo yv) {
        String id = sc.next().trim();
        if (!yv.isPremiumVideo(id))
            System.out.println(NO_PREMIUM_VIDEO_MSG);
        else {
            System.out.printf(LIST_SUBTITLES, ((Publishable) yv.getVideo(id)).getTitle());
            Iterator<Subtitle> it = yv.subtitleIterator(id);
            while (it.hasNext()) {
                Subtitle sub = it.next();
                Locale loc = Locale.of(sub.code());
                System.out.printf(SUBTITLE_LAYOUT_MSG, sub.url(),
                        loc.getDisplayLanguage(Locale.UK).toUpperCase()); // troquei para UK isso resolve o FULAH SUPOSTAMENTE
            }
        }
    }

    /**
     * Sets the command "createpodcast".
     *
     * @param sc: scanner that reads the input
     * @param yv: YouVideo where the video is created
     */
    public static void setCreatePodcast(Scanner sc, YouVideo yv) {
        String title = sc.nextLine().trim();
        String author = sc.nextLine();
        String code = sc.nextLine();
        if (yv.isInvalidCode(code))
            System.out.println(INVALID_LANGUAGE_MSG);
        else if (yv.isExistingPodcast(title))
            System.out.println(PODCAST_ALREADY_EXISTS_MSG);
        else {
            yv.createPodcast(title, author, code);
            System.out.println(PODCATS_CREATED_MSG);
        }
    }

    /**
     * Sets the command "addepisode".
     *
     * @param sc: scanner that reads the input
     * @param yv: YouVideo where the video is created
     */
    public static void setAddEpisode(Scanner sc, YouVideo yv) {
        String podcast = sc.nextLine().trim();
        String id = sc.next();
        int duration = sc.nextInt();
        String url = sc.nextLine().trim();
        String date = sc.nextLine();
        if (yv.isInvalidDuration(duration))
            System.out.println(INVALID_VALUE_MSG);
        else if (!yv.isExistingPodcast(podcast))
            System.out.println(PODCAST_NON_EXISTENT_MSG);
        else if (yv.isExistingEpisode(id) || yv.isExistingVideo(id))
            System.out.println(EPISODE_ID_ALREADY_EXISTS_MSG);
        else if (yv.isDateEarlier(podcast, date))
            System.out.println(EPISODE_DATE_IS_EARLY_MSG);
        else {
            yv.addEpisode(podcast, id, duration, url, date);
            System.out.println(EPISODE_ADDED_MSG);
        }
    }

    /**
     * Sets the command "getpodcast".
     *
     * @param sc: scanner that reads the input
     * @param yv: YouVideo where the video is created
     */
    public static void setGetPodcast(Scanner sc, YouVideo yv) {
        String title = sc.nextLine().trim();
        if (!yv.isExistingPodcast(title))
            System.out.println(PODCAST_NON_EXISTENT_MSG);
        else {
            Podcast pod = yv.getPodcast(title);
            System.out.printf(PODCAST_LAYOUT_MSG, pod.getTitle(), pod.getAuthor(), pod.getCode());
            if (yv.hasEpisodes(title))
                System.out.printf(LATEST_DATE_MSG, pod.getLastEpisode().getDate());
        }
    }

    /**
     * Sets the command "episodes".
     *
     * @param sc: scanner that reads the input
     * @param yv: YouVideo where the video is created
     */
    public static void setEpisodes(Scanner sc, YouVideo yv) {
        String title = sc.nextLine().trim();
        if (!yv.isExistingPodcast(title))
            System.out.println(PODCAST_NON_EXISTENT_MSG);
        else if (!yv.hasEpisodes(title))
            System.out.println(NO_EPISODES_AVAILABLE_MSG);
        else {
            System.out.printf(EPISODES_HEADER_MSG, title);
            Iterator<Episode> it = yv.getPodcastEpisodes(title);
            while (it.hasNext()) {
                Episode ep = it.next();
                System.out.printf(EPISODE_LAYOUT,
                        ep.getId(), ep.getDuration(), ep.getDate(), ep.getUrl());
            }
        }
    }

    /**
     * Sets the command "authorpodcasts".
     *
     * @param sc: scanner that reads the input
     * @param yv: YouVideo where the video is created
     */
    public static void setAuthorPodcasts(Scanner sc, YouVideo yv) {
        String authorName = sc.nextLine().trim();
        if (!yv.hasAuthorPodcasts(authorName))
            System.out.println(NO_PODCASTS_FOR_AUTHOR_MSG);
        else {
            Author author = yv.getAuthor(authorName);
            System.out.printf(AUTHOR_PODCASTS_HEADER_MSG, authorName);
            Iterator<Podcast> it = yv.listAuthorPodcasts(author);
            while (it.hasNext()) {
                Podcast pod = it.next();
                System.out.printf(PODCAST_LAYOUT_MSG,
                        pod.getTitle(), author.getName(), pod.getCode());
            }
        }
    }

    /**
     * Sets the command "removepodcast".
     *
     * @param sc: scanner that reads the input
     * @param yv: YouVideo where the video is created
     */
    public static void setRemovePodcast(Scanner sc, YouVideo yv) {
        String title = sc.nextLine().trim();
        if (!yv.isExistingPodcast(title)) {
            System.out.println(PODCAST_NON_EXISTENT_MSG);
        } else {
            yv.removePodcast(title);
            System.out.println(PODCAST_REMOVED_MSG);
        }
    }

    /**
     * Sets the command "createshow".
     *
     * @param sc: scanner that reads the input
     * @param yv: YouVideo where the video is created
     */
    public static void setCreateShow(Scanner sc, YouVideo yv) {
        String author = sc.nextLine().trim();
        String id = sc.next().trim();
        String date = sc.nextLine().trim();
        if (!yv.isExistingVideo(id) || yv.isExistingEpisode(id)) {
            System.out.println(VIDEO_FOR_SHOW_NON_EXISTENT_MSG);
        } else if (yv.isShow(id)) {
            System.out.println(SHOW_ALREADY_EXISTS_MSG);
        } else {
            yv.createShow(author, id, date);
            System.out.println(SHOW_CREATED_MSG);
        }
    }

    /**
     * Sets the command "getshow".
     *
     * @param sc: scanner that reads the input
     * @param yv: YouVideo where the video is created
     */
    public static void setGetShow(Scanner sc, YouVideo yv) {
        String title = sc.nextLine().trim();
        if (!yv.isExistingShow(title))
            System.out.println(SHOW_NON_EXISTENT_MSG);
        else {
            Show show = yv.getShow(title);
            if (show.video() instanceof Publishable) {
                System.out.printf(SHOW_LAYOUT,
                        show.transmissionDate(),
                        show.authorName(), show.getShowTitle());
            }
        }
    }

    /**
     * Sets the command "removeshow".
     *
     * @param sc: scanner that reads the input
     * @param yv: YouVideo where the video is created
     */
    public static void setRemoveShow(Scanner sc, YouVideo yv) {
        String title = sc.nextLine().trim();
        if (!yv.isExistingShow(title))
            System.out.println(SHOW_NON_EXISTENT_MSG);
        else {
            yv.removeShow(title);
            System.out.println(SHOW_REMOVED_MSG);
        }
    }

    /**
     * Sets the command "removevideo".
     *
     * @param sc: scanner that reads the input
     * @param yv: YouVideo where the video is created
     */
    public static void setRemoveVideo(Scanner sc, YouVideo yv) {
        String id = sc.nextLine().trim();
        if (yv.isExistingEpisode(id))
            System.out.println(VIDEO_IS_EPISODE_MSG);
        else if (!yv.isExistingVideo(id))
            System.out.println(VIDEO_NON_EXISTENT_MSG);
        else if (yv.isShow(id))
            System.out.println(VIDEO_IS_IN_SHOW_MSG);
        else {
            yv.removeVideo(id);
            System.out.println(VIDEO_REMOVED_MSG);
        }
    }

    /**
     * Sets the command "help".
     */
    private static void setHelp(Scanner sc) {
        sc.nextLine();
        System.out.println(HELP_MSG);
    }

    /**
     * Sets the command "exit".
     */
    private static void setExit() {
        System.out.println(BYE_MSG);
    }

    /**
     * Sets any unknown command.
     */
    private static void setDefault() {
        System.out.println(UNKNOWN_COMMAND_MSG);
    }
}