package lt.zapasnikas.carscraper.scraper;

import lt.zapasnikas.carscraper.model.Advertisement;
import lt.zapasnikas.carscraper.model.CarParam;
import lt.zapasnikas.carscraper.model.Seller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public abstract class AbstractScraper implements Scraper {
    private static final Logger LOG = LoggerFactory.getLogger(AbstractScraper.class);
    private static final String IMAGE_DESTINATION_FOLDER = "C:\\Users\\Antanas\\Downloads\\Wrapper-master\\Scraper\\build\\images";
    private String link;

    abstract CarParam scrapParams();

    abstract String scrapId();

    abstract int scrapPrice();

    abstract List<String> getImagesLinks();


    public void downloadImagesFromLinksList(List<String> linksList, String id) {
        int i = 0;
        for (String linkFromList : linksList) {
            String strImageName = i++ + "-" + linkFromList.substring(linkFromList.lastIndexOf('/') + 1);
            LOG.info("Saving: {}, from: {}", strImageName, linkFromList);
            URL urlImage;
            try {
                urlImage = new URL(linkFromList);
            } catch (MalformedURLException e) {
                LOG.error("URL reading error\n {}", e.getStackTrace());
                return;
            }
            try (OutputStream os =
                         new FileOutputStream(IMAGE_DESTINATION_FOLDER + "/" + id + "/" + strImageName);
                 InputStream in = urlImage.openStream()) {


                byte[] buffer = new byte[4096];
                int n;

                File file = new File(IMAGE_DESTINATION_FOLDER + '/' + id);
                file.mkdir();
                while ((n = in.read(buffer)) != -1) {
                    os.write(buffer, 0, n);
                }

                LOG.info("Image saved");

            } catch (IOException e) {
                LOG.error(e.getMessage(), e);
            }
        }
    }

    @Override
    public Seller scrapAdWithNewSeller(String link, Seller seller) {
        this.link = link;

        CarParam carParam = scrapParams();

        Advertisement advertisement = getAdvertisement();
        advertisement.setCarParams(carParam);

        downloadImagesFromLinksList(getImagesLinks(), advertisement.getId());

        seller.addAdvertisement(advertisement);

        return seller;
    }

    @Override
    public Seller scrapAdWithExistingSeller(String link, Seller seller) {
        this.link = link;
        CarParam carParam = scrapParams();

        Advertisement advertisement = getAdvertisement();
        advertisement.setCarParams(carParam);

        List<Advertisement> advertisements = seller.getAdvertisements();
        advertisements.add(advertisement);

        downloadImagesFromLinksList(getImagesLinks(), advertisement.getId());

        seller.setAdvertisements(advertisements);

        return seller;
    }

    @Override
    public Advertisement getAdvertisement() {
        Advertisement advertisement = new Advertisement();
        advertisement.setLink(link);
        advertisement.setPrice(scrapPrice());
        advertisement.setId(scrapId());
        return advertisement;
    }

}
