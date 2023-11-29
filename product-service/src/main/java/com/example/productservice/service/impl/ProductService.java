package com.example.productservice.service.impl;

import com.example.productservice.dto.ProductDto;
import com.example.productservice.repository.ProductRepository;
import com.example.productservice.service.IProductService;
import com.example.productservice.util.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Primary
public class ProductService implements IProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository)
    {
        this.productRepository = productRepository;
    }

    public Flux<ProductDto> getAll(){
        return this
                .productRepository
                .findAll()
                .map(ProductMapper.INSTANCE::toDto);
    }

    public Mono<ProductDto> getProductById(String id)
    {
        return this
                .productRepository
                .findById(id)
                .map(ProductMapper.INSTANCE::toDto);
    }

    public Mono<ProductDto> insertProduct(Mono<ProductDto> productDtoMono)
    {
        return productDtoMono
                .map(ProductMapper.INSTANCE::toEntity)
                .flatMap(this.productRepository::insert)
                .map(ProductMapper.INSTANCE::toDto);
    }


//    public Mono<ProductDto> updateProduct(String id, Mono<ProductDto> productDtoMono){
//        return this
//                .productRepository
//                .findById(id)
//                .flatMap(p -> productDtoMono
//                        .map(ProductMapper.INSTANCE::toEntity)
//                        .doOnNext(e -> e.setId(id)))
//                .flatMap(this.productRepository::save)
//                .map(ProductMapper.INSTANCE::toDto);
//    }

    public Mono<Void> deleteProduct(String id)
    {
        return this
                .productRepository
                .deleteById(id);
    }

}
