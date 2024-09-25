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

    /**
     * Add a new module
     * @param module The module object to be added
     * @return Result object indicating success or failure
     */
    @PutMapping
    public Result addModule(@RequestBody Module module) {

        int code = moduleService.addModule(module);

        if (code == 1) {
            return Result.successResult(null);
        } else {
            return Result.errorResult();
        }
    }

    /**
     * Update an existing module
     * @param module The module object with updated information
     * @return Result object indicating success or failure
     */
    @PostMapping
    public Result update(@RequestBody Module module) {

        int code = moduleService.update(module);

        if (code == 1) {
            return Result.successResult(null);
        } else {
            return Result.errorResult();
        }
    }

    /**
     * Retrieve a list of all modules
     * @return Result object containing the list of modules or an error
     */
    @GetMapping
    public Result list() {

        ArrayList<Module> modulesFound = moduleService.list();

        if (modulesFound != null) {
            return Result.successResult(modulesFound);
        } else {
            return Result.errorResult();
        }
    }

    /**
     * Retrieve a module by its ID
     * @param id The ID of the module
     * @return Result object containing the module data or an error
     */
    @GetMapping("/{id}")
    public Result byId(@PathVariable Integer id) {

        Module moduleFound = moduleService.byId(id);

        if (moduleFound != null) {
            return Result.successResult(moduleFound);
        } else {
            return Result.errorResult();
        }
    }

    /**
     * Delete a specific module
     * @param module The module object to be deleted
     * @return Result object indicating success or failure
     */
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
