package lt.zapasnikas.carscrapper.service;

import lt.zapasnikas.carscrapper.model.Advertisement;
import lt.zapasnikas.carscrapper.model.CarParam;
import lt.zapasnikas.carscrapper.model.Seller;
import lt.zapasnikas.carscrapper.repository.AdvertisementRepository;
import lt.zapasnikas.carscrapper.repository.CarParamRepository;
import lt.zapasnikas.carscrapper.repository.SellersRepository;
import lt.zapasnikas.carscrapper.scrapper.Scrapper;
import lt.zapasnikas.carscrapper.scrapper.ScrapperFactory;
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
        Scrapper scrapper = ScrapperFactory.getScrapperByLink(link);
        Seller seller = scrapper.scrapSeller();
        Optional<Seller> sellerFromDatabase = sellersRepository.findById(seller.getPhoneNumber());
        if (sellerFromDatabase.isPresent() && sellerFromDatabase.get().getPhoneNumber().equals(seller.getPhoneNumber())) {
            sellerToSave = scrapper.scrap(link, sellerFromDatabase.get());
        } else {
            sellerToSave = scrapper.scrap(link);
        }
        return sellerToSave;
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
        return sellersRepository.findAll();
    }
}
