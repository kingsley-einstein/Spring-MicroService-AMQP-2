package com.spring.rest.amqp.repositories;

import java.util.Optional;

import com.spring.rest.amqp.model.Profile;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    public Optional<Profile> findByEmail(String email);
}