package com.voatads.authentication.repository;

import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.voatads.authentication.model.Auth;

@Repository
public interface AuthRepository extends MongoRepository<Auth, UUID> {
    
    public Auth findByLogin(String login);

    public Auth findByLoginAndPassword(String login, String password);
}
