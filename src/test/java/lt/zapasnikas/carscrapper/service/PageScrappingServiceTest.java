package lt.zapasnikas.carscrapper.service;

import com.google.common.truth.Truth;
import lt.zapasnikas.carscrapper.model.Seller;
import lt.zapasnikas.carscrapper.repository.AdvertisementRepository;
import lt.zapasnikas.carscrapper.repository.CarParamRepository;
import lt.zapasnikas.carscrapper.repository.SellersRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class PageScrappingServiceTest {
    @InjectMocks
    private PageScrappingService pageScrappingService;
    @Mock
    private SellersRepository sellersRepository;
    @Mock
    private AdvertisementRepository advertisementRepository;
    @Mock
    private CarParamRepository carParamRepository;

    @Test
    public void testGetDataFromSellersRepository() {
        Seller seller1 = new Seller("phoneNumber", "Location");
        Seller seller2 = new Seller("phoneNumber", "Location");
        List<Seller> retList = Arrays.asList(seller1, seller2);
        when(sellersRepository.findAll()).thenReturn(retList);

        List<Seller> result = pageScrappingService.getData();

        Truth.assertThat(result).isEqualTo(retList);
        verify(sellersRepository, times(1)).findAll();
    }
}