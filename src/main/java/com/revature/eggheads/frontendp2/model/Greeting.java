package com.revature.eggheads.frontendp2.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Greeting {
    private long id;
    private String content;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}