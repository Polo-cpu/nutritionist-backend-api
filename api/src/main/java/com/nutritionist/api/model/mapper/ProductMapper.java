package com.nutritionist.api.model.mapper;

import com.nutritionist.api.model.dto.CustomerDto;
import com.nutritionist.api.model.dto.ProductDto;
import com.nutritionist.api.model.entity.CustomerEntity;
import com.nutritionist.api.model.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mappings({
            @Mapping(source = "productNameDto",target = "productName"),
            @Mapping(source = "productDetailsDto",target = "productDetails"),
            @Mapping(source = "productPriceDto",target = "productPrice")})
    public ProductEntity toProductEntity(ProductDto productDto);
}
