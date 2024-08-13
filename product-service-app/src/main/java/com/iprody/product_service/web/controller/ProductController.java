package com.iprody.product_service.web.controller;

import com.iprody.product_service.domain.exception.ResourceNotFoundException;
import com.iprody.product_service.domain.service.ProductService;
import com.iprody.product_service.web.dto.ProductDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(this.productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<ProductDto>> getProductById(
            @PathVariable Long id
    ) {
        return ResponseEntity.of(
                Optional.ofNullable(
                        productService.getProductById(id)
                ));
    }

    @PostMapping
    public ResponseEntity<?> createProduct(
            @Valid @RequestBody ProductDto productDto
    ) {
        if (productDto == null) {
            return ResponseEntity.badRequest()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(new ResourceNotFoundException("Error creating product"));
        } else {
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(this.productService.createProduct(productDto));
        }
    }

    @PutMapping("/{id}")
    public void updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody ProductDto productDto
    ) {
        productService.updateProduct(id, productDto);
    }

    @PutMapping("/{id}/active")
    public ResponseEntity<Void>  updateActiveDiscountProduct(
            @PathVariable Long id,
            @Valid @RequestParam boolean active) {
        productService.updateActiveDiscountProduct(id, active);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/price")
    public ResponseEntity<Void>  updatePriceProduct(
            @PathVariable Long id,
            @Valid @RequestParam BigDecimal price) {
        productService.updatePriceProduct(id, price);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}
