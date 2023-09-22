package com.nutritionist.api.service;

import com.nutritionist.api.model.dto.ProductDto;
import com.nutritionist.api.model.entity.ProductEntity;
import com.nutritionist.api.model.mapper.ProductMapper;
import com.nutritionist.api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@ComponentScan("com.nutritionist.api.service.ProductService.class")
public class ProductService {
    private ProductRepository productRepository;
    private ProductMapper productMapper;
    @Autowired
    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }
    public List<ProductEntity> getAll(){
        return productRepository.findAll();
    }
    public ProductEntity getById(Long id){
        return productRepository.getReferenceById(id);
    }
    public ProductEntity addProduct(ProductDto productDto){
        ProductEntity productDto2product = productMapper.productDTO2ProductEntity(productDto);
        return productRepository.save(productDto2product);
    }
    public void deleteById(Long id){
        productRepository.deleteById(id);
    }

}
