package com.dulanjith.learningportal.repository;

import com.dulanjith.learningportal.entitiy.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepository extends CrudRepository<Profile, Long> {

    Optional<Profile> findByEmail(String email);
}
