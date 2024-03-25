package com.tech.reactive.utils;

import com.tech.reactive.dto.ProductDto;
import com.tech.reactive.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class AppUtil {

    public static ProductDto prepareDto(Product product){

        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .build();

    }


    public static Product prepareEntity(ProductDto product){

        return Product.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .build();
    }
}
