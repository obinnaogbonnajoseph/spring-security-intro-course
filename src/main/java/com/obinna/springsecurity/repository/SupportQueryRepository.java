package com.obinna.springsecurity.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.obinna.springsecurity.entity.SupportQuery;

public interface SupportQueryRepository extends MongoRepository<SupportQuery, String> {

    List<SupportQuery> findByUsername(String username);
}