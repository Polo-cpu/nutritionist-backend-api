package com.nutritionist.api.model.mapper;

import com.nutritionist.api.model.dto.CustomerDto;
import com.nutritionist.api.model.entity.CustomerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
@Mapper(componentModel = "spring")
public interface CustomerMapper {

    @Mappings({
            @Mapping(source = "nameDto", target = "name"),
            @Mapping(source = "sexDto", target = "sex"),
            @Mapping(source = "ageDto", target = "age"),
            @Mapping(source = "heightDto", target = "height"),
            @Mapping(source = "weightDto", target = "weight")})
    CustomerEntity toCustomerEntity(CustomerDto customerDto);




}
