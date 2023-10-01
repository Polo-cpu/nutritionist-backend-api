package com.nutritionist.api.controller;

import com.nutritionist.api.model.dto.CustomerDto;
import com.nutritionist.api.model.entity.CustomerEntity;
import com.nutritionist.api.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<CustomerEntity>> getAll(){
        List<CustomerEntity> allCustomers = customerService.getAll();

        return new ResponseEntity<List<CustomerEntity>>(allCustomers, HttpStatus.OK);
    }
    @GetMapping("/{no}/{size}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Page<CustomerEntity>> getCustomersWithPagination(@PathVariable int no,
                                                                           @PathVariable int size){
        Page<CustomerEntity> customersWithPagination = customerService.getCustomersWithPagination(no,size);

        return new ResponseEntity<Page<CustomerEntity>>(customersWithPagination, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<CustomerEntity> getById(@PathVariable("id") Long id){
        CustomerEntity customer = customerService.getById(id);

        return new ResponseEntity<CustomerEntity>(customer,HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<CustomerEntity> addCustomer(@RequestBody CustomerDto customerDto){
        CustomerEntity add = customerService.addCustomer(customerDto);

        return new ResponseEntity<CustomerEntity>(add,HttpStatus.CREATED);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<CustomerEntity> deleteCustomerById(@PathVariable("id") Long id){
        CustomerEntity deletedCustomer = customerService.deleteById(id);
        return new ResponseEntity<CustomerEntity>(deletedCustomer,HttpStatus.ACCEPTED);
    }
}
