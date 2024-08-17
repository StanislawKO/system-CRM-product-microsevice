package com.masya.product_service.web.controller;

import com.masya.product_service.domain.exception.ResourceNotFoundException;
import com.masya.product_service.domain.service.DiscountService;
import com.masya.product_service.web.dto.DiscountDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/discounts")
public class DiscountController {

    private final DiscountService discountService;

    @GetMapping
    public ResponseEntity<List<DiscountDto>> getAllDiscounts() {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(this.discountService.getAllDiscounts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DiscountDto> getDiscountById(
            @PathVariable Long id
    ) {
        return discountService.getDiscountById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Discount not found with id " + id)
                );
    }

    @PostMapping
    public ResponseEntity<?> createDiscount(
            @Valid @RequestBody DiscountDto discountDto
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(this.discountService.createDiscount(discountDto));
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public void updateDiscount(
            @PathVariable Long id,
            @Valid @RequestBody DiscountDto discountDto) {
        discountService.updateDiscount(id, discountDto);
    }

    @DeleteMapping("/{id}")
    public void deleteDiscount(@PathVariable Long id) {
        discountService.deleteDiscount(id);
    }
}
