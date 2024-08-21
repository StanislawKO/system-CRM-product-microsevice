package com.masya.product_service.web.controller;

import com.masya.product_service.domain.exception.ResourceNotFoundException;
import com.masya.product_service.domain.service.ProductService;
import com.masya.product_service.web.dto.ProductDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public List<ProductDto> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "25") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return productService.getAllProducts(pageable).getContent();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(
            @PathVariable Long id
    ) {
        return productService.getProductById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Product not found with id " + id)
                );
    }

    @PostMapping
    public ResponseEntity<?> createProduct(
            @Valid @RequestBody ProductDto productDto
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(this.productService.createProduct(productDto));
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public void updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody ProductDto productDto
    ) {
        productService.updateProduct(id, productDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}/active")
    public void updateActiveDiscountProduct(
            @PathVariable Long id,
            @Valid @RequestParam boolean active) {
        productService.updateActiveDiscountProduct(id, active);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}/price")
    public void updatePriceProduct(
            @PathVariable Long id,
            @Valid @RequestParam BigDecimal price) {
        productService.updatePriceProduct(id, price);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}
