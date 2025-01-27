package com.ofg.oauth.model.entity;

import com.ofg.oauth.core.model.entity.BaseEntity;
import com.ofg.oauth.security.token.Token;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "users")
@Data
public class User extends BaseEntity {
    @Email
    @Column(nullable = false, unique = true)
    private String email;

    private String username;

    @Size(min = 3, max = 255)
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotBlank
    @Size(min = 3, max = 255)
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Size(min = 8, max = 255)
    private String password;

    @Column(nullable = false)
    @Size(min = 10, max = 15)
    private String phoneNumber;

    private String oauth2Provider;

    private String oauth2Id;

    @Column(nullable = false)
    private boolean active = false;

    @Column(name = "activation_token", unique = true)
    private String activationToken;

    @Column(name = "profile_image", nullable = false)
    private String profileImage = "default.png";

    @Column(name = "password_reset_token", unique = true)
    private String passwordResetToken;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private Set<Token> tokens = new HashSet<>();
}
