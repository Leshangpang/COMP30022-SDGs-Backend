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

    /**
     * Update an existing user
     * @param user The user object with updated information
     * @return Result object indicating success or failure
     */
    @PostMapping
    public Result update(@RequestBody User user) {
        int code = usersService.update(user);

        if (code == 1) {
            return Result.successResult(null);
        } else {
            return Result.errorResult();
        }
    }

    /**
     * Search for a user by their ID
     * @param user The user object containing the ID to search for
     * @return Result object containing the user data or an error
     */
    @GetMapping
    public  Result searchById(User user) {
        User userSearched = usersService.searchById(user);

        if (userSearched != null) {
            return Result.successResult(userSearched);
        } else {
            return Result.errorResult();
        }
    }

    /**
     * Delete a user
     * @param user The user object to be deleted
     * @return Result object indicating success or failure
     */
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
