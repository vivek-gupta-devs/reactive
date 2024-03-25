package com.tech.reactive.service;

import com.tech.reactive.dto.ProductDto;
import com.tech.reactive.entity.Product;
import com.tech.reactive.repository.ProductRepository;
import com.tech.reactive.utils.AppUtil;
import org.springframework.data.domain.Range;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public Flux<ProductDto> getProducts(){
        return repository.findAll().map(AppUtil::prepareDto);
    }

    public Mono<ProductDto> getProduct(String id){
        return repository.findById(id).map(AppUtil::prepareDto);
    }

    public Flux<ProductDto> getProductsByPriceRange(double min, double max){
        return repository.findByPriceBetween(Range.closed(min,max)).map(AppUtil::prepareDto);
    }

    public Mono<ProductDto> saveProduct(Mono<ProductDto> productDto){
        return productDto.map(AppUtil :: prepareEntity)
                .flatMap(repository::insert)
                .map(AppUtil::prepareDto);

    }


    public Mono<ProductDto> updateProduct(Mono<ProductDto> productDto, String id){
        return repository.findById(id)
                .flatMap(product -> productDto.map(AppUtil::prepareEntity))
                .doOnNext(product -> product.setId(id))
                .flatMap(repository::save)
                .map(AppUtil::prepareDto);

    }

    public Mono<Void> deleteProduct(String id){
        return repository.deleteById(id);
    }
}
