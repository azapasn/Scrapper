package lt.zapasnikas.carscraper.scraper;

import lt.zapasnikas.carscraper.model.Advertisement;
import lt.zapasnikas.carscraper.model.CarParam;
import lt.zapasnikas.carscraper.model.Seller;

import java.io.IOException;
import java.util.List;

public interface Scraper {
    Seller scrapSeller();

    Seller scrapAdvertisement(String link);

    Seller scrapAdvertisement(String link, Seller seller);

    List<String> scrapAdvertisementLinks(String link) throws IOException;

    CarParam scrapParams();

    List<String> getImagesLinks();

    int scrapPrice();

    String scrapId();

    Advertisement getAdvertisement();

}
