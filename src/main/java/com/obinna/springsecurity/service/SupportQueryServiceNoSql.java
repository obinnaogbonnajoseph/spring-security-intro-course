package com.obinna.springsecurity.service;

import static com.obinna.springsecurity.util.AuthenticationUtil.getUsername;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.obinna.springsecurity.annotations.FilterOutNonCurrentUser;
import com.obinna.springsecurity.entity.SupportQuery;
import com.obinna.springsecurity.model.PostDto;
import com.obinna.springsecurity.model.SupportQueryDto;
import com.obinna.springsecurity.repository.SupportQueryRepository;

@Service
public class SupportQueryServiceNoSql implements SupportQueryService {

    private final SupportQueryRepository supportRepository;

    public SupportQueryServiceNoSql(SupportQueryRepository supportRepository) {
        this.supportRepository = supportRepository;
    }

    @Override
    public List<SupportQueryDto> getSupportQueriesForUser() {
        var supportQueries = supportRepository.findByUsername(getUsername());
        return mapEntityToModel(supportQueries);
    }

    @Override
    public SupportQueryDto getSupportQueryById(String id) {
        return mapEntityToModel(supportRepository.findById(id).get());
    }

    @Override
    public List<SupportQueryDto> getSupportQueriesForAllUsers() {
        return mapEntityToModel(supportRepository.findAll());
    }

    private SupportQueryDto mapEntityToModel(SupportQuery supportQuery) {
        var posts = supportQuery.getPosts().stream()
                .map(post -> new PostDto(post.getId(), post.getContent(), post.getUsername(),
                        supportQuery.isResolved()))
                .collect(Collectors.toList());
        return new SupportQueryDto(supportQuery, posts);
    }

    @FilterOutNonCurrentUser
    private List<SupportQueryDto> mapEntityToModel(List<SupportQuery> supportQueries) {
        return supportQueries.stream()
                .map(this::mapEntityToModel)
                .collect(Collectors.toList());
    }

}