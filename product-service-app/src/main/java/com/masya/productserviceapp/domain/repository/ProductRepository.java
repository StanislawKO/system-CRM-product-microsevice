package com.masya.productserviceapp.domain.repository;

import com.masya.productserviceapp.domain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {


}
