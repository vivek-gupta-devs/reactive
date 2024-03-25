package com.tech.reactive.controller;

import com.tech.reactive.dto.ProductDto;
import com.tech.reactive.service.ProductService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "")
    public Flux<ProductDto> getProducts(){
        return productService.getProducts();
    }

    @GetMapping(value = "/{id}")
    public Mono<ProductDto> getProduct(@PathVariable String id){
        return productService.getProduct(id);
    }

    @GetMapping(value = "/product-range")
    public Flux<ProductDto> getProduct(@RequestParam("min") double min, @RequestParam("max") double max){
        return productService.getProductsByPriceRange(min,max);
    }

    @PostMapping(value = "/")
    public Mono<ProductDto> saveProduct(@RequestBody Mono<ProductDto> productDto){
        return productService.saveProduct(productDto);
    }

    @PutMapping(value = "/update/{id}")
    public Mono<ProductDto> updateProduct(@RequestBody Mono<ProductDto> productDto, @PathVariable String  id){
        return productService.updateProduct(productDto,id);
    }

    @DeleteMapping(value = "/delete/{id}")
    public Mono<Void> deleteProduct(@PathVariable String id){
        return productService.deleteProduct(id);
    }
}
