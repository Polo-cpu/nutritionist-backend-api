package com.nutritionist.api.service;

import com.nutritionist.api.exception.CustomerNotFoundException;
import com.nutritionist.api.exception.NutritionistNotFoundException;
import com.nutritionist.api.model.dto.NutritionistDto;
import com.nutritionist.api.model.entity.CustomerEntity;
import com.nutritionist.api.model.entity.NutritionistEntity;
import com.nutritionist.api.model.enums.Language;
import com.nutritionist.api.model.enums.MessageCodes;
import com.nutritionist.api.model.mapper.NutritionistMapper;
import com.nutritionist.api.repository.NutritionistRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@ComponentScan("com.nutritionist.api.service.NutritionistService.class")
@Slf4j
@AllArgsConstructor
public class NutritionistService {
    @Autowired
    private NutritionistRepository nutritionistRepository;
    @Autowired
    private NutritionistMapper nutritionistMapper;
    public List<NutritionistEntity> getAll(Language language) {
        try {
            log.info("all nutritionists are showing");
            return nutritionistRepository.findAll();
        } catch (Exception e) {
            throw new NutritionistNotFoundException(language, MessageCodes.NUTRITIONIST_NOT_FOUND);
        }
    }
    public Page<NutritionistEntity> getNutritionistWithPagination (Language language,int page, int size){
        try {
            log.info("all nutritionists are showing as page");
            return nutritionistRepository.findAll(PageRequest.of(page, size));
        } catch (Exception e) {
            throw new NutritionistNotFoundException(language, MessageCodes.NUTRITIONIST_NOT_FOUND);
        }
    }
    public Optional<NutritionistEntity> getById(Language language, Long id){
        Optional<NutritionistEntity> byId = nutritionistRepository.findById(id);
        if(byId.isEmpty()){
            throw new NutritionistNotFoundException(language, MessageCodes.NUTRITIONIST_NOT_FOUND);
        }
        else{
            log.info("Nutritionist has found!");
            return byId;
        }
    }
    public NutritionistEntity create (Language language, NutritionistDto nutritionistDto){
        try {
            log.info("Nutritionist added successfully!");
            return nutritionistRepository.save(nutritionistMapper.toNutritionistEntity(nutritionistDto));
        }catch (Exception e){
            throw  new NutritionistNotFoundException(language,MessageCodes.NUTRITIONIST_NOT_CREATED);
        }
    }
    public boolean isNutritionistAvailable (Language language, Long id){
        try {
            Optional<NutritionistEntity> nutritionist = nutritionistRepository.findById(id);
            return nutritionist.get().getIsAvailable();
        }catch (Exception e){
            throw new NutritionistNotFoundException(language,MessageCodes.NUTRITIONIST_NOT_AVAILABLE);
        }
    }

    public NutritionistEntity deleteById(Language language, Long id){
        Optional<NutritionistEntity> byId = nutritionistRepository.findById(id);
        if(byId.isEmpty()){
            throw new NutritionistNotFoundException(language,MessageCodes.NUTRITIONIST_NOT_DELETED);
        }
        else{
            log.error("Nutritionist deleted successfully!");
            nutritionistRepository.delete(byId.get());
        }

        return byId.get();
    }
}