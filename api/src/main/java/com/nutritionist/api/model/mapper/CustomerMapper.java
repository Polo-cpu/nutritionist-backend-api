package com.nutritionist.api.model.mapper;

import com.nutritionist.api.model.dto.CustomerDto;
import com.nutritionist.api.model.entity.CustomerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
@Mapper(componentModel = "spring")
public interface CustomerMapper {

    @Mappings({
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "gender", target = "gender"),
            @Mapping(source = "age", target = "age"),
            @Mapping(source = "height", target = "height"),
            @Mapping(source = "weight", target = "weight"),
            @Mapping(source = "startOperation",target = "startOperation")})
    CustomerEntity toCustomerEntity(CustomerDto customerDto);




}
