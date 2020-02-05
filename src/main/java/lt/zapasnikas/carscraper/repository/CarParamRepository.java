package lt.zapasnikas.carscraper.repository;

import lt.zapasnikas.carscraper.model.CarParam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarParamRepository extends JpaRepository<CarParam, Long> {
}
