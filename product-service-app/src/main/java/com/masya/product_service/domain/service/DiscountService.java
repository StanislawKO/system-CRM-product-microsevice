package com.masya.product_service.domain.service;


import com.masya.product_service.web.dto.DiscountDto;

import java.util.List;
import java.util.Optional;

public interface DiscountService {

    List<DiscountDto> getAllDiscounts();

    DiscountDto createDiscount(DiscountDto discountDto);

    void updateDiscount(Long id, DiscountDto discountDto);

    Optional<DiscountDto> getDiscountById(Long id);

    void deleteDiscount(Long id);

}
