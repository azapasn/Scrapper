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

public class AutopliusScraper extends AbstractScraper {
    private static final Logger LOG = LoggerFactory.getLogger(AutopliusScraper.class);
    private static final String PAGELINK = "?page_nr=";

    Document doc;
    String link;

    public AutopliusScraper(String link) {
        this.link = link;
        try {
            doc = Jsoup.connect(link).get();
        } catch (IOException e) {
            LOG.error("Couldn't get a {} page with jsoup\n{}", link, Arrays.toString(e.getStackTrace()));
            throw new RuntimeException();
        }
    }



    public Seller scrapSeller() {
        Element phoneNumberElement = doc.getElementsByClass("owner-phone").first();
        Element sellerLocationElement = doc.getElementsByClass("owner-location").first();
        String phoneNumber;
        String sellerLocation;
        try {
            phoneNumber = phoneNumberElement.text()
                    .replace(" ", "");
            sellerLocation = sellerLocationElement.text();

        } catch (NullPointerException e) {
            phoneNumber = "unknown";
            sellerLocation = "unknown";
        }
        return new Seller(phoneNumber, sellerLocation);
    }

    @Override
    public List<String> scrapAdvertisementLinks(String link) throws IOException {
        List<String> advertisementLinks = new ArrayList<>();
        int pageCount = getPagesCount(link) / 20 + 1;
        for (int currentPage = 1; currentPage <= pageCount && currentPage < 2; currentPage++) {
            String nextPageLink = link + PAGELINK + currentPage;
            doc = Jsoup.connect(nextPageLink).get();
            advertisementLinks.addAll(scrapAdvertisementLinksOnThePage());
        }

        return advertisementLinks;
    }

    private int getPagesCount(String link) throws IOException {
        Document tempDoc = Jsoup.connect(link).get();
        return Integer.parseInt(tempDoc.getElementsByClass("result-count").text()
                .replace("(", "")
                .replace(")", ""));
    }


    private List<String> scrapAdvertisementLinksOnThePage() {
        List<String> linksToScrap = new ArrayList<>();
        Elements tempElements = doc.getElementsByClass("announcement-item");
        for (Element tempElement : tempElements) {
            linksToScrap.add(tempElement.getElementsByAttribute("href").attr("href"));
        }
        return linksToScrap;
    }


    public String scrapId() {
        return doc.getElementsByClass("announcement-id").last().text()
                .replace("ID: ", "");
    }

    public int scrapPrice() {
        String priceString = doc.getElementsByClass("price").first().ownText();
        return Integer.parseInt(priceString
                .replace(" ", ""));
    }

    public CarParam scrapParams() {
        CarParam carParams = new CarParam();
        Elements paramsElements = doc.getElementsByClass("parameter-row");
        String[] tempTitle = doc.title().split(",")[0].split(" ");
        carParams.setMake(tempTitle[0]);
        carParams.setModel(tempTitle[1]);
        for (Element element : paramsElements) {
            String paramName;
            String paramValue;
            try {
                paramName = element.getElementsByClass("parameter-label").first().ownText();
                paramValue = element.getElementsByClass("parameter-value").first().ownText();

            } catch (NullPointerException e) {
                paramName = "ERROR";
                paramValue = "ERROR";
            }
            switch (paramName) {
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
                case "Varantieji ratai":
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

    public List<String> getImagesLinks() {
        List<String> imagesLinks = new ArrayList<>();
        int i = 0;
        for (Element element : doc.getElementsByTag("script")) {
            for (DataNode dataNode : element.dataNodes()) {
                if (dataNode.getWholeData().contains("mediaGalleryItems")) {
                    for (String line : dataNode.getWholeData().split("\"")) {
                        if (line.contains("autoplius-img.dgn")) {
                            if (i % 2 == 0)
                                imagesLinks.add(line.replace("\\", ""));
                            i++;
                        }
                    }
                }
            }
        }

        return imagesLinks;
    }
}
