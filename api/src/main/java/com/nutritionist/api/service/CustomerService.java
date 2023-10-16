package com.nutritionist.api.service;

import com.nutritionist.api.exception.CustomerNotFoundException;
import com.nutritionist.api.model.dto.CustomerDto;
import com.nutritionist.api.model.entity.CustomerEntity;
import com.nutritionist.api.model.enums.IMessageCodes;
import com.nutritionist.api.model.enums.Language;
import com.nutritionist.api.model.enums.MessageCodes;
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

    public List<CustomerEntity> getAll(Language language){
        try {
            log.info("all customers are showing");
            return customerRepository.findAll();
        }catch (Exception e){
            throw new CustomerNotFoundException(language,MessageCodes.CUSTOMER_NOT_FOUND);
        }
    }

    public Page<CustomerEntity> getCustomersWithPagination(Language language, int page, int size){
        try {
            log.info("all customers are showing as page");
            return customerRepository.findAll(PageRequest.of(page, size));
        }catch (Exception e){
            throw new CustomerNotFoundException(language,MessageCodes.CUSTOMER_NOT_FOUND);
        }
    }

    public Optional<CustomerEntity> getById(Language language, Long id){
        Optional<CustomerEntity> byId = customerRepository.findById(id);
        if(byId.isEmpty()){
            throw new CustomerNotFoundException(language, MessageCodes.CUSTOMER_NOT_FOUND);
        }
        else{
            log.info("Customer has found!");
            return byId;
        }
    }

    public CustomerEntity create(Language language, CustomerDto customerDto){
        try {
            log.info("Customer added successfully!");
            return customerRepository.save(customerMapper.toCustomerEntity(customerDto));
        }catch (Exception e){
            throw  new CustomerNotFoundException(language,MessageCodes.CUSTOMER_NOT_CREATED);
        }
    }

    public CustomerEntity deleteById(Language language, Long id){
        Optional<CustomerEntity> byId = customerRepository.findById(id);
        if(byId.isEmpty()){
            throw new CustomerNotFoundException(language,MessageCodes.CUSTOMER_NOT_DELETED);
        }
        else{
            log.error("Customer deleted successfully!");
            customerRepository.delete(byId.get());
        }

       return byId.get();
    }


}
