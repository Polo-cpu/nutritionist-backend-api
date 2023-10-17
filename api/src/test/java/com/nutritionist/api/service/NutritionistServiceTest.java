package com.nutritionist.api.service;

import com.nutritionist.api.model.entity.NutritionistEntity;
import com.nutritionist.api.model.enums.Language;
import com.nutritionist.api.model.mapper.NutritionistMapper;
import com.nutritionist.api.repository.NutritionistRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class NutritionistServiceTest {
    @Autowired
    private final Language language = Language.EN;
    @Mock
    private NutritionistMapper nutritionistMapper;
    @Mock
    private NutritionistRepository nutritionistRepository;
    @InjectMocks
    private NutritionistService nutritionistService;

    @Test
    void getAllNutritionists(){
        List<NutritionistEntity> sampleNutritionists = sampleNutritionistList();
        Mockito.when(nutritionistService.getAll(language)).thenReturn(sampleNutritionists);
        List<NutritionistEntity> actualNutritionist = nutritionistRepository.findAll();
        Assertions.assertEquals(actualNutritionist.size(), sampleNutritionists.size());
        for(int i = 0;i<actualNutritionist.size();i++){
            NutritionistEntity toCompare1 = sampleNutritionists.get(i);
            NutritionistEntity toCompare2 = actualNutritionist.get(i);

            Assertions.assertEquals(toCompare1.getNutritionistName(),toCompare2.getNutritionistName());
            Assertions.assertEquals(toCompare1.getProfession(),toCompare2.getProfession());
            Assertions.assertEquals(toCompare1.getIsAvailable(),toCompare2.getIsAvailable());
            Assertions.assertEquals(toCompare1.getCustomers(),toCompare2.getCustomers());
        }
    }

    @Test
    void getById(){
        NutritionistEntity sampleNutritionist = sampleNutritionistList().get(1);
        Mockito.when(nutritionistRepository.getReferenceById(Mockito.any())).thenReturn(sampleNutritionist);
        Optional<NutritionistEntity> actualNutritionist = nutritionistService.getById(language,1L);
        Assertions.assertEquals(sampleNutritionist.getNutritionistName(), actualNutritionist.get().getNutritionistName());
        Assertions.assertEquals(sampleNutritionist.getProfession(), actualNutritionist.get().getProfession());
        Assertions.assertEquals(sampleNutritionist.getIsAvailable(), actualNutritionist.get().getIsAvailable());
        Assertions.assertEquals(sampleNutritionist.getCustomers(), actualNutritionist.get().getCustomers());
    }

    @Test
    void addCustomer(){
        NutritionistEntity expectedNutritionist = sampleNutritionistList().get(0);
        expectedNutritionist.setId(null);
        Mockito.when(nutritionistRepository.save(Mockito.any())).thenReturn(expectedNutritionist);
        NutritionistEntity nutritionist = new NutritionistEntity();
        nutritionist.setId(expectedNutritionist.getId());
        nutritionist.setNutritionistName(expectedNutritionist.getNutritionistName());
        nutritionist.setProfession(expectedNutritionist.getProfession());
        nutritionist.setIsAvailable(expectedNutritionist.getIsAvailable());
        nutritionist.setCustomers(expectedNutritionist.getCustomers());
        nutritionistRepository.save(nutritionist);
        verify(nutritionistRepository,times(1)).save(expectedNutritionist);
        Assertions.assertEquals(expectedNutritionist.getNutritionistName(),nutritionist.getNutritionistName());
        Assertions.assertEquals(expectedNutritionist.getProfession(),nutritionist.getProfession());
        Assertions.assertEquals(expectedNutritionist.getIsAvailable(),nutritionist.getIsAvailable());
        Assertions.assertEquals(expectedNutritionist.getCustomers(),nutritionist.getCustomers());
    }

    @Test
    void delete(){
        Long nutritionistId = 1L;
        NutritionistEntity nutritionist = sampleNutritionistList().get(0);
        Mockito.when(nutritionistRepository.getReferenceById(nutritionistId)).thenReturn(nutritionist);
        doNothing().when(nutritionistRepository).deleteById(nutritionistId);
        nutritionistService.deleteById(language,1L);
        verify(nutritionistRepository,times(1)).deleteById(nutritionistId);
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
