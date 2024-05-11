package com.jwtAuth.security.dao;

import com.jwtAuth.security.entity.User;

public interface UserDao {
    User findUserByEmail(String email);
}
