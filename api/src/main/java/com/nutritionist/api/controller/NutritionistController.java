package com.nutritionist.api.controller;

import com.nutritionist.api.model.dto.CustomerDto;
import com.nutritionist.api.model.dto.NutritionistDto;
import com.nutritionist.api.model.entity.CustomerEntity;
import com.nutritionist.api.model.entity.NutritionistEntity;
import com.nutritionist.api.service.NutritionistService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/nutritionist")
public class NutritionistController {
    @Autowired
    private NutritionistService nutritionistService;

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<List<NutritionistEntity>> getAll(){
        List<NutritionistEntity> allNutritionists = nutritionistService.getAll();

        return new ResponseEntity<List<NutritionistEntity>>(allNutritionists, HttpStatus.OK);
    }
    @GetMapping("/{no}/{size}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<Page<NutritionistEntity>> getCustomersWithPagination(@PathVariable int no,
                                                                           @PathVariable int size){
        Page<NutritionistEntity> nutritionistsWithPagination = nutritionistService.getNutritionistWithPagination(no,size);

        return new ResponseEntity<Page<NutritionistEntity>>(nutritionistsWithPagination, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Optional<NutritionistEntity>> getById(@PathVariable("id") Long id){
        Optional<NutritionistEntity> byId = nutritionistService.getById(id);
        return new ResponseEntity<Optional<NutritionistEntity>>(byId,HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<NutritionistEntity> addNutritionist(@RequestBody NutritionistDto nutritionistDto){
        NutritionistEntity add = nutritionistService.create(nutritionistDto);

        return new ResponseEntity<NutritionistEntity>(add,HttpStatus.CREATED);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<NutritionistEntity> deleteNutritionistById(@PathVariable("id") Long id){
        NutritionistEntity deletedNutritionist = nutritionistService.deleteById(id);
        return new ResponseEntity<NutritionistEntity>(deletedNutritionist,HttpStatus.ACCEPTED);
    }
}
