package com.nutritionist.api.repository;

import com.nutritionist.api.model.entity.NutritionistEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NutritionistRepository extends JpaRepository<NutritionistEntity,Long> {
}
