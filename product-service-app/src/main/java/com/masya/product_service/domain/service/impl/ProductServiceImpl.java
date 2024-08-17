package com.masya.product_service.domain.service.impl;

import com.masya.product_service.domain.exception.ResourceNotFoundException;
import com.masya.product_service.domain.model.Product;
import com.masya.product_service.domain.repository.ProductRepository;
import com.masya.product_service.domain.service.ProductService;
import com.masya.product_service.web.dto.ProductDto;
import com.masya.product_service.web.dto.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    @Transactional
    @Override
    public Page<ProductDto> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable)
                .map(productMapper::toDto);
    }

    @Transactional
    @Override
    public Optional<ProductDto> getProductById(Long id) {
        return productRepository.findById(id)
                .map(productMapper::toDto);
    }

    @Transactional
    @Override
    public ProductDto createProduct(ProductDto productDto) {
        return Optional.ofNullable(productDto)
                .map(productMapper::toEntity)
                .map(productRepository::saveAndFlush)
                .map(productMapper::toDto)
                .orElseThrow(() -> new IllegalStateException("Error save product"));
    }


    @Transactional
    @Override
    public void updateActiveDiscountProduct(Long id, boolean active) {
        productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        productRepository.updateActiveDiscountById(id, active);
    }

    @Transactional
    @Override
    public void updateProduct(Long id, ProductDto productDto) {
        Product productUpdate = productMapper.toEntity(productDto);
        Product product = productRepository.findById(id).orElseThrow();
        product.setDuration(productUpdate.getDuration());
        product.setDescription(productUpdate.getDescription());
        product.setPrice(productUpdate.getPrice());
        product.setActive(productUpdate.isActive());
        product.setSummary(productUpdate.getSummary());
        product.setDiscount(productUpdate.getDiscount());
        productRepository.save(product);
    }

    @Transactional
    @Override
    public void updatePriceProduct(Long id, BigDecimal price) {
        productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with id=" + id + " not found"));
        productRepository.updatePriceById(id, price);
    }

    @Transactional
    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

}
