package com.nutritionist.api.service;

import com.nutritionist.api.exception.ProductNotFoundException;
import com.nutritionist.api.model.dto.ProductDto;
import com.nutritionist.api.model.entity.ProductEntity;
import com.nutritionist.api.model.enums.Language;
import com.nutritionist.api.model.enums.MessageCodes;
import com.nutritionist.api.model.mapper.ProductMapper;
import com.nutritionist.api.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@ComponentScan("com.nutritionist.api.service.ProductService.class")
@Slf4j
@AllArgsConstructor
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductMapper productMapper;

    public List<ProductEntity> getAll(Language language){
        try {
            log.info("all products are showing");
            return productRepository.findAll();
        }catch (Exception e){
            throw new ProductNotFoundException(language, MessageCodes.PRODUCT_NOT_FOUND);
        }
    }
    public Page<ProductEntity> getProductsWithPagination(Language language, int page, int size){
        try {
            log.info("all products are showing as page");
            return productRepository.findAll(PageRequest.of(page, size));
        }catch (Exception e){
            throw new ProductNotFoundException(language,MessageCodes.PRODUCT_NOT_FOUND);
        }
    }

    public Optional<ProductEntity> getById(Language language, Long id){
        Optional<ProductEntity> byId = productRepository.findById(id);
        if(byId.isEmpty()){
            throw new ProductNotFoundException(language, MessageCodes.PRODUCT_NOT_FOUND);
        }
        else{
            log.info("product has found!");
            return byId;
        }
    }

    public ProductEntity create(Language language, ProductDto productDto){
        try {
            log.info("product added successfully!");
            return productRepository.save(productMapper.toProductEntity(productDto));
        }catch (Exception e){
            throw  new ProductNotFoundException(language,MessageCodes.PRODUCT_NOT_CREATED);
        }
    }

    public ProductEntity deleteById(Language language, Long id){
        Optional<ProductEntity> byId = productRepository.findById(id);
        if(byId.isEmpty()){
            throw new ProductNotFoundException(language,MessageCodes.CUSTOMER_NOT_DELETED);
        }
        else{
            log.error("Product deleted successfully!");
            productRepository.delete(byId.get());
        }
        return byId.get();
    }
}
