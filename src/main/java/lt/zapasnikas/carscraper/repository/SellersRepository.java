package lt.zapasnikas.carscraper.repository;

import lt.zapasnikas.carscraper.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SellersRepository extends JpaRepository<Seller, String> {

    Optional<Seller> findSellerByPhoneNumberContains(String phoneNumber);
}
