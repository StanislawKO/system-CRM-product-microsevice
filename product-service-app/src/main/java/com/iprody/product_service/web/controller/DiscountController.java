package com.iprody.product_service.web.controller;

import com.iprody.product_service.domain.service.DiscountService;
import com.iprody.product_service.web.dto.DiscountDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/discounts")
public class DiscountController {

    private final DiscountService discountService;

    @GetMapping
    public List<DiscountDto> getAllDiscounts() {
        return discountService.getAllDiscounts();
    }

    @GetMapping("/{id}")
    public Optional<DiscountDto> getDiscountById(
            @PathVariable Long id
    ) {
        return discountService.getDiscountById(id);
    }

    @PostMapping
    public ResponseEntity<DiscountDto> createDiscount(
            @Valid @RequestBody DiscountDto discountDto
    ) {
        return ResponseEntity.ok(discountService.createDiscount(discountDto));
    }

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
