package com.obinna.springsecurity.service;

import static com.obinna.springsecurity.util.AuthenticationUtil.getUsername;

import org.springframework.stereotype.Service;

import com.obinna.springsecurity.entity.Post;
import com.obinna.springsecurity.entity.SupportQuery;
import com.obinna.springsecurity.model.CreateSupportQueryDto;
import com.obinna.springsecurity.model.PostDto;
import com.obinna.springsecurity.repository.SupportQueryRepository;

@Service
public class SupportCommandServiceNoSql implements SupportCommandService {

    private final SupportQueryRepository supportRepository;

    public SupportCommandServiceNoSql(SupportQueryRepository supportRepository) {
        this.supportRepository = supportRepository;
    }

    @Override
    public void createQuery(CreateSupportQueryDto query) {
        supportRepository.save(mapModelToEntity(query));
    }

    @Override
    public void postToQuery(PostDto model) {
        var post = new Post(getUsername(), model.getContent(), System.currentTimeMillis());
        var query = supportRepository.findById(model.getQueryId()).get();
        query.addPost(post);
        if (model.isResolve()) {
            query.resolve();
        }
        supportRepository.save(query);
    }

    @Override
    public void resolveQuery(String id) {
        var query = supportRepository.findById(id).get();
        query.resolve();
        supportRepository.save(query);
    }

    private SupportQuery mapModelToEntity(CreateSupportQueryDto model) {
        var supportQuery = new SupportQuery(getUsername(), model.getSubject());
        supportQuery.addPost(model.getContent(), getUsername());
        return supportQuery;
    }

}