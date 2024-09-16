package com.chen.itproject.services;

import com.chen.itproject.pojo.Resource;
import com.chen.itproject.pojo.User;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface UsersService {

    int signUp(User user);
    int update(User user);
    User searchById(User user);
    int delete(User user);
    User login(User user);
}
