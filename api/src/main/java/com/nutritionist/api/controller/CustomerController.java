package com.nutritionist.api.controller;

import com.nutritionist.api.model.dto.CustomerDto;
import com.nutritionist.api.model.entity.CustomerEntity;
import com.nutritionist.api.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        List<CustomerEntity> allCustomers = customerService.gelAll();
        return new ResponseEntity<List<CustomerEntity>>(allCustomers, HttpStatus.OK);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<CustomerEntity> getById(@PathVariable("id") Long id){
        CustomerEntity customer = customerService.getById(id);
        return new ResponseEntity<CustomerEntity>(customer,HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<CustomerEntity> addCustomer(@RequestBody CustomerDto customerDto){
        CustomerEntity add = customerService.addCustomer(customerDto);
        return new ResponseEntity<CustomerEntity>(add,HttpStatus.CREATED);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCustomerById(@PathVariable("id") Long id){
        customerService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
