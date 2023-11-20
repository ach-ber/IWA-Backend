package com.iwa.serviceuser.repository;


import com.iwa.serviceuser.entity.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserCredentialRepository extends JpaRepository<UserCredential,Integer> {
    Optional<UserCredential> findByName(String username);

    Optional<UserCredential> findByEmail(String email);
}
