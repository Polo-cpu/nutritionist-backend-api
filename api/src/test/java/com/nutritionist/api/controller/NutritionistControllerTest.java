package com.nutritionist.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nutritionist.api.exception.handler.GenericExceptionHandler;
import com.nutritionist.api.model.dto.CustomerDto;
import com.nutritionist.api.model.dto.NutritionistDto;
import com.nutritionist.api.model.entity.CustomerEntity;
import com.nutritionist.api.model.entity.NutritionistEntity;
import com.nutritionist.api.model.enums.Language;
import com.nutritionist.api.model.mapper.CustomerMapper;
import com.nutritionist.api.model.mapper.CustomerMapperImpl;
import com.nutritionist.api.model.mapper.NutritionistMapper;
import com.nutritionist.api.model.mapper.NutritionistMapperImpl;
import com.nutritionist.api.service.CustomerService;
import com.nutritionist.api.service.NutritionistService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
@ExtendWith(SpringExtension.class)
@WebMvcTest(NutritionistController.class)
@EnableAutoConfiguration

public class NutritionistControllerTest {
    private NutritionistMapper nutritionistMapper = new NutritionistMapperImpl();
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private final Language language = Language.EN;
    @MockBean
    private NutritionistService nutritionistService;
    @InjectMocks
    private NutritionistController nutritionistController;
    @BeforeEach
    public void setup() {

        JacksonTester.initFields(this, new ObjectMapper());

        mockMvc = MockMvcBuilders.standaloneSetup(nutritionistController)
                .setControllerAdvice(new GenericExceptionHandler())
                .build();
    }
    @Test
    void getAllNutritionist() throws Exception {
        List<NutritionistEntity> sampleNutritionists = sampleNutritionistList();
        when(nutritionistService.getAll(language)).thenReturn(sampleNutritionists);
        MockHttpServletResponse response = mockMvc.perform(get("/nutritionist/all"))
                .andExpect((ResultMatcher) MediaType.APPLICATION_JSON)
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

    }
    @Test
    void getNutritionistsWithPagination() throws Exception{
        Page<NutritionistEntity> nutritionistEntityPage = (Page<NutritionistEntity>) sampleNutritionistList();
        when(nutritionistService.getNutritionistWithPagination(language,5,5)).thenReturn(nutritionistEntityPage);
        MockHttpServletResponse response = mockMvc.perform(get("/5/5"))
                .andExpect((ResultMatcher) MediaType.APPLICATION_JSON)
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }
    @Test
    void getNutritionistById() throws Exception{
        List<NutritionistEntity> sampleNutritionistList = sampleNutritionistList();
        when(nutritionistService.getById(language,1L)).thenReturn(Optional.ofNullable(sampleNutritionistList.get(0)));
        MockHttpServletResponse response = mockMvc.perform(get("/nutritionist/{}"))
                .andExpect((ResultMatcher) MediaType.APPLICATION_JSON)
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        NutritionistEntity nutritionist = new ObjectMapper().readValue(response.getContentAsString(),NutritionistEntity.class);
        Assertions.assertEquals(sampleNutritionistList.get(0).getNutritionistName(), nutritionist.getNutritionistName());
    }
    @Test
    void deleteNutritionist() throws Exception{
        NutritionistEntity nutritionist = sampleNutritionistList().get(0);
        Mockito.when(nutritionistService.deleteById(language,1L)).thenReturn(nutritionist);
        MockHttpServletResponse response = mockMvc.perform(get("/nutritionist/1L"))
                .andExpect((ResultMatcher) MediaType.APPLICATION_JSON)
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }
    @Test
    void createNutritionist() throws Exception{
        NutritionistEntity nutritionist = sampleNutritionistList().get(1);
        NutritionistDto nutritionistDto = new NutritionistDto("marta","diabetic",true,null);
        Mockito.when(nutritionistService.create(language, nutritionistDto)).thenReturn(nutritionist);
        MockHttpServletResponse response = mockMvc.perform(get("/nutritionist/create"))
                .andExpect((ResultMatcher) MediaType.APPLICATION_JSON)
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }
    public List<NutritionistEntity> sampleNutritionistList(){
        List<NutritionistEntity> sampleList = new ArrayList<>();
        NutritionistEntity nutritionist1 = new NutritionistEntity(1L,"marta","diabetic",true,null);
        NutritionistEntity nutritionist2 = new NutritionistEntity(2L,"tamara","sports",false,null);
        NutritionistEntity nutritionist3 = new NutritionistEntity(3L,"hank","obesity",true,null);
        sampleList.add(nutritionist1);
        sampleList.add(nutritionist2);
        sampleList.add(nutritionist3);
        return sampleList;
    }
}
