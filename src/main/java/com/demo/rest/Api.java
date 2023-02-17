package com.demo.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface Api {

    default String getName() {return this.getClass().getName();}

    @GetMapping
    default String get() {
        return getName();
    }

    @PostMapping
    default String post(@RequestBody String body) {
        return getName() + " : " + body;
    }

}
