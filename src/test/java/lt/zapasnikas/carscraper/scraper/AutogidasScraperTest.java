package lt.zapasnikas.carscraper.scraper;

import com.google.common.truth.Truth;
import lt.zapasnikas.carscraper.model.Seller;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.IOException;

@PrepareForTest({Jsoup.class})
@RunWith(PowerMockRunner.class)
public class AutogidasScraperTest {

    AutogidasScraper autogidasScraper;
    @Mock
    private Elements phoneNumberElements;
    @Mock
    private Element phoneNumberElement;
    @Mock
    private Elements sellerLocationElements;
    @Mock
    private Element sellerLocationElement;
    @Mock
    private Connection connection;
    @Mock
    private Document document;


    @Before
    public void prepareDocument() throws IOException {
        Mockito.when(connection.get()).thenReturn(document);
        PowerMockito.mockStatic(Jsoup.class);
        PowerMockito.when(Jsoup.connect("autogidas.lt/LINK")).thenReturn(connection);
        autogidasScraper = new AutogidasScraper("autogidas.lt/LINK");
    }

    @Test
    public void scrapSellerTest() {
        Mockito.when(document.getElementsByClass("seller-ico seller-phones btn-action")).thenReturn(phoneNumberElements);
        Mockito.when(phoneNumberElements.first()).thenReturn(phoneNumberElement);
        Mockito.when(phoneNumberElement.ownText()).thenReturn("112");
        Mockito.when(document.getElementsByClass("seller-ico seller-btn seller-location")).thenReturn(sellerLocationElements);
        Mockito.when(sellerLocationElements.first()).thenReturn(sellerLocationElement);
        Mockito.when(sellerLocationElement.ownText()).thenReturn("Vilnius");
        Seller result = autogidasScraper.scrapSeller();
        Truth.assertThat(result.getPhoneNumber()).isEqualTo("112");
        Truth.assertThat(result.getSellerLocation()).isEqualTo("Vilnius");
    }

    @Test
    public void testWithoutSellerParameters() {
        Mockito.when(document.getElementsByClass("seller-ico seller-phones btn-action")).thenReturn(phoneNumberElements);
        Mockito.when(phoneNumberElements.first()).thenReturn(phoneNumberElement);
        Mockito.when(document.getElementsByClass("seller-ico seller-btn seller-location")).thenReturn(sellerLocationElements);
        Mockito.when(sellerLocationElements.first()).thenReturn(sellerLocationElement);
        Seller result = autogidasScraper.scrapSeller();
        Truth.assertThat(result.getPhoneNumber()).isEqualTo("unknown");
        Truth.assertThat(result.getSellerLocation()).isEqualTo("unknown");
    }

    @Test
    public void scrapAdvertisementLinks() {
    }

    @Test
    public void scrapId() {
    }

    @Test
    public void scrapParams() {
    }

    @Test
    public void getImagesLinks() {
    }

    @Test
    public void scrapPrice() {
    }
}