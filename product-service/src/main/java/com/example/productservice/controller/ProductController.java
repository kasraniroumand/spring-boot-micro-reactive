package com.example.productservice.controller;

import com.example.productservice.dto.ProductDto;
import com.example.productservice.service.IProductService;
import com.example.productservice.service.impl.ProductService;
import com.example.productservice.util.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("products")
public class ProductController {

    private IProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("all")
    public Flux<ProductDto> all(){
        return this
                .productService
                .getAll();
    }


    @GetMapping("{id}")
    public Mono<ResponseEntity<ProductDto>> getProductById(@PathVariable String id)
    {
        return this
                .productService
                .getProductById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<ProductDto> insertProduct(@RequestBody Mono<ProductDto> productDto)
    {
        return this
                .productService
                .insertProduct(productDto);
    }

//    @PutMapping("/{id}")
//    public Mono<ResponseEntity<ProductDto>> updateProduct(
//            @PathVariable String id,
//            Mono<ProductDto> productDtoMono
//            )
//    {
//        return this
//                .productService
//                .updateProduct(id,productDtoMono)
//                .map(ResponseEntity::ok)
//                .defaultIfEmpty(ResponseEntity.notFound().build());
//    }

    @DeleteMapping("{id}")
    public Mono<Void> deleteProduct(
            @PathVariable String id
    )
    {
        return this
                .productService
                .deleteProduct(id);
    }


}
