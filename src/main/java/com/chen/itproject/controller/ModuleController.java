package com.chen.itproject.controller;


import com.chen.itproject.pojo.Module;
import com.chen.itproject.pojo.Result;
import com.chen.itproject.services.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RequestMapping("/modules")
@RestController
public class ModuleController {

    @Autowired
    ModuleService moduleService;

    @PutMapping
    public Result addModule(@RequestBody Module module) {

        int code = moduleService.addModule(module);

        if (code == 1) {
            return Result.successResult(null);
        } else {
            return Result.errorResult();
        }
    }

    @PostMapping
    public Result update(@RequestBody Module module) {

        int code = moduleService.update(module);

        if (code == 1) {
            return Result.successResult(null);
        } else {
            return Result.errorResult();
        }
    }

    @GetMapping
    public Result list() {

        ArrayList<Module> modulesFound = moduleService.list();

        if (modulesFound != null) {
            return Result.successResult(modulesFound);
        } else {
            return Result.errorResult();
        }
    }

    @GetMapping("/{id}")
    public Result byId(@PathVariable Integer id) {

        Module moduleFound = moduleService.byId(id);

        if (moduleFound != null) {
            return Result.successResult(moduleFound);
        } else {
            return Result.errorResult();
        }
    }

    @DeleteMapping
    public Result delete(@RequestBody Module module) {

        int code = moduleService.delete(module);

        if (code == 1) {
            return Result.successResult(null);
        } else {
            return Result.errorResult();
        }
    }

}
