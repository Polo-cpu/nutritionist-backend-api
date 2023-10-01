package com.nutritionist.api.service;

import com.nutritionist.api.exception.CustomerNotFoundException;
import com.nutritionist.api.model.dto.CustomerDto;
import com.nutritionist.api.model.entity.CustomerEntity;
import com.nutritionist.api.model.entity.UserEntity;
import com.nutritionist.api.model.mapper.CustomerMapper;
import com.nutritionist.api.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

@Service
@ComponentScan("com.nutritionist.api.service.CustomerService.class")
@Slf4j
public class CustomerService{


    private CustomerRepository customerRepository;
    private CustomerMapper customerMapper;

    @Autowired
    public CustomerService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }


    public List<CustomerEntity> getAll(){

        log.info("all customers are showing");
        return customerRepository.findAll();
    }

    public Page<CustomerEntity> getCustomersWithPagination(int no,int size){
        log.info("all customers are showing as page");
        return customerRepository.findAll(PageRequest.of(no,size));
    }

    public CustomerEntity getById(Long id){
        CustomerEntity customerById = customerRepository.getReferenceById(id);
        if(customerRepository.getReferenceById(id) == null){
            log.error("Customer not found!");
            throw new CustomerNotFoundException("Customer","Not Found");
        }
        else{
            log.info("Customer has found!");
            return customerById;
        }

    }

    public CustomerEntity addCustomer(CustomerDto customerDto){
        log.info("Customer added successfully!");
        CustomerEntity customerDto2customer = customerMapper.customerDTO2CustomerEntity(customerDto);
        return customerRepository.save(customerDto2customer);
    }

    public CustomerEntity deleteById(Long id){
        CustomerEntity customer = getById(id);
        customerRepository.deleteById(id);
        log.info(customer.getName() + "is deleted");
        return customer;
    }


}
