package lt.zapasnikas.carscrapper.repository;

import lt.zapasnikas.carscrapper.model.Advertisement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvertisementRepository extends JpaRepository<Advertisement, String> {
}
