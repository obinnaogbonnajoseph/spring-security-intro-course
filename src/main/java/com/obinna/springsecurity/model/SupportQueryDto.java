package com.obinna.springsecurity.model;

import java.util.Calendar;
import java.util.List;

import com.obinna.springsecurity.entity.SupportQuery;

public record SupportQueryDto(String id, String subject, Calendar creationTime, String username, boolean resolved,
                List<PostDto> posts) {

        public SupportQueryDto(SupportQuery query, List<PostDto> posts) {
                this(query.getId(), query.getSubject(), query.getCreated(), query.getUsername(), query.isResolved(),
                                posts);
        }

}