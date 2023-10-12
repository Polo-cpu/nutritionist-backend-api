package com.nutritionist.api.controller;

import com.nutritionist.api.model.dto.ProductDto;
import com.nutritionist.api.model.entity.CustomerEntity;
import com.nutritionist.api.model.entity.ProductEntity;
import com.nutritionist.api.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<ProductEntity>> gelAllProducts(){
        List<ProductEntity> products = productService.getAll();

        return new ResponseEntity<List<ProductEntity>>(products, HttpStatus.OK);
    }
    @GetMapping("/{no}/{size}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Page<ProductEntity>> getProductsWithPagination(@PathVariable int no,
                                                                           @PathVariable int size){
        Page<ProductEntity> productsWithPagination = productService.getProductsWithPagination(no,size);

        return new ResponseEntity<Page<ProductEntity>>(productsWithPagination, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Optional<ProductEntity>> findById(@PathVariable("id") Long id){
        Optional<ProductEntity> byId = productService.getById(id);

        return new ResponseEntity<Optional<ProductEntity>>(byId,HttpStatus.OK);
    }
    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ProductEntity> addProduct(@RequestBody ProductDto productDto){
        ProductEntity add = productService.addProduct(productDto);

        return new ResponseEntity<ProductEntity>(add,HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<ProductEntity> deleteProductById(@PathVariable("id") Long id){
        ProductEntity deletedProduct = productService.deleteById(id);
        return new ResponseEntity<ProductEntity>(deletedProduct,HttpStatus.ACCEPTED);
    }



}
