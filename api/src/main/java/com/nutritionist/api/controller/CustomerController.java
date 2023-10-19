package com.nutritionist.api.controller;

import com.nutritionist.api.model.dto.CustomerDto;
import com.nutritionist.api.model.entity.CustomerEntity;
import com.nutritionist.api.model.enums.Language;
import com.nutritionist.api.service.CustomerService;
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
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;
    private final Language language;
    @Autowired
    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
        this.language = Language.EN;
    }
    @GetMapping("/all")
    public ResponseEntity<List<CustomerEntity>> getAll(){
        List<CustomerEntity> allCustomers = customerService.getAll(language);
        return new ResponseEntity<List<CustomerEntity>>(allCustomers, HttpStatus.OK);
    }
    @GetMapping("/{no}/{size}")
    public ResponseEntity<Page<CustomerEntity>> getCustomersWithPagination(@PathVariable int no,
                                                                           @PathVariable int size){
        Page<CustomerEntity> customersWithPagination = customerService.getCustomersWithPagination(language, no,size);
        return new ResponseEntity<Page<CustomerEntity>>(customersWithPagination, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<CustomerEntity>> getById(@PathVariable("id") Long id){
        Optional<CustomerEntity> byId = customerService.getById(language, id);
        return new ResponseEntity<Optional<CustomerEntity>>(byId,HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<CustomerEntity> addCustomer(@RequestBody CustomerDto customerDto){
        CustomerEntity add = customerService.create(language, customerDto);

        return new ResponseEntity<CustomerEntity>(add,HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<CustomerEntity> deleteCustomerById(@PathVariable("id") Long id){
        CustomerEntity deletedCustomer = customerService.deleteById(language, id);
        return new ResponseEntity<CustomerEntity>(deletedCustomer,HttpStatus.ACCEPTED);
    }
}
