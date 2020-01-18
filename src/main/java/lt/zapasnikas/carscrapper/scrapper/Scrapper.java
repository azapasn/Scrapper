package lt.zapasnikas.carscrapper.scrapper;

import lt.zapasnikas.carscrapper.model.Seller;

public interface Scrapper {
    Seller scrap(String link);
    Seller scrapSeller();
    Seller scrap(String link, Seller seller);
}
