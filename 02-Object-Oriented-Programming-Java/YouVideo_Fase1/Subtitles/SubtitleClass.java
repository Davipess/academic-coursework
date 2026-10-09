package Subtitles;

/**
 * This record class implements the Subtitle interface
 * @param url the url of the subtitle
 * @param code the code of the language of the subtitle
 */
public record SubtitleClass(String url, String code) implements Subtitle {
}
