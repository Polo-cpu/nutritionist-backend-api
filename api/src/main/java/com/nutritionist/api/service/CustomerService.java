package com.nutritionist.api.service;

import com.nutritionist.api.model.dto.CustomerDto;
import com.nutritionist.api.model.entity.CustomerEntity;
import com.nutritionist.api.model.mapper.CustomerMapper;
import com.nutritionist.api.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@ComponentScan("com.nutritionist.api.service.CustomerService.class")
public class CustomerService {


    private CustomerRepository customerRepository;
    private CustomerMapper customerMapper;
    @Autowired
    public CustomerService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    public List<CustomerEntity> gelAll(){
        return customerRepository.findAll();
        log.
    }
    public CustomerEntity getById(Long id){
        return customerRepository.getReferenceById(id);
    }
    public CustomerEntity addCustomer(CustomerDto customerDto){
        CustomerEntity customerDto2customer = customerMapper.customerDTO2CustomerEntity(customerDto);
        return customerRepository.save(customerDto2customer);
    }
    public void deleteById(Long id){
        customerRepository.deleteById(id);
    }
}
