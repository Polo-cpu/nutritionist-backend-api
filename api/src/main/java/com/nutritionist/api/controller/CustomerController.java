package com.nutritionist.api.controller;

import com.nutritionist.api.model.dto.CustomerDto;
import com.nutritionist.api.model.entity.CustomerEntity;
import com.nutritionist.api.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/customer")
public class CustomerController {
    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }
    @GetMapping("/all")
    public ResponseEntity<List<CustomerEntity>> getAll(){
        List<CustomerEntity> allCustomers = customerService.getAll();
        log.info("");
        return new ResponseEntity<List<CustomerEntity>>(allCustomers, HttpStatus.OK);
    }
    @GetMapping("/{no}/{size}")
    public ResponseEntity<Page<CustomerEntity>> getCustomersWithPagination(@PathVariable int no,
                                                                           @PathVariable int size){
        Page<CustomerEntity> customersWithPagination = customerService.getCustomersWithPagination(no,size);
        log.info("");
        return new ResponseEntity<Page<CustomerEntity>>(customersWithPagination, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerEntity> getById(@PathVariable("id") Long id){
        CustomerEntity customer = customerService.getById(id);
        log.info("");
        return new ResponseEntity<CustomerEntity>(customer,HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<CustomerEntity> addCustomer(@RequestBody CustomerDto customerDto){
        CustomerEntity add = customerService.addCustomer(customerDto);
        log.info("");
        return new ResponseEntity<CustomerEntity>(add,HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomerById(@PathVariable("id") Long id){
        customerService.deleteById(id);
        log.info("");
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
