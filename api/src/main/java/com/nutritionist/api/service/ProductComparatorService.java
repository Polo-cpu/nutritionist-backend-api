package com.nutritionist.api.service;

import com.nutritionist.api.model.dto.ProductDto;
import com.nutritionist.api.model.entity.ProductEntity;
import com.nutritionist.api.model.mapper.ProductMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;

@Slf4j
@Service
public class ProductComparatorService implements Comparator<ProductEntity> {


    @Override
    public int compare(ProductEntity o1, ProductEntity o2) {
        return Double.compare(o1.getProductPrice(),o2.getProductPrice());
    }

}
