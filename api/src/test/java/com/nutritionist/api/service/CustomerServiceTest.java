package com.nutritionist.api.service;

import com.nutritionist.api.model.dto.CustomerDto;
import com.nutritionist.api.model.entity.CustomerEntity;
import com.nutritionist.api.model.enums.Language;
import com.nutritionist.api.model.mapper.CustomerMapper;
import com.nutritionist.api.repository.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {
    @Autowired
    private final Language language = Language.EN;
    @Mock
    private CustomerMapper customerMapper;
    @Mock
    private CustomerRepository customerRepository;
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
            Assertions.assertEquals(toCompare1.getGender(),toCompare2.getGender());
            Assertions.assertEquals(toCompare1.getAge(),toCompare2.getAge());
            Assertions.assertEquals(toCompare1.getHeight(),toCompare2.getHeight());
            Assertions.assertEquals(toCompare1.getWeight(),toCompare2.getWeight());
            Assertions.assertEquals(toCompare1.getStartOperation(),toCompare2.getStartOperation());
            Assertions.assertEquals(toCompare1.getNutritionist(),toCompare2.getNutritionist());
            Assertions.assertEquals(toCompare1.getProducts(),toCompare2.getProducts());
        }
    }

    @Test
    void getById(){
        CustomerEntity sampleCustomer = sampleCustomerList().get(1);
        Mockito.when(customerRepository.getReferenceById(Mockito.any())).thenReturn(sampleCustomer);
        Optional<CustomerEntity> actualCustomer = customerService.getById(language,1L);

        Assertions.assertEquals(sampleCustomer.getName(), actualCustomer.get().getName());
        Assertions.assertEquals(sampleCustomer.getGender(), actualCustomer.get().getGender());
        Assertions.assertEquals(sampleCustomer.getAge(), actualCustomer.get().getAge());
        Assertions.assertEquals(sampleCustomer.getHeight(), actualCustomer.get().getHeight());
        Assertions.assertEquals(sampleCustomer.getWeight(), actualCustomer.get().getWeight());
        Assertions.assertEquals(sampleCustomer.getStartOperation(), actualCustomer.get().getStartOperation());
        Assertions.assertEquals(sampleCustomer.getNutritionist(), actualCustomer.get().getNutritionist());
        Assertions.assertEquals(sampleCustomer.getProducts(), actualCustomer.get().getProducts());
    }
    @Test
    void addCustomer(){
        CustomerEntity expectedCustomer = sampleCustomerList().get(0);
        expectedCustomer.setId(null);
        Mockito.when(customerRepository.save(Mockito.any())).thenReturn(expectedCustomer);
        CustomerDto customerDto = new CustomerDto();
        customerDto.setName(expectedCustomer.getName());
        customerDto.setGender(expectedCustomer.getGender());
        customerDto.setAge(expectedCustomer.getAge());
        customerDto.setWeight(expectedCustomer.getWeight());
        customerDto.setHeight(expectedCustomer.getHeight());
        customerService.create(language,customerDto);
        verify(customerRepository,times(1)).save(expectedCustomer);

        Assertions.assertEquals(expectedCustomer.getName(),customerDto.getName());
        Assertions.assertEquals(expectedCustomer.getGender(),customerDto.getGender());
        Assertions.assertEquals(expectedCustomer.getAge(),customerDto.getAge());
        Assertions.assertEquals(expectedCustomer.getHeight(),customerDto.getHeight());
        Assertions.assertEquals(expectedCustomer.getWeight(),customerDto.getWeight());
        Assertions.assertEquals(expectedCustomer.getStartOperation(),customerDto.getStartOperation());
        Assertions.assertEquals(expectedCustomer.getNutritionist(), customerDto.getNutritionist());
        Assertions.assertEquals(expectedCustomer.getProducts(), customerDto.getProducts());
    }
    @Test
    void delete(){
        Long customerId = 1L;
        CustomerEntity customer = sampleCustomerList().get(0);
        Mockito.when(customerRepository.getReferenceById(customerId)).thenReturn(customer);
        doNothing().when(customerRepository).deleteById(customerId);
        customerService.deleteById(language,1L);
        verify(customerRepository,times(1)).deleteById(customerId);
    }
    public List<CustomerEntity> sampleCustomerList(){
        List<CustomerEntity> sampleCustomers = new ArrayList<>();
        CustomerEntity customer1 = new CustomerEntity(1L,"john","male",35,185.0,120.0,LocalDate.now(),null,null);
        CustomerEntity customer2 = new CustomerEntity(2L,"marie","female",25,155.0,88.0,LocalDate.now(),null,null);
        CustomerEntity customer3 = new CustomerEntity(3L,"steve","male",42,175.0,142.0,LocalDate.now(),null,null);
        CustomerEntity customer4 = new CustomerEntity(4L,"eve","female",19,165.0,91.0,LocalDate.now(),null,null);
        sampleCustomers.add(customer1);
        sampleCustomers.add(customer2);
        sampleCustomers.add(customer3);
        sampleCustomers.add(customer4);
        return sampleCustomers;
    }
}
