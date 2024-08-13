package com.iprody.product_service.utils;

import com.iprody.product_service.domain.model.Discount;

import java.time.Instant;

public class DiscountBuilder {

    public static Discount buildDiscount() {
        return new Discount(
                (short) 1,
                Instant.now(),
                Instant.now(),
                Instant.now(),
                Instant.now());
    }

}
