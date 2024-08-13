package com.iprody.product_service.service;

import com.iprody.product_service.domain.model.Discount;
import com.iprody.product_service.domain.repository.DiscountRepository;
import com.iprody.product_service.domain.service.ProductService;
import com.iprody.product_service.utils.DiscountBuilder;
import com.iprody.product_service.web.dto.ProductDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
@Transactional
public class ProductServiceTest {

    @Autowired
    private ProductService productService;
    @Autowired
    private DiscountRepository discountRepository;

    static Discount discount;

    @BeforeAll
    public void setUpBeforeClass() {
        discount = discountRepository
                .save(DiscountBuilder.buildDiscount());
    }

    @Test
    public void createProduct_ReturnNotNull() {

        // given
        ProductDto productDto = getProductDto();

        // when
        ProductDto productSaved = productService.createProduct(productDto);

        // then
        assertThat(productSaved).isNotNull();
    }

    @Test
    public void updateProduct_ReturnNotNull() {

        // given
        ProductDto productDto = getProductDto();
        ProductDto productDtoUpdated = productService.createProduct(productDto);

        // when
        productDto.setDuration((short) 10);
        productService.updateProduct(productDtoUpdated.getId(), productDto);

        // then
        ProductDto productDtoSaved = productService.getProductById(productDtoUpdated.getId()).get();
        assertThat(productDtoSaved).isNotNull();
        assertThat(productDtoSaved.getDuration()).isEqualTo(productDto.getDuration());
    }

    @Test
    public void deleteProduct_ReturnNotNull() {

        // given
        ProductDto productDto = getProductDto();
        ProductDto productDtoUpdated = productService.createProduct(productDto);

        // when
        productService.deleteProduct(productDtoUpdated.getId());

        // then
        ProductDto productDtoSaved = productService.getProductById(productDtoUpdated.getId()).orElse(null);
        assertThat(productDtoSaved).isNull();
    }

    @Test
    public void getAllProducts_ReturnNotNull() {

        // given
        ProductDto productDto = getProductDto();
        productService.createProduct(productDto);
        productService.createProduct(productDto);

        // when
        List<ProductDto> productDtoList = productService.getAllProducts();

        // then
        assertThat(productDtoList.size()).isEqualTo(2);
        assertThat(productDtoList.get(0).getId()).isNotEqualTo(productDtoList.get(1).getId());
    }

    @Test
    public void updatePrice_ReturnUpdatedPrice() {

        // given
        ProductDto productDto = getProductDto();
        ProductDto productDtoUpdated = productService.createProduct(productDto);

        // when
        productService.updatePriceProduct(productDtoUpdated.getId(), BigDecimal.valueOf(1));

        // then
        Optional<ProductDto> productDtoSaved = productService.getProductById(productDtoUpdated.getId());
        assertThat(productDtoSaved.get().getPrice()).isNotEqualByComparingTo(productDto.getPrice());
    }

    private static ProductDto getProductDto() {
        return ProductDto.builder()
                .summary("Java")
                .description("Course")
                .price(BigDecimal.valueOf(45))
                .discountId(discount.getId())
                .duration((short) 5)
                .active(true)
                .build();
    }
}
