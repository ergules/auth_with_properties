package com.demo.config.Auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@ConfigurationPropertiesBinding
@Slf4j
public class AuthUserMapper implements Converter<String, AuthUser> {

    @Override
    public AuthUser convert(String source) {
        try {
            String[] props = source.split("::");
            AuthUser user = new AuthUser();
            user.setName(props[0]);
            user.setPass(props[1]);
            user.setRoles(props[2].split(","));
            return user;
        } catch (Exception e) {
            log.warn("Property: {} can not be mapped as user", source);
        }
        return null;
    }
}
