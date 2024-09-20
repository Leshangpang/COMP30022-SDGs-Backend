package com.chen.itproject.services;

import com.chen.itproject.pojo.User;

public interface UsersService {

    int signUp(User user);
    int update(User user);
    User searchById(User user);
    int delete(User user);
    User login(User user);
}
