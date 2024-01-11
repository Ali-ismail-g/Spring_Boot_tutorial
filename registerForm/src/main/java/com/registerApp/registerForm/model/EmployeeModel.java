package com.registerApp.registerForm.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="employee")
@Setter
@Getter
@PasswordMatching
public class EmployeeModel {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int Id;

    @Column(name="username")
    @NotNull(message="field can't be null")
    @Size(min = 1,message="field is required")
    private String username;

    @NotNull(message="field can't be null")
    @Size(min = 1,message="field is required")
   // @Pattern(regexp = "^(.+)@(\\\\S+)$",message = "enter a valid email")
    @Email
    @Column(name="email")
    private String email;

    @NotNull(message="field can't be null")
    @Size(min = 1,message="field is required")
    @Column(name="password")
    private String password;

    @NotNull(message="field can't be null")
    @Size(min = 1,message="field is required")
    @Column(name="confirmPassword")
    private String confirmPassword;
}
