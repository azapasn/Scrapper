package lt.zapasnikas.carscraper.scraper;

import lt.zapasnikas.carscraper.model.Advertisement;
import lt.zapasnikas.carscraper.model.Seller;

import java.io.IOException;
import java.util.List;

public interface Scraper {
    Seller scrapSeller();

    Seller scrapAdWithNewSeller(String link, Seller seller);

    Seller scrapAdWithExistingSeller(String link, Seller seller);

    List<String> scrapAdvertisementLinks(String link) throws IOException;

    Advertisement getAdvertisement();

}
