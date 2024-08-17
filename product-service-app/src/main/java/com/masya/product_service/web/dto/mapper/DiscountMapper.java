package com.masya.product_service.web.dto.mapper;

import com.masya.product_service.domain.model.Discount;
import com.masya.product_service.web.dto.DiscountDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DiscountMapper extends Mappable<Discount, DiscountDto> {

    @Override
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Discount toEntity(DiscountDto dto);

}
