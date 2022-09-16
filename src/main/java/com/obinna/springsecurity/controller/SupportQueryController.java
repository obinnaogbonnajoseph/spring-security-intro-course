package com.obinna.springsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.obinna.springsecurity.model.CreateSupportQueryDto;
import com.obinna.springsecurity.model.PostDto;
import com.obinna.springsecurity.model.SupportQueryDto;
import com.obinna.springsecurity.service.SupportQueryService;

@Controller
public class SupportQueryController {

    private final SupportQueryService supportService;

    public SupportQueryController(SupportQueryService supportService) {
        this.supportService = supportService;
    }

    @GetMapping("/support")
    public ModelAndView getQueries() {
        return new ModelAndView("support", "queries", supportService.getSupportQueriesForUser());
    }

    @GetMapping("/support/query/{id}")
    public ModelAndView getQuery(@PathVariable String id) {
        SupportQueryDto query = supportService.getSupportQueryById(id);
        ModelAndView model = new ModelAndView("query", "query", query);
        var newPost = new PostDto();
        newPost.setResolve(query.resolved());
        model.addObject("newPost", new PostDto());
        return model;
    }

    @GetMapping("/support/compose")
    public ModelAndView createNewSupportQuery() {
        ModelAndView model = new ModelAndView();
        model.addObject("newQuery", new CreateSupportQueryDto());
        model.setViewName("compose");
        return model;
    }
}