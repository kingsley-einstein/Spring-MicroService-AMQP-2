package com.spring.rest.amqp.controllers;

import java.util.Optional;

import com.spring.rest.amqp.model.Profile;
import com.spring.rest.amqp.repositories.ProfileRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile")
public class MainController {
    @Autowired
    private ProfileRepository repository;

    @GetMapping
    @ResponseBody
    public Optional<Profile> getProfile(@RequestParam String email) {
        return repository.findByEmail(email);
    }
}