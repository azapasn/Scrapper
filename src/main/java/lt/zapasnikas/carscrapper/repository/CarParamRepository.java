package lt.zapasnikas.carscrapper.repository;

import lt.zapasnikas.carscrapper.model.CarParam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarParamRepository extends JpaRepository<CarParam, Long> {
}
