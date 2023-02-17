package com.demo.rest;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api2")
public class Api2 implements Api {

    @DeleteMapping("/{a}")
    @PreAuthorize("hasRole('ADMIN')")
    public String delete(@PathVariable String a) {
        return "delete accepted: " + a;
    }

}
