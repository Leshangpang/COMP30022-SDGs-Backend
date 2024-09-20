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

    @PostMapping
    public Result update(@RequestBody Process process, @RequestHeader("token") String jwt) {

        Claims claims = JwtUtils.parseJwt(jwt);
        Integer userId = claims.get("id", Integer.class);
        process.setUserId(userId);

        if (process.getResourcesFinished() != null) {
            Process oldProcess = processService.byId(process.getModuleId(), userId);
            String oldResourcesFinished = oldProcess.getResourcesFinished();
            String newResourcesFinished;
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


    @GetMapping
    public  Result list(@RequestHeader("token") String jwt) {

        Claims claims = JwtUtils.parseJwt(jwt);
        Integer userId = claims.get("id", Integer.class);
        ArrayList<Process> processes = processService.list(userId);

        if (processes != null) {
            return Result.successResult(processes);
        } else {
            return Result.errorResult();
        }
    }

    @GetMapping("/{id}")
    public  Result list(@PathVariable Integer id, @RequestHeader("token") String jwt) {

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
