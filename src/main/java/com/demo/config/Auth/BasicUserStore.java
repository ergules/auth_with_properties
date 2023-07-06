package com.demo.config.Auth;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "auth")
public class BasicUserStore implements UserStore {

    private List<AuthUser> users;

    public List<AuthUser> getAll() {
        return getUsers();
    }

}
