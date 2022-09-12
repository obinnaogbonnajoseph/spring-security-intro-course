package com.obinna.springsecurity.model;

import java.util.Calendar;
import java.util.List;

public record SupportQueryDto(String id, String subject, Calendar creationTime, String username, boolean resolved,
        List<PostDto> posts) {

}