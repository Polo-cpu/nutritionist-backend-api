package com.nutritionist.api.controller;

import com.nutritionist.api.model.dto.ProductDto;
import com.nutritionist.api.model.entity.ProductEntity;
import com.nutritionist.api.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/product")
public class ProductController {
    private ProductService productService;
    @Autowired
    public ProductController(ProductService productService){
        this.productService = productService;
    }
    @GetMapping("/all")
    public ResponseEntity<List<ProductEntity>> gelAllProducts(){
        List<ProductEntity> products = productService.getAll();
        log.info("");
        return new ResponseEntity<List<ProductEntity>>(products, HttpStatus.OK);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<ProductEntity> getById(@PathVariable("id") Long id){
        ProductEntity product = productService.getById(id);
        log.info("");
        return new ResponseEntity<ProductEntity>(product,HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<ProductEntity> addProduct(@RequestBody ProductDto productDto){
        ProductEntity add = productService.addProduct(productDto);
        log.info("");
        return new ResponseEntity<ProductEntity>(add,HttpStatus.CREATED);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProduct(Long id){
        productService.deleteById(id);
        log.info("");
        return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
    }



}
