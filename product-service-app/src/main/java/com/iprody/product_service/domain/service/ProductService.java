package com.iprody.product_service.domain.service;

import com.iprody.product_service.web.dto.ProductDto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<ProductDto> getAllProducts();

    ProductDto createProduct(ProductDto productDto);

    void updatePriceProduct(Long id, BigDecimal price);

    void updateProduct(Long id, ProductDto productDto);

    void updateActiveDiscountProduct(Long id, boolean active);

    Optional<ProductDto> getProductById(Long id);

    void deleteProduct(Long id);

}
