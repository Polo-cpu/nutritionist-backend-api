package com.nutritionist.api.model.entity;

import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
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
public class UserEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(unique = true, nullable = false, name = "user_name")
    private String userName;
    @Column(nullable = false, name = "email")
    private String email;

    @Column(unique = true, nullable = false, name = "password")
    private String password;
    @Column(name = "role")
    private Role role;

    @Enumerated(EnumType.STRING)
    private Set<Role> roles;



}
