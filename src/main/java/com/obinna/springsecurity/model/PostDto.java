package com.obinna.springsecurity.model;

public class PostDto {

    private String queryId;
    private String content;
    private String usename;
    private boolean resolve;

    public PostDto() {
    }

    public PostDto(String queryId, String content, String usename, boolean resolve) {
        this.queryId = queryId;
        this.content = content;
        this.usename = usename;
        this.resolve = resolve;
    }

    public String getQueryId() {
        return this.queryId;
    }

    public void setQueryId(String queryId) {
        this.queryId = queryId;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUsename() {
        return this.usename;
    }

    public void setUsename(String usename) {
        this.usename = usename;
    }

    public boolean isResolve() {
        return this.resolve;
    }

    public void setResolve(boolean resolve) {
        this.resolve = resolve;
    }

}