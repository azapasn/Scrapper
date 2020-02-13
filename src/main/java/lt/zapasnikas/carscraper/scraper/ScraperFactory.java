package lt.zapasnikas.carscraper.scraper;


public class ScraperFactory {


    private ScraperFactory() {
    }

    public static Scraper getScrapperByLink(String link) {
        if (link.contains("autogidas.lt")) {
            return new AutogidasScraper(link);
        } else if (link.contains("autoplius.lt")) {
            return new AutopliusScraper(link);
        }
        throw new NotSupportedWebException();
    }
}
