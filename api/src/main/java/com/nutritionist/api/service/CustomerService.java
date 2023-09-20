package com.nutritionist.api.service;

import com.nutritionist.api.model.entity.CustomerEntity;
import com.nutritionist.api.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@ComponentScan("com.nutritionist.api.service")
public class CustomerService {


    private CustomerRepository customerRepository;
    @Autowired
    public CustomerService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    public List<CustomerEntity> gelAll(){
        return customerRepository.findAll();
    }
    public CustomerEntity getById(Long id){
        return customerRepository.getReferenceById(id);
    }
    public CustomerEntity addCustomer(CustomerEntity customer){
        return customerRepository.save(customer);
    }
    public void deleteById(Long id){
        customerRepository.deleteById(id);
    }
}
