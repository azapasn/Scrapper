package lt.zapasnikas.carscraper.scraper;

import lt.zapasnikas.carscraper.model.CarParam;
import lt.zapasnikas.carscraper.model.Seller;
import org.jsoup.Jsoup;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;




public class AutogidasScraper extends AbstractScraper {
    private static final Logger LOG = LoggerFactory.getLogger(AutogidasScraper.class);
    private static final String PAGELINK = "?page=";

    private Document doc;
    String link;

    public AutogidasScraper() {
    }

    public AutogidasScraper(String link) {
        this.link = link;
        try {
            doc = Jsoup.connect(link).get();
        } catch (IOException e) {
            LOG.error("Couldn't get a {} page with jsoup\n {}", link, Arrays.toString(e.getStackTrace()));
            throw new RuntimeException();
        }
    }

    @Override
    public Seller scrapSeller() {
        Element phoneNumberElement = doc.getElementsByClass("seller-ico seller-phones btn-action").first();
        Element sellerLocationElement = doc.getElementsByClass("seller-ico seller-btn seller-location").first();
        String phoneNumber;
        String sellerLocation;
        try {
            phoneNumber = phoneNumberElement.ownText()
                    .replace(" ", "")
                    .replace("+370", "_");

            sellerLocation = sellerLocationElement.ownText();

        } catch (NullPointerException e) {
            phoneNumber = "unknown";
            sellerLocation = "unknown";
        }
        return new Seller(phoneNumber, sellerLocation);
    }

    @Override
    public List<String> scrapAdvertisementLinks(String link) throws IOException {
        List<String> advertisementLinks = new ArrayList<>();
        int pageCount = 1;
        String nextPageLink = link;
        do {
            doc = Jsoup.connect(nextPageLink).get();
            nextPageLink = null;
            if (nextPageHasAdvertisements(link, pageCount++)) {
                nextPageLink = link + PAGELINK + pageCount;
            }
            advertisementLinks.addAll(scrapAdvertisementLinksOnThePage());
        } while (nextPageLink != null && pageCount < 1);
        return advertisementLinks;
    }

    private boolean nextPageHasAdvertisements(String link, int i) throws IOException {
        Document tempDoc = Jsoup.connect(link + PAGELINK + i).get();
        return !tempDoc.getElementsByClass("list-item").isEmpty();
    }

    private List<String> scrapAdvertisementLinksOnThePage() {
        List<String> linksToScrap = new ArrayList<>();
        Elements tempElements = doc.getElementsByClass("list-item");
        for (Element tempElement : tempElements) {
            linksToScrap.add("https://autogidas.lt/skelbimas" + tempElement.getElementsByAttribute("href").attr("href"));
        }
        return linksToScrap;
    }

    String scrapId() {
        return doc.getElementsByClass("times-item-right").last().ownText();
    }

    CarParam scrapParams() {
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
                    carParam.setYearsProd(paramValue);
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
        }
        return carParam;
    }

    List<String> getImagesLinks() {
        List<String> imagesLinks = new ArrayList<>();
        for (Element element : doc.getElementsByTag("script")) {
            for (DataNode dataNode : element.dataNodes()) {
                if (dataNode.getWholeData().contains("gallery.addImage")) {
                    for (String line : dataNode.getWholeData().split("gallery.addImage")) {
                        if (line.contains("https://img.autogidas.lt")) {
                            imagesLinks.add(line.split(",")[0].replace("'", "").replace("(", ""));
                        }

                    }
                }
            }
        }

        return imagesLinks;
    }

    int scrapPrice() {
        String priceString = doc.getElementsByClass("price").first().ownText();
        return Integer.parseInt(priceString
                .replace("€", "")
                .replace(" ", ""));
    }

}
