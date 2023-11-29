package com.example.productservice.service;

import com.example.productservice.dto.ProductDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IProductService {

    Flux<ProductDto> getAll();

    Mono<ProductDto> getProductById(String id);

    Mono<ProductDto> insertProduct(Mono<ProductDto> productDtoMono);

//    Mono<ProductDto> updateProduct(String id, Mono<ProductDto> productDtoMono);

    Mono<Void> deleteProduct(String id);

}