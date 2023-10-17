package com.nutritionist.api.controller;
import com.nutritionist.api.model.dto.NutritionistDto;
import com.nutritionist.api.model.entity.NutritionistEntity;
import com.nutritionist.api.model.enums.Language;
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
@RequestMapping("/nutritionist")
public class NutritionistController {
    private final NutritionistService nutritionistService;
    private final Language language;
    @Autowired
    public NutritionistController(NutritionistService nutritionistService){
        this.nutritionistService = nutritionistService;
        this.language = Language.EN;
    }
    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<List<NutritionistEntity>> getAll(){
        List<NutritionistEntity> allNutritionists = nutritionistService.getAll(language);
        return new ResponseEntity<List<NutritionistEntity>>(allNutritionists, HttpStatus.OK);
    }
    @GetMapping("/{no}/{size}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<Page<NutritionistEntity>> getCustomersWithPagination(@PathVariable int no,
                                                                           @PathVariable int size){
        Page<NutritionistEntity> nutritionistsWithPagination = nutritionistService.getNutritionistWithPagination(language, no, size);
        return new ResponseEntity<Page<NutritionistEntity>>(nutritionistsWithPagination, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Optional<NutritionistEntity>> getById(@PathVariable("id") Long id){
        Optional<NutritionistEntity> byId = nutritionistService.getById(language, id);
        return new ResponseEntity<Optional<NutritionistEntity>>(byId,HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<NutritionistEntity> addNutritionist(@RequestBody NutritionistDto nutritionistDto){
        NutritionistEntity add = nutritionistService.create(language, nutritionistDto);
        return new ResponseEntity<NutritionistEntity>(add,HttpStatus.CREATED);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<NutritionistEntity> deleteNutritionistById(@PathVariable("id") Long id){
        NutritionistEntity deletedNutritionist = nutritionistService.deleteById(language, id);
        return new ResponseEntity<NutritionistEntity>(deletedNutritionist,HttpStatus.ACCEPTED);
    }
}
