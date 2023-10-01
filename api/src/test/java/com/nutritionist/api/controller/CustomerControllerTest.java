package com.nutritionist.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nutritionist.api.exception.CustomerNotFoundException;
import com.nutritionist.api.exception.handler.GenericExceptionHandler;
import com.nutritionist.api.model.dto.CustomerDto;
import com.nutritionist.api.model.entity.CustomerEntity;
import com.nutritionist.api.model.mapper.CustomerMapper;
import com.nutritionist.api.service.CustomerService;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CustomerController.class)

public class CustomerControllerTest {
    private CustomerMapper customerMapper;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CustomerService customerService;
    @InjectMocks
    private CustomerController customerController;
    @BeforeEach
    public void setup() {

        JacksonTester.initFields(this, new ObjectMapper());

        mockMvc = MockMvcBuilders.standaloneSetup(customerController)
                .setControllerAdvice(new GenericExceptionHandler())
                .build();
    }
    @Test
    void getAllCustomers() throws Exception {
        List<CustomerEntity> sampleCustomers = sampleCustomerList();
        when(customerService.getAll()).thenReturn(sampleCustomers);
        MockHttpServletResponse response = mockMvc.perform(get("/customer/all"))
                .andExpect((ResultMatcher) MediaType.APPLICATION_JSON)
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

    }
    @Test
    void getCustomersWithPagination() throws Exception{
        Page<CustomerEntity> customerEntityPage = (Page<CustomerEntity>) sampleCustomerList();
        when(customerService.getCustomersWithPagination(5,5)).thenReturn(customerEntityPage);
        MockHttpServletResponse response = mockMvc.perform(get("/5/5"))
                .andExpect((ResultMatcher) MediaType.APPLICATION_JSON)
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }
    @Test
    void getCustomerById() throws Exception{
        List<CustomerEntity> sampleCustomersList = sampleCustomerList();
        when(customerService.getById(1L)).thenReturn(sampleCustomersList.get(0));
        MockHttpServletResponse response = mockMvc.perform(get("/customer/all"))
                .andExpect((ResultMatcher) MediaType.APPLICATION_JSON)
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        CustomerEntity customer = new ObjectMapper().readValue(response.getContentAsString(),CustomerEntity.class);
        Assertions.assertEquals(sampleCustomersList.get(0).getName(), customer.getName());
    }
    @Test
    void deleteCustomer() throws Exception{

        CustomerEntity customer = sampleCustomerList().get(0);

        Mockito.when(customerService.deleteById(1L)).thenReturn(customer);
        MockHttpServletResponse response = mockMvc.perform(get("/customer/1L"))
                .andExpect((ResultMatcher) MediaType.APPLICATION_JSON)
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

    }
    @Test
    void addCustomer() throws Exception{
        CustomerEntity customer = sampleCustomerList().get(1);

        CustomerDto customerDto = new CustomerDto("adam","male",55,175.0,80.00);
        Mockito.when(customerService.addCustomer(customerDto)).thenReturn(customer);
        MockHttpServletResponse response = mockMvc.perform(get("/customer/add"))
                .andExpect((ResultMatcher) MediaType.APPLICATION_JSON)
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
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
