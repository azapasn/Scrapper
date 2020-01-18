package lt.zapasnikas.carscrapper.repository;

import lt.zapasnikas.carscrapper.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellersRepository extends JpaRepository<Seller, String> {
}
