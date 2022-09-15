package com.obinna.springsecurity.service;

import com.obinna.springsecurity.model.CreateSupportQueryDto;
import com.obinna.springsecurity.model.PostDto;

public interface SupportCommandService {

    void createQuery(CreateSupportQueryDto query);

    void postToQuery(PostDto supportQueryPostModel);

    void resolveQuery(String id);

}