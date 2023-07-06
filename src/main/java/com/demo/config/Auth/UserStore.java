package com.demo.config.Auth;

import java.util.Collection;

public interface UserStore {
    Collection<AuthUser> getAll();
}
