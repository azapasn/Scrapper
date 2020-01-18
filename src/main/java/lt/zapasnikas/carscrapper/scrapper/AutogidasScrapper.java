package lt.zapasnikas.carscrapper.scrapper;

import lt.zapasnikas.carscrapper.model.Advertisement;
import lt.zapasnikas.carscrapper.model.CarParam;
import lt.zapasnikas.carscrapper.model.Seller;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AutogidasScrapper implements Scrapper {
    Document doc;
    String link;

    public AutogidasScrapper(String link) throws IOException {
        this.link = link;
        doc = Jsoup.connect(link).get();
    }

    @Override
    public Seller scrap(String link) {
        Seller seller = scrapSeller();

        List<CarParam> carParams = scrapParams();

        Advertisement advertisement = getAdvertisement();
        advertisement.setCarParams(carParams);

        List<Advertisement> advertisements = new ArrayList<>();
        advertisements.add(advertisement);

        seller.setAdvertisements(advertisements);

        return seller;
    }

    @Override
    public Seller scrap(String link, Seller seller) {
        List<CarParam> carParams = scrapParams();

        Advertisement advertisement = getAdvertisement();
        advertisement.setCarParams(carParams);

        List<Advertisement> advertisements = seller.getAdvertisements();
        advertisements.add(advertisement);

        seller.setAdvertisements(advertisements);

        return seller;
    }

    public Seller scrapSeller() {
        Element phoneNumberElement = doc.getElementsByClass("seller-ico seller-phones btn-action").first();
        Element sellerLocationElement = doc.getElementsByClass("seller-ico seller-btn seller-location").first();
        String phoneNumber = phoneNumberElement.ownText();
        String sellerLocation = sellerLocationElement.ownText();
        return new Seller(phoneNumber, sellerLocation);
    }

    private Advertisement getAdvertisement() {
        Advertisement advertisement = new Advertisement();
        advertisement.setLink(link);
        advertisement.setPrice(scrapPrice());
        return advertisement;
    }

    private List<CarParam> scrapParams() {
        List<CarParam> carParams = new ArrayList<>();
        Element paramBlockElement = doc.getElementsByClass("params-block").last();
        Elements paramsElements = paramBlockElement.getElementsByClass("param");
        for (Element element: paramsElements) {
            String paramName = element.getElementsByClass("left").first().ownText();
            String paramValue = element.getElementsByClass("right").first().ownText();
            CarParam carParam = new CarParam(paramName, paramValue);
            carParams.add(carParam);
        }
        return carParams;
    }
    private int scrapPrice(){
        String priceString = doc.getElementsByClass("price").first().ownText();
        return Integer.parseInt(priceString
                .replace("€", "")
                .replace(" ", ""));
    }
}
