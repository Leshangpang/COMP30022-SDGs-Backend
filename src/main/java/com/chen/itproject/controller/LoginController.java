package com.chen.itproject.controller;


import com.chen.itproject.pojo.Result;
import com.chen.itproject.pojo.User;
import com.chen.itproject.services.ProcessService;
import com.chen.itproject.services.UsersService;
import com.chen.itproject.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RequestMapping("/login")
@RestController
public class LoginController {

    @Autowired
    UsersService usersService;

    @Autowired
    private ProcessService processService;

    @PostMapping
    public Result login(@RequestBody User user){

        User userFound = usersService.login(user);

        if (userFound != null) {
            HashMap<String, Object> claims = new HashMap<>();
            claims.put("id", userFound.getUserId());
            claims.put("name", userFound.getName());
            String jwt = JwtUtils.createJwt(claims);

            return Result.successResult(jwt);
        } else {
            return Result.errorResult("NOT_LOGIN");
        }
    }

    @PutMapping
    public Result create(@RequestBody User user) {

        int code = usersService.signUp(user);
        User userCreated = usersService.login(user);
        int initResult = processService.init(userCreated.getUserId());

        if (code == 1 && initResult == 1) {
            return Result.successResult(null);
        } else {
            return Result.errorResult();
        }
    }

}
