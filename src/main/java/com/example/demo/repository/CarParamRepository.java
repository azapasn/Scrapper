package com.example.demo.repository;

import com.example.demo.model.CarParam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarParamRepository extends JpaRepository<CarParam, Long> {
}
