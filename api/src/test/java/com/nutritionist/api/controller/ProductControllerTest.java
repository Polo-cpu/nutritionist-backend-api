package com.nutritionist.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nutritionist.api.exception.handler.GenericExceptionHandler;
import com.nutritionist.api.model.dto.CustomerDto;
import com.nutritionist.api.model.dto.ProductDto;
import com.nutritionist.api.model.entity.CustomerEntity;
import com.nutritionist.api.model.entity.ProductEntity;
import com.nutritionist.api.model.mapper.CustomerMapper;
import com.nutritionist.api.model.mapper.ProductMapper;
import com.nutritionist.api.model.mapper.ProductMapperImpl;
import com.nutritionist.api.service.CustomerService;
import com.nutritionist.api.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

public class ProductControllerTest {
    private ProductMapper productMapper = new ProductMapperImpl();
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ProductService productService;
    @InjectMocks
    private ProductController productController;
    @BeforeEach
    public void setup() {

        JacksonTester.initFields(this, new ObjectMapper());

        mockMvc = MockMvcBuilders.standaloneSetup(productController)
                .setControllerAdvice(new GenericExceptionHandler())
                .build();
    }
    @Test
    void getAllProducts() throws Exception {
        List<ProductEntity> sampleProducts = sampleProductList();
        when(productService.getAll()).thenReturn(sampleProducts);
        MockHttpServletResponse response = mockMvc.perform(get("/product/all"))
                .andExpect((ResultMatcher) MediaType.APPLICATION_JSON)
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

    }
    @Test
    void getProductsWithPagination() throws Exception{
        Page<ProductEntity> productEntityPage = (Page<ProductEntity>) sampleProductList();
        when(productService.getProductsWithPagination(5,5)).thenReturn(productEntityPage);
        MockHttpServletResponse response = mockMvc.perform(get("/product/5/5"))
                .andExpect((ResultMatcher) MediaType.APPLICATION_JSON)
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }
    @Test
    void getProductById() throws Exception{
        List<ProductEntity> sampleProductList = sampleProductList();
        when(productService.getById(1L)).thenReturn(sampleProductList.get(0));
        MockHttpServletResponse response = mockMvc.perform(get("/product/1L"))
                .andExpect((ResultMatcher) MediaType.APPLICATION_JSON)
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        ProductEntity product = new ObjectMapper().readValue(response.getContentAsString(),ProductEntity.class);
        Assertions.assertEquals(sampleProductList.get(0).getProductPrice(), product.getProductPrice());
    }
    @Test
    void deleteCustomer() throws Exception{

        ProductEntity product = sampleProductList().get(0);

        Mockito.when(productService.deleteById(1L)).thenReturn(product);
        MockHttpServletResponse response = mockMvc.perform(get("/customer/1L"))
                .andExpect((ResultMatcher) MediaType.APPLICATION_JSON)
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

    }
    @Test
    void addCustomer() throws Exception{
        ProductEntity product = sampleProductList().get(1);

        ProductDto productDto = new ProductDto("abc","medicine",10.0);
        Mockito.when(productService.addProduct(productDto)).thenReturn(product);
        MockHttpServletResponse response = mockMvc.perform(get("/product/add"))
                .andExpect((ResultMatcher) MediaType.APPLICATION_JSON)
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }



    public List<ProductEntity> sampleProductList(){
        List<ProductEntity> sampleProducts = new ArrayList<>();
        ProductEntity customer1 = new ProductEntity(1L,"creatine","medicine",5.0);
        ProductEntity customer2 = new ProductEntity(2L,"dumbbell","sport",10.0);
        ProductEntity customer3 = new ProductEntity(3L,"rope","sport",2.0);
        ProductEntity customer4 = new ProductEntity(4L,"slimming_cure","slimming",1.0);
        sampleProducts.add(customer1);
        sampleProducts.add(customer2);
        sampleProducts.add(customer3);
        sampleProducts.add(customer4);
        return sampleProducts;
    }
}
