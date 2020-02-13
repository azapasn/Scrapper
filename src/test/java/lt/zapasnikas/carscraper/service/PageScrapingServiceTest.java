package lt.zapasnikas.carscraper.service;

import com.google.common.truth.Truth;
import lt.zapasnikas.carscraper.model.Seller;
import lt.zapasnikas.carscraper.repository.AdvertisementRepository;
import lt.zapasnikas.carscraper.repository.CarParamRepository;
import lt.zapasnikas.carscraper.repository.SellersRepository;
import lt.zapasnikas.carscraper.scraper.AutogidasScraper;
import lt.zapasnikas.carscraper.scraper.NotSupportedWebException;
import lt.zapasnikas.carscraper.scraper.Scraper;
import lt.zapasnikas.carscraper.scraper.ScraperFactory;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

@PrepareForTest({Jsoup.class, AutogidasScraper.class})
@RunWith(PowerMockRunner.class)
class PageScrapingServiceTest {
    private static final String LINK = "advertisementlink";
    private static final String AUTOGIDAS_LINK = "autogidas.lt";

    @InjectMocks
    private PageScrapingService pageScrapingService;
    @Mock
    private SellersRepository sellersRepository;
    @Mock
    private AdvertisementRepository advertisementRepository;
    @Mock
    private CarParamRepository carParamRepository;
    @Mock
    private Connection connection;
    @Mock
    private Document document;
    @Mock
    private Seller seller;
    @Mock
    private AutogidasScraper autogidasScraper;
    @Mock
    private Scraper scraper;


    public PageScrapingServiceTest() {
    }

    @Test
    public void testGetDataFromSellersRepository() {
        Seller seller1 = new Seller("phoneNumber", "Location");
        Seller seller2 = new Seller("phoneNumber", "Location");
        List<Seller> retList = Arrays.asList(seller1, seller2);
        when(sellersRepository.findAll()).thenReturn(retList);

        List<Seller> result = pageScrapingService.getData();

        Truth.assertThat(result).isEqualTo(retList);
        verify(sellersRepository, times(1)).findAll();
    }

    @Test(expected = NotSupportedWebException.class)
    public void testUnsupportedAdvertisementLink() {
        pageScrapingService.scrapSinglePage(LINK);
    }

    @Test
    public void test() throws IOException {
        Mockito.when(connection.get()).thenReturn(document);
        PowerMockito.mockStatic(Jsoup.class);
        PowerMockito.when(Jsoup.connect(AUTOGIDAS_LINK)).thenReturn(connection);
        PowerMockito.mockStatic(ScraperFactory.class);
        //Mockito.when(scraperFactory.getScrapperByLink(AUTOGIDAS_LINK)).thenReturn(autogidasScraper);
        Mockito.when(autogidasScraper.scrapSeller()).thenReturn(seller);
        pageScrapingService.scrapSinglePage(AUTOGIDAS_LINK);


        System.out.println();
    }

}