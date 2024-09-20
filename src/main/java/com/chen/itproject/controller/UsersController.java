package com.chen.itproject.controller;

import com.chen.itproject.pojo.Result;
import com.chen.itproject.pojo.User;
import com.chen.itproject.services.ProcessService;
import com.chen.itproject.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/users")
@RestController
public class UsersController {

    @Autowired
    private UsersService usersService;

    @PostMapping
    public Result update(@RequestBody User user) {
        int code = usersService.update(user);

        if (code == 1) {
            return Result.successResult(null);
        } else {
            return Result.errorResult();
        }
    }

    @GetMapping
    public  Result searchById(User user) {
        User userSearched = usersService.searchById(user);

        if (userSearched != null) {
            return Result.successResult(userSearched);
        } else {
            return Result.errorResult();
        }
    }

    @DeleteMapping
    public Result delete(@RequestBody User user) {

        int code = usersService.delete(user);

        if (code == 1) {
            return Result.successResult(null);
        } else {
            return Result.errorResult();
        }
    }
}
