package com.nutritionist.api.model.mapper;

import com.nutritionist.api.model.dto.ProductDto;
import com.nutritionist.api.model.dto.VisitDto;
import com.nutritionist.api.model.entity.ProductEntity;
import com.nutritionist.api.model.entity.Visit;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface VisitMapper {
    @Mappings({
            @Mapping(source = "localDate",target = "localDate"),
            @Mapping(source = "description",target = "description"),
            @Mapping(source = "customer",target = "customer")})
    public Visit toVisit(VisitDto visitDto);

}
