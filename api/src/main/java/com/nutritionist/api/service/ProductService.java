package com.nutritionist.api.service;

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

@Service
@ComponentScan("com.nutritionist.api.service.ProductService.class")
@Slf4j
@AllArgsConstructor
public class ProductService {
    private ProductRepository productRepository;
    private ProductMapper productMapper;
    private ProductComparatorService productComparatorService;


    public List<ProductEntity> getAll(){
        log.info("all products are showing");
        return productRepository.findAll();
    }
    public Page<ProductEntity> getProductsWithPagination(int no, int size){
        log.info("all customers are showing as page");
        return productRepository.findAll(PageRequest.of(no,size));
    }

    public ProductEntity getById(Long id){
        ProductEntity productById = productRepository.getReferenceById(id);
        if(productById == null){
            log.error("Product not found!");
            throw new ProductException("Product","Not Found");
        }
        else {
            return productRepository.getReferenceById(id);
        }
    }

    public ProductEntity addProduct(ProductDto productDto){
        log.info("a customer is adding");
        ProductEntity productDto2product = productMapper.toProductEntity(productDto);
        return productRepository.save(productDto2product);
    }

    public ProductEntity deleteById(Long id){
        ProductEntity product = getById(id);
        productRepository.deleteById(id);
        log.info(product.getProductName() + "is deleted");
        return product;
    }
    public String compareProductPrices(Long id1, Long id2){
        ProductEntity product1 = productRepository.getReferenceById(id1);
        ProductEntity product2 = productRepository.getReferenceById(id2);

        if(productComparatorService.compare(product1,product2)!=0){
            return "Prices :" + " First Product : " + product1.getProductPrice() + " Second Product :" + product2.getProductPrice();
        }
        else {
            return "Product prices are equal";
        }
    }


}
