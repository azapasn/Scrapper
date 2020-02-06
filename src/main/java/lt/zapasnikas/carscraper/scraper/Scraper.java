package lt.zapasnikas.carscraper.scraper;

import lt.zapasnikas.carscraper.model.Seller;

import java.io.*;
import java.net.URL;
import java.util.List;

public interface Scraper {
    String IMAGE_DESTINATION_FOLDER = "C:\\Users\\Antanas\\Downloads\\Wrapper-master\\Scraper\\build\\images";

    Seller scrapSeller();

    Seller scrapAdvertisement(String link);

    Seller scrapAdvertisement(String link, Seller seller);

    List<String> scrapAdvertisementLinks(String link) throws IOException;

    default void downloadImagesFromLinksList(List<String> linksList, String id) {
        int i = 0;
        for (String link : linksList) {

            String strImageName = i++ + "-" + link.substring(link.lastIndexOf("/") + 1);
            System.out.println("Saving: " + strImageName + ", from: " + link);
            try {
                URL urlImage = new URL(link);
                InputStream in = urlImage.openStream();
                byte[] buffer = new byte[4096];
                int n;

                File file = new File(IMAGE_DESTINATION_FOLDER + "/" + id + "/");
                file.mkdir();
                OutputStream os = new FileOutputStream(IMAGE_DESTINATION_FOLDER + "/" + id + "/" + strImageName);

                while ((n = in.read(buffer)) != -1) {
                    os.write(buffer, 0, n);
                }

                os.close();

                System.out.println("Image saved");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
