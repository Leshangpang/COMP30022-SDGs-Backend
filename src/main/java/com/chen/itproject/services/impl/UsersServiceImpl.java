package com.chen.itproject.services.impl;
import com.chen.itproject.mappers.UsersMapper;
import com.chen.itproject.pojo.User;
import com.chen.itproject.services.UsersService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersMapper usersMapper;

    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public int signUp(User user) {

        int code = usersMapper.create(user);

        return code;
    }

    @Override
    public int update(User user) {

        int code = usersMapper.update(user);

        return code;
    }

    @Override
    public User searchById(User user) {

        User userSearched = usersMapper.searchById(user);

        return userSearched;
    }

    @Override
    public int delete(User user) {
        int code = usersMapper.delete(user);

        return code;
    }

    @Override
    public User login(User user) {

        return usersMapper.login(user);
    }
}
