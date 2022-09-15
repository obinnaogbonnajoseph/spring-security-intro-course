package com.obinna.springsecurity.service;

import java.util.List;

import com.obinna.springsecurity.model.SupportQueryDto;

public interface SupportQueryService {

    List<SupportQueryDto> getSupportQueriesForUser();

    SupportQueryDto getSupportQueryById(String queryId);

    List<SupportQueryDto> getSupportQueriesForAllUsers();

}