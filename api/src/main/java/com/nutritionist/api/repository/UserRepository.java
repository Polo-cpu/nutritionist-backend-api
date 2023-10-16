package com.nutritionist.api.repository;

import com.nutritionist.api.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {
    Optional<UserEntity> findByUsername(String userName);
    @Query(value = "SELECT EMAIL FROM USERS",nativeQuery = true)
    List<String> getAllEmails();
}
