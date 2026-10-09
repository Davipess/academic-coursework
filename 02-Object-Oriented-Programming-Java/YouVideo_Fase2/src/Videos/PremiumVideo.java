package Videos;

/**
 * This interface represents a premium video that can additionally store and array of codes of
 * subtitles
 */
public interface PremiumVideo extends PremiumVideoGetters{

    /**
     * Adds a subtitle to a premium video
     * @param url the subtitle url
     * @param code the subtitle code
     */
    void addSubtitle(String url, String code);
}
