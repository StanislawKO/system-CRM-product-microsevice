package com.masya.productserviceapp.domain.repository;

import com.masya.productserviceapp.domain.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRepository extends JpaRepository<Price, Long> {
}
