package com.nutritionist.api.service;

import com.nutritionist.api.model.dto.CustomerDto;
import com.nutritionist.api.model.entity.CustomerEntity;
import com.nutritionist.api.model.mapper.CustomerMapper;
import com.nutritionist.api.repository.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private CustomerMapper customerMapper;
    @InjectMocks
    private CustomerService customerService;

    @Test
    void getAllCustomers(){
        List<CustomerEntity> sampleCustomers = sampleCustomerList();
        Mockito.when(customerRepository.findAll()).thenReturn(sampleCustomers);
        List<CustomerEntity> actualCustomers = customerRepository.findAll();
        Assertions.assertEquals(actualCustomers.size(), sampleCustomers.size());
        for(int i = 0;i<actualCustomers.size();i++){
            CustomerEntity toCompare1 = sampleCustomers.get(i);
            CustomerEntity toCompare2 = actualCustomers.get(i);

            Assertions.assertEquals(toCompare1.getName(),toCompare2.getName());
            Assertions.assertEquals(toCompare1.getSex(),toCompare2.getSex());
            Assertions.assertEquals(toCompare1.getAge(),toCompare2.getAge());
            Assertions.assertEquals(toCompare1.getHeight(),toCompare2.getHeight());
            Assertions.assertEquals(toCompare1.getWeight(),toCompare2.getWeight());

        }


    }
    @Test
    void getById(){
        CustomerEntity sampleCustomer = sampleCustomerList().get(1);
        Mockito.when(customerRepository.getReferenceById(Mockito.any())).thenReturn(sampleCustomer);
        CustomerEntity realCustomer = customerService.getById(1L);
        Assertions.assertEquals(sampleCustomer.getName(), realCustomer.getName());
        Assertions.assertEquals(sampleCustomer.getSex(), realCustomer.getSex());
        Assertions.assertEquals(sampleCustomer.getAge(), realCustomer.getAge());
        Assertions.assertEquals(sampleCustomer.getHeight(), realCustomer.getHeight());
        Assertions.assertEquals(sampleCustomer.getWeight(), realCustomer.getWeight());

    }
    @Test
    void addCustomer(){
        CustomerEntity expectedCustomer = sampleCustomerList().get(0);
        expectedCustomer.setId(null);
        Mockito.when(customerRepository.save(Mockito.any())).thenReturn(expectedCustomer);
        CustomerDto customerDto = new CustomerDto();
        customerDto.setNameDto(expectedCustomer.getName());
        customerDto.setSexDto(expectedCustomer.getSex());
        customerDto.setAgeDto(expectedCustomer.getAge());
        customerDto.setHeightDto(expectedCustomer.getHeight());
        customerDto.setWeightDto(expectedCustomer.getWeight());
        CustomerEntity actualCustomer = customerService.addCustomer(customerDto);

        verify(customerRepository,times(1)).save(expectedCustomer);
        Assertions.assertEquals(expectedCustomer.getName(),actualCustomer.getName());
        Assertions.assertEquals(expectedCustomer.getSex(),actualCustomer.getSex());
        Assertions.assertEquals(expectedCustomer.getAge(),actualCustomer.getAge());
        Assertions.assertEquals(expectedCustomer.getHeight(),actualCustomer.getHeight());
        Assertions.assertEquals(expectedCustomer.getWeight(),actualCustomer.getWeight());

    }
    @Test
    void delete(){
        Long customerId = 1L;
        CustomerEntity customer = sampleCustomerList().get(0);
        Mockito.when(customerRepository.getReferenceById(customerId)).thenReturn(customer);
        doNothing().when(customerRepository).deleteById(customerId);
        customerService.deleteById(1L);
        verify(customerRepository,times(1)).deleteById(customerId);
    }
    public List<CustomerEntity> sampleCustomerList(){
        List<CustomerEntity> sampleCustomers = new ArrayList<>();
        CustomerEntity customer1 = new CustomerEntity(1L,"john","male",35,185.0,120.0);
        CustomerEntity customer2 = new CustomerEntity(2L,"marie","female",25,155.0,88.0);
        CustomerEntity customer3 = new CustomerEntity(3L,"steve","male",42,175.0,142.0);
        CustomerEntity customer4 = new CustomerEntity(4L,"eve","female",19,165.0,91.0);
        sampleCustomers.add(customer1);
        sampleCustomers.add(customer2);
        sampleCustomers.add(customer3);
        sampleCustomers.add(customer4);
        return sampleCustomers;
    }

}
