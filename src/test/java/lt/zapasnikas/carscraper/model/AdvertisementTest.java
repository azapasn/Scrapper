package lt.zapasnikas.carscraper.model;

import com.google.common.truth.Truth;
import org.junit.Before;
import org.junit.Test;

public class AdvertisementTest {
    Advertisement advertisement;
    CarParam carParam = new CarParam();
    Seller seller = new Seller();

    @Before
    public void prepareAdvertisement() {
        carParam.setId(1);
        seller.setPhoneNumber("1");
        advertisement = new Advertisement();
        advertisement.setCarParams(carParam);
        advertisement.setId("id");
        advertisement.setLink("link");
        advertisement.setPrice(1);
        advertisement.setSeller(seller);
    }

    @Test
    public void setterAndGetterTest() {
        Truth.assertThat(advertisement.getId()).isEqualTo("id");
        Truth.assertThat(advertisement.getCarParam()).isEqualTo(carParam);
        Truth.assertThat(advertisement.getLink()).isEqualTo("link");
        Truth.assertThat(advertisement.getPrice()).isEqualTo(1);
        Truth.assertThat(advertisement.getSeller()).isEqualTo(seller);
    }

    @Test
    public void setterFailTest() {
        Truth.assertThat(advertisement.getId()).isNotEqualTo("newId");
        Truth.assertThat(advertisement.getCarParam()).isNotEqualTo(new CarParam());
        Truth.assertThat(advertisement.getLink()).isNotEqualTo("newLink");
        Truth.assertThat(advertisement.getPrice()).isNotEqualTo(0);
        Truth.assertThat(advertisement.getSeller()).isNotEqualTo(new Seller());
    }
}