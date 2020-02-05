package lt.zapasnikas.carscraper.scraper;


public class ScraperFactory {
    public static Scraper getScrapperByLink(String link) {
        if (link.contains("autogidas")) {
            return new AutogidasScraper(link);
        } else if (link.contains("autoplius")) {
            return new AutopliusScraper(link);
        }
        throw new NotSupportedWebException();
    }
}
