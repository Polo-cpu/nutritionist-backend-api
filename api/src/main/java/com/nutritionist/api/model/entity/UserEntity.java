package com.nutritionist.api.model.entity;

import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.management.relation.Role;
import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Size(min = 5, max = 25, message = "Username length should be between 5 and 25 characters")
    @Column(unique = true, nullable = false, name = "user_name")
    private String userName;
    @Column(nullable = false, name = "email")
    private String email;
    @Column(unique = true, nullable = false, name = "password")
    private String password;
    @Column(name = "role")
    private Role role;

    @ManyToMany
    private Set<RoleEntity> roles;





}
