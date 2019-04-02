package com.spring.rest.amqp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Table(name = "profile")
@Entity
public class Profile implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email(message = "Invalid email format")
    @NotEmpty(message = "Email is required")
    @NotNull(message = "Email cannot be left null")
    @Column(name = "email", unique = true)
    private String email;

    @NotEmpty(message = "First name is required")
    @NotNull(message = "First name cannot be null")
    @Column(name = "first_name")
    private String firstName;

    @NotEmpty(message = "Last name is required")
    @NotNull(message = "Last name cannot be null")
    @Column(name = "last_name")
    private String lastName;

    @NotNull
    @Column(name = "age", nullable = false)
    private Integer age;

    @Column(name = "occupation", nullable = true)
    private String occupation;

    public Profile(String email, String firstName, String lastName, Integer age, String occupation) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.occupation = occupation;
    }
}