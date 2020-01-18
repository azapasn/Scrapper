package lt.zapasnikas.carscrapper.scrapper;


import java.io.IOException;

public class ScrapperFactory {
    public static Scrapper getScrapperByLink(String link) throws IOException {
        if (link.contains("autogidas")){
            return new AutogidasScrapper(link);
        }
        else if(link.contains("autoplius")){
            throw new NotSupportedWebException();
        }
        throw new NotSupportedWebException();
    }
}
