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

    /**
     * Handles user login requests
     * @param user The user object containing login credentials
     * @return Result object containing the JWT token if login is successful, or an error message
     */
    @PostMapping
    public Result login(@RequestBody User user){

        User userFound = usersService.login(user);

        if (userFound != null) {
            // Create claims to include user information in the JWT
            HashMap<String, Object> claims = new HashMap<>();
            claims.put("id", userFound.getUserId());
            claims.put("name", userFound.getName());

            // Generate the JWT token using user information
            String jwt = JwtUtils.createJwt(claims);

            return Result.successResult(jwt);
        } else {
            return Result.errorResult("NOT_LOGIN");
        }
    }

    /**
     * Handles user registration requests, and initialize user's process tracking while registry
     * @param user The user object containing registration details
     * @return Result object indicating success or failure of the registration process
     */
    @PutMapping
    public Result create(@RequestBody User user) {

        int code = usersService.signUp(user);

        // Retrieve the created user's details for processes initialization
        User userCreated = usersService.login(user);

        // Initialize the user's processes
        int initResult = processService.init(userCreated.getUserId());

        if (code == 1 && initResult == 1) {
            return Result.successResult(null);
        } else {
            return Result.errorResult();
        }
    }

}
