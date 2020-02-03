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
        advertisement.setId(scrapId());
        return advertisement;
    }

    private String scrapId() {
        return doc.getElementsByClass("times-item-right").last().ownText();
    }

    private List<CarParam> scrapParams() {
        List<CarParam> carParams = new ArrayList<>();
        Element paramBlockElement = doc.getElementsByClass("params-block").last();
        Elements paramsElements = paramBlockElement.getElementsByClass("param");
        CarParam carParam = new CarParam();
        for (Element element : paramsElements) {
            String paramName = element.getElementsByClass("left").first().ownText();
            String paramValue = element.getElementsByClass("right").first().ownText();
            switch (paramName) {
                case "Markė":
                    carParam.setMake(paramValue);
                    break;
                case "Modelis":
                    carParam.setModel(paramValue);
                    break;
                case "Metai":
                    carParam.setYears(paramValue);
                    break;
                case "Variklis":
                    carParam.setEngine(paramValue);
                    break;
                case "Kuro tipas":
                    carParam.setFuelType(paramValue);
                    break;
                case "Spalva":
                    carParam.setColor(paramValue);
                    break;
                case "Rida, km":
                    carParam.setMileageKm(paramValue);
                    break;
                case "Varomieji ratai":
                    carParam.setDriveTrain(paramValue);
                    break;
                case "Defektai":
                    carParam.setDefects(paramValue);
                    break;
                case "VIN kodas":
                    carParam.setVinCode(paramValue);
                    break;
                case "Pirmosios registracijos šalis":
                    carParam.setFirstRegistrationCountry(paramValue);
                    break;
                default:
                    break;
            }
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
