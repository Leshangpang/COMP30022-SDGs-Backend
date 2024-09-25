package com.chen.itproject.controller;

import com.chen.itproject.pojo.Process;
import com.chen.itproject.pojo.Result;
import com.chen.itproject.services.ProcessService;
import com.chen.itproject.util.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RequestMapping("/process")
@RestController
public class ProcessController {

    @Autowired
    private ProcessService processService;

    /**
     * Update a process entry for the logged-in user
     * @param process The process object with updated information
     * @param jwt The JWT token from the request header for user authentication
     * @return Result object indicating success or failure
     */
    @PostMapping
    public Result update(@RequestBody Process process, @RequestHeader("token") String jwt) {

        // Extract user ID from the JWT token
        Claims claims = JwtUtils.parseJwt(jwt);
        Integer userId = claims.get("id", Integer.class);
        process.setUserId(userId);

        // Update the list of finished resources if present
        if (process.getResourcesFinished() != null) {
            // Retrieve the existing list of finished resources
            Process oldProcess = processService.byId(process.getModuleId(), userId);
            String oldResourcesFinished = oldProcess.getResourcesFinished();
            String newResourcesFinished;

            // Concatenate the new finished resources with the existing ones
            if (oldResourcesFinished.isEmpty()) {
                newResourcesFinished = process.getResourcesFinished();
            } else {
                newResourcesFinished = oldResourcesFinished.concat(",")
                        .concat(process.getResourcesFinished());
            }

            process.setResourcesFinished(newResourcesFinished);
        }

        int code = processService.update(process);

        if (code == 1) {
            return Result.successResult(null);
        } else {
            return Result.errorResult();
        }
    }

    /**
     * Retrieve all process entries for the logged-in user
     * @param jwt The JWT token from the request header for user authentication
     * @return Result object containing a list of process entries or an error
     */
    @GetMapping
    public  Result byId(@RequestHeader("token") String jwt) {
        // Extract user ID from the JWT token
        Claims claims = JwtUtils.parseJwt(jwt);
        Integer userId = claims.get("id", Integer.class);

        ArrayList<Process> processes = processService.list(userId);

        if (processes != null) {
            return Result.successResult(processes);
        } else {
            return Result.errorResult();
        }
    }

    /**
     * Retrieve a specific process entry by module ID for the logged-in user
     * @param id The ID of the module
     * @param jwt The JWT token from the request header for user authentication
     * @return Result object containing the process entry or an error
     */
    @GetMapping("/{id}")
    public  Result byId(@PathVariable Integer id, @RequestHeader("token") String jwt) {
        // Extract user ID from the JWT token
        Claims claims = JwtUtils.parseJwt(jwt);
        Integer userId = claims.get("id", Integer.class);

        Process process = processService.byId(id, userId);

        if (process != null) {
            return Result.successResult(process);
        } else {
            return Result.errorResult();
        }
    }
}
