package com.nutritionist.api.service;

import com.nutritionist.api.exception.CustomerNotFoundException;
import com.nutritionist.api.exception.NutritionistNotFoundException;
import com.nutritionist.api.model.dto.NutritionistDto;
import com.nutritionist.api.model.entity.CustomerEntity;
import com.nutritionist.api.model.entity.NutritionistEntity;
import com.nutritionist.api.model.mapper.NutritionistMapper;
import com.nutritionist.api.repository.NutritionistRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class NutritionistService {
    private NutritionistRepository nutritionistRepository;
    private NutritionistMapper nutritionistMapper;

    public NutritionistEntity create(NutritionistDto nutritionistDto){
        log.info("Nutritionist has been created!");
        return nutritionistMapper.toNutritionistEntity(nutritionistDto);
    }
    public boolean isNutritionistAvailable(Long id){
        Optional<NutritionistEntity> nutritionist = nutritionistRepository.findById(id);
        return nutritionist.get().getIsAvailable();
    }
    public List<NutritionistEntity> getAll(){
        return nutritionistRepository.findAll();
    }
    public Optional<NutritionistEntity> getById(Long id){
        Optional<NutritionistEntity> byId = nutritionistRepository.findById(id);
        if(byId.isEmpty()){
            log.error("Nutritionist not found!");
            throw new CustomerNotFoundException("Nutritionist","Not Found");
        }
        else{
            log.error("Nutritionist has found successfully!");
            return byId;
        }
    }
    public Page<NutritionistEntity> getNutritionistWithPagination(int page, int size){
        log.info("all nutritionists are showing as page");
        return nutritionistRepository.findAll(PageRequest.of(page, size));
    }
    public NutritionistEntity deleteById(Long id){
        Optional<NutritionistEntity> byId = nutritionistRepository.findById(id);

        if(byId.isEmpty()){
            log.error("Nutritionist not found!");
            throw new NutritionistNotFoundException("Nutritionist","Not Found");
        }
        else{
            log.error("Nutritionist deleted successfully!");
            nutritionistRepository.delete(byId.get());
        }

        return byId.get();
    }
}
