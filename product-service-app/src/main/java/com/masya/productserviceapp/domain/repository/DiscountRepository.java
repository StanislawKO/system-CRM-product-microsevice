package com.masya.productserviceapp.domain.repository;

import com.masya.productserviceapp.domain.model.Discount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountRepository extends JpaRepository<Discount, Long> {
}
