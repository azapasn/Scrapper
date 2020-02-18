package lt.zapasnikas.carscraper.repository;

import lt.zapasnikas.carscraper.model.Advertisement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdvertisementRepository extends JpaRepository<Advertisement, String> {
    List<Advertisement> findAdvertisementsByCarParam_VinCode(String vinCode);

    List<Advertisement> findAdvertisementsByCarParam_LicencePlate(String licencePlate);
}
