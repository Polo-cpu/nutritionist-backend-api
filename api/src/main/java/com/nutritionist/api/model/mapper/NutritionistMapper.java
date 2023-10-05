package com.nutritionist.api.model.mapper;

import com.nutritionist.api.model.dto.CustomerDto;
import com.nutritionist.api.model.dto.NutritionistDto;
import com.nutritionist.api.model.entity.CustomerEntity;
import com.nutritionist.api.model.entity.NutritionistEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface NutritionistMapper {
    @Mappings({
            @Mapping(source = "nutritionistName", target = "nutritionistName"),
            @Mapping(source = "profession", target = "profession"),
            @Mapping(source = "isAvailable", target = "isAvailable")})
    NutritionistEntity toNutritionistEntity(NutritionistDto nutritionistDto);
}
