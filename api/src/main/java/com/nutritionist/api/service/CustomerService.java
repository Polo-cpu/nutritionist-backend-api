package com.nutritionist.api.service;

import com.nutritionist.api.exception.CustomerNotFoundException;
import com.nutritionist.api.model.dto.CustomerDto;
import com.nutritionist.api.model.entity.CustomerEntity;
import com.nutritionist.api.model.mapper.CustomerMapper;
import com.nutritionist.api.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@ComponentScan("com.nutritionist.api.service.CustomerService.class")
@Slf4j
@AllArgsConstructor
public class CustomerService{

    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;

    public List<CustomerEntity> getAll(){
        log.info("all customers are showing");
        return customerRepository.findAll();
    }

    public Page<CustomerEntity> getCustomersWithPagination(int page,int size){
        log.info("all customers are showing as page");
        return customerRepository.findAll(PageRequest.of(page, size));
    }

    public Optional<CustomerEntity> getById(Long id){
        Optional<CustomerEntity> byId = customerRepository.findById(id);
        if(byId.isEmpty()){
            log.error("Customer not found!");
            throw new CustomerNotFoundException("Customer","Not Found");
        }
        else{
            log.info("Customer has found!");
            return byId;
        }
    }

    public CustomerEntity create(CustomerDto customerDto){
        log.info("Customer added successfully!");
        return customerRepository.save(customerMapper.toCustomerEntity(customerDto));
    }

    public CustomerEntity deleteById(Long id){
        Optional<CustomerEntity> byId = customerRepository.findById(id);

        if(byId.isEmpty()){
            log.error("Customer not found!");
            throw new CustomerNotFoundException("Customer","Not Found");
        }
        else{
            log.error("Customer deleted successfully!");
            customerRepository.delete(byId.get());
        }

       return byId.get();
    }


}
