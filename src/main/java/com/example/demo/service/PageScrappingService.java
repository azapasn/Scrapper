package com.example.demo.service;

import com.example.demo.model.Advertisement;
import com.example.demo.model.CarParam;
import com.example.demo.model.Seller;
import com.example.demo.repository.AdvertisementRepository;
import com.example.demo.repository.CarParamRepository;
import com.example.demo.repository.SellersRepository;
import com.example.demo.srapper.AutogidasScrapper;
import com.example.demo.srapper.Scrapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class PageScrappingService {
    private SellersRepository sellersRepository;
    private AdvertisementRepository advertisementRepository;
    private CarParamRepository carParamRepository;

    public PageScrappingService(SellersRepository sellersRepository, AdvertisementRepository advertisementRepository, CarParamRepository carParamRepository) {
        this.sellersRepository = sellersRepository;
        this.advertisementRepository = advertisementRepository;
        this.carParamRepository = carParamRepository;
    }

    public Seller scrap(String link) throws IOException {
        Seller sellerToSave;
        Scrapper scrapper = new AutogidasScrapper(link);
        Seller seller = scrapper.scrapSeller();
        Optional<Seller> sellerFromDatabase = sellersRepository.findById(seller.getPhoneNumber());
        if (sellerFromDatabase.isPresent() && sellerFromDatabase.get().getPhoneNumber().equals(seller.getPhoneNumber())) {
            sellerToSave = scrapper.scrap(link, sellerFromDatabase.get());
        } else {
            sellerToSave = scrapper.scrap(link);
        }
        return saveAll(sellerToSave);
    }
    private Seller saveAll(Seller seller){

        List<Advertisement> advertisementToSave = seller.getAdvertisements();
        for (Advertisement advertisement: advertisementToSave) {
            advertisement.setSeller(seller);
            List<CarParam> carParams = advertisement.getCarParams();
            for (CarParam carParam : carParams) {
                 carParam.setAdvertisement(advertisement);
            }
        }
        return sellersRepository.save(seller);
    }
    public List<Seller> getData(){
        List<Seller> sellers = sellersRepository.findAll();
        return sellers;
    }
}
