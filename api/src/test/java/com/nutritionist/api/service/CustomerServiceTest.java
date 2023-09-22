package com.nutritionist.api.service;

import com.nutritionist.api.model.dto.CustomerDto;
import com.nutritionist.api.model.entity.CustomerEntity;
import com.nutritionist.api.model.mapper.CustomerMapper;
import com.nutritionist.api.repository.CustomerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

class CustomerServiceTest {
    private CustomerRepository customerRepository;
    private CustomerMapper customerMapper;
    private CustomerService customerService;
    @BeforeEach
    public void setUp(){
        customerRepository = Mockito.mock(CustomerRepository.class);
        customerMapper = Mockito.mock(CustomerMapper.class);
    }
    @Test
    public void
    public List<CustomerEntity> customerSampleData(){
        List<CustomerEntity> sampleDataList = new ArrayList<>();
        CustomerEntity customer1 = new CustomerEntity(1L,"john","male",35,180.0,100.0);
        CustomerEntity customer2 = new CustomerEntity(2L,"stoic","male",18,190.0,150.0);
        CustomerEntity customer3 = new CustomerEntity(3L,"maria","female",25,170.0,80.0);
        sampleDataList.add(customer1);
        sampleDataList.add(customer2);
        sampleDataList.add(customer3);
        return sampleDataList;
    }

}
