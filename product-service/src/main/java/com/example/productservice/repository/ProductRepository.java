package com.example.productservice.repository;

import com.example.productservice.enitty.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository  extends ReactiveMongoRepository<Product,String> {



}
