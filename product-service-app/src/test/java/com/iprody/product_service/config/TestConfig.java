package com.iprody.product_service.config;

import com.iprody.product_service.domain.repository.DiscountRepository;
import com.iprody.product_service.domain.repository.ProductRepository;
import com.iprody.product_service.domain.service.impl.DiscountServiceImpl;
import com.iprody.product_service.domain.service.impl.ProductServiceImpl;
import com.iprody.product_service.web.dto.mapper.DiscountMapper;
import com.iprody.product_service.web.dto.mapper.ProductMapper;

import lombok.RequiredArgsConstructor;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@TestConfiguration
@RequiredArgsConstructor
public class TestConfig {

    private final ProductRepository productRepository;
    private final DiscountRepository discountRepository;

    private final ProductMapper productMapper;
    private final DiscountMapper discountMapper;

//    @Bean
//    public Configuration configuration() {
//        return Mockito.mock(Configuration.class);
//    }

    @Bean
    @Primary
    public ProductServiceImpl productService(

    ) {
        return new ProductServiceImpl(
                productRepository, productMapper
        );
    }

    @Bean
    @Primary
    public DiscountServiceImpl discountService(

    ) {
        return new DiscountServiceImpl(discountRepository, discountMapper);
    }

    @Bean
    public ProductRepository productRepository() {
        return Mockito.mock(ProductRepository.class);
    }

    @Bean
    public DiscountRepository discountRepository() {
        return Mockito.mock(DiscountRepository.class);
    }

}
