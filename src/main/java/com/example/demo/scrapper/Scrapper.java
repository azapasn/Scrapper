package com.example.demo.scrapper;

import com.example.demo.model.Seller;

public interface Scrapper {
    Seller scrap(String link);
    Seller scrapSeller();
    Seller scrap(String link, Seller seller);
}
