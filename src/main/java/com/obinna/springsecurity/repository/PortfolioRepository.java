package com.obinna.springsecurity.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.access.prepost.PostAuthorize;

import com.obinna.springsecurity.entity.Portfolio;

public interface PortfolioRepository extends MongoRepository<Portfolio, String> {
    @PostAuthorize("hasPermission(returnObject, 'READ')")
    Portfolio findByUsername(String username);

    boolean existsByUsername(String username);
}