package com.example.productservice.util;

import com.example.productservice.dto.ProductDto;
import com.example.productservice.enitty.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper( ProductMapper.class );

//    @Mapping(source = "price", target = "price")
    ProductDto toDto(Product product);

//    @Mapping(source = "price", target = "price")
    Product toEntity(ProductDto productDto);


}

