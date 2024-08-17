package com.masya.product_service.domain.repository;

import com.masya.product_service.domain.model.Discount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountRepository extends JpaRepository<Discount, Long> {
}
