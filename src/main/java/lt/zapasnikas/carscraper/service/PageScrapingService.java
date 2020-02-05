package lt.zapasnikas.carscraper.service;

import lt.zapasnikas.carscraper.model.Advertisement;
import lt.zapasnikas.carscraper.model.CarParam;
import lt.zapasnikas.carscraper.model.Seller;
import lt.zapasnikas.carscraper.repository.AdvertisementRepository;
import lt.zapasnikas.carscraper.repository.CarParamRepository;
import lt.zapasnikas.carscraper.repository.SellersRepository;
import lt.zapasnikas.carscraper.scraper.Scraper;
import lt.zapasnikas.carscraper.scraper.ScraperFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PageScrapingService {
    private SellersRepository sellersRepository;
    private AdvertisementRepository advertisementRepository;
    private CarParamRepository carParamRepository;

    public PageScrapingService(SellersRepository sellersRepository, AdvertisementRepository advertisementRepository, CarParamRepository carParamRepository) {
        this.sellersRepository = sellersRepository;
        this.advertisementRepository = advertisementRepository;
        this.carParamRepository = carParamRepository;
    }

    public void scrapAllAdvertisementsBySearchLink(String link) throws IOException {
        Scraper scraper = ScraperFactory.getScrapperByLink(link);
        List<String> links = scraper.scrapAdvertisementLinks(link);
        links.stream()
                .parallel()
                .map(this::scrapSinglePage)
                .filter(this::advertisementMissingInDb)
                .collect(Collectors.toList())
                .forEach(this::saveAll);
    }


    private boolean advertisementMissingInDb(Seller seller) {
        if (seller.getAdvertisements().isEmpty()) {
            return true;
        }
        Advertisement advertisement = seller.getAdvertisements().get(0);
        Optional<Advertisement> byId = advertisementRepository.findById(advertisement.getId());
        return !byId.isPresent();
    }

    public Seller scrapSinglePage(String link) {
        Seller sellerToSave;
        Scraper scraper = ScraperFactory.getScrapperByLink(link);
        Seller seller = scraper.scrapSeller();
        Optional<Seller> sellerFromDatabase = sellersRepository.findById(seller.getPhoneNumber());
        if (sellerFromDatabase.isPresent() && sellerFromDatabase.get().getPhoneNumber().equals(seller.getPhoneNumber())) {
            sellerToSave = scraper.scrapAdvertisement(link, sellerFromDatabase.get());
        } else {
            sellerToSave = scraper.scrapAdvertisement(link);
        }
        return sellerToSave;
    }

    private Seller saveAll(Seller seller){

        List<Advertisement> advertisementToSave = seller.getAdvertisements();
        for (Advertisement advertisement: advertisementToSave) {
            advertisement.setSeller(seller);
            CarParam carParam = advertisement.getCarParam();
            carParam.setAdvertisement(advertisement);
        }
        return sellersRepository.save(seller);
    }

    public List<Seller> getData(){
        return sellersRepository.findAll();
    }
}
