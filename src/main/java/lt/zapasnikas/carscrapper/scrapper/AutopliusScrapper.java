package lt.zapasnikas.carscrapper.scrapper;

import lt.zapasnikas.carscrapper.model.Advertisement;
import lt.zapasnikas.carscrapper.model.CarParam;
import lt.zapasnikas.carscrapper.model.Seller;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AutopliusScrapper implements Scrapper {
    private final static Logger LOG = LoggerFactory.getLogger(AutopliusScrapper.class);
    Document doc;
    String link;

    public AutopliusScrapper(String link) {
        this.link = link;
        try {
            doc = Jsoup.connect(link).get();
        } catch (IOException e) {
            LOG.error("Couldn't get a {} page with jsoup\n" + Arrays.toString(e.getStackTrace()), link);
            throw new RuntimeException();
        }
    }

    @Override
    public Seller scrapAdvertisement(String link) {
        Seller seller = scrapSeller();

        CarParam carParams = scrapParams();

        Advertisement advertisement = getAdvertisement();
        advertisement.setCarParams(carParams);

        List<Advertisement> advertisements = new ArrayList<>();
        advertisements.add(advertisement);

        seller.setAdvertisements(advertisements);

        return seller;
    }

    @Override
    public Seller scrapAdvertisement(String link, Seller seller) {
        CarParam carParams = scrapParams();

        Advertisement advertisement = getAdvertisement();
        advertisement.setCarParams(carParams);

        List<Advertisement> advertisements = seller.getAdvertisements();
        advertisements.add(advertisement);

        seller.setAdvertisements(advertisements);

        return seller;
    }

    public Seller scrapSeller() {
        Element phoneNumberElement = doc.getElementsByClass("owner-phone").first();
        Element sellerLocationElement = doc.getElementsByClass("owner-location").first();
        String phoneNumber = phoneNumberElement.ownText();
        String sellerLocation = sellerLocationElement.ownText();
        return new Seller(phoneNumber, sellerLocation);
    }

    @Override
    public List<String> scrapAdvertisementLinks(String link) throws IOException {
        List<String> advertisementLinks = new ArrayList<>();
        String nextPageLink = link;
        do {
            doc = Jsoup.connect(nextPageLink).get();
            nextPageLink = getLinkToNextAdvertismentPage();
            advertisementLinks.addAll(scrapAdvertisementLinksOnThePage());
        } while (nextPageLink != null);

        return advertisementLinks;
    }

    private String getLinkToNextAdvertismentPage() {
        //get next page link else return null
        return null;
    }

    private List<String> scrapAdvertisementLinksOnThePage() {
        //get all ad links
        return null;
    }

    private Advertisement getAdvertisement() {
        Advertisement advertisement = new Advertisement();
        advertisement.setLink(link);
        advertisement.setPrice(scrapPrice());
        advertisement.setId(scrapId());
        return advertisement;
    }

    private String scrapId() {
        return doc.getElementsByClass("announcement-id").last().ownText()
                .replace("ID: ", "");
    }

    private int scrapPrice() {
        String priceString = doc.getElementsByClass("price").first().ownText();
        return Integer.parseInt(priceString
                .replace(" ", ""));
    }

    private CarParam scrapParams() {
        CarParam carParams = new CarParam();
        Elements paramsElements = doc.getElementsByClass("parameter-row");
        for (Element element : paramsElements) {
            if (element.className().contains("carvertical")) {
                break;
            }
            String paramName = element.getElementsByClass("parameter-label").first().ownText();
            String paramValue = element.getElementsByClass("parameter-value").first().ownText();
            switch (paramName) {
                case "Markė":
                    carParams.setMake(paramValue);
                    break;
                case "Modelis":
                    carParams.setModel(paramValue);
                    break;
                case "Pagaminimo data":
                    carParams.setYears(paramValue);
                    break;
                case "Variklis":
                    carParams.setEngine(paramValue);
                    break;
                case "Kuro tipas":
                    carParams.setFuelType(paramValue);
                    break;
                case "Spalva":
                    carParams.setColor(paramValue);
                    break;
                case "Rida":
                    carParams.setMileageKm(paramValue);
                    break;
                case "Varomieji ratai":
                    carParams.setDriveTrain(paramValue);
                    break;
                case "Defektai":
                    carParams.setDefects(paramValue);
                    break;
                case "VIN kodas":
                    carParams.setVinCode(paramValue);
                    break;
                case "Pirmosios registracijos šalis":
                    carParams.setFirstRegistrationCountry(paramValue);
                    break;
                default:
                    break;
            }
        }
        return carParams;
    }

}
