package com.nutritionist.api.service;

import com.nutritionist.api.exception.CustomerNotFoundException;
import com.nutritionist.api.exception.ProductException;
import com.nutritionist.api.model.dto.ProductDto;
import com.nutritionist.api.model.entity.CustomerEntity;
import com.nutritionist.api.model.entity.ProductEntity;
import com.nutritionist.api.model.mapper.ProductMapper;
import com.nutritionist.api.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@ComponentScan("com.nutritionist.api.service.ProductService.class")
@Slf4j
@AllArgsConstructor
public class ProductService {
    private ProductRepository productRepository;
    private ProductMapper productMapper;


    public List<ProductEntity> getAll(){
        log.info("all products are showing");
        return productRepository.findAll();
    }
    public Page<ProductEntity> getProductsWithPagination(int no, int size){
        log.info("all customers are showing as page");
        return productRepository.findAll(PageRequest.of(no,size));
    }

    public Optional<ProductEntity> getById(Long id){
        Optional<ProductEntity> byId = productRepository.findById(id);
        if(byId.isEmpty()){
            log.error("Product not found!");
            throw new ProductException("Product","Not Found");
        }
        else {
            return byId;
        }
    }

    public ProductEntity addProduct(ProductDto productDto){
        log.info("a customer is adding");
        ProductEntity productDto2product = productMapper.toProductEntity(productDto);
        return productRepository.save(productDto2product);
    }

    public ProductEntity deleteById(Long id){
        Optional<ProductEntity> byId = productRepository.findById(id);

        if(byId.isEmpty()){
            log.error("Customer not found!");
            throw new CustomerNotFoundException("Customer","Not Found");
        }
        else{
            log.error("Customer deleted successfully!");
            productRepository.delete(byId.get());
        }

        return byId.get();
    }


}
