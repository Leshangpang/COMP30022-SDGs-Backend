package com.chen.itproject.services.impl;

import com.chen.itproject.mappers.ModuleMapper;
import com.chen.itproject.pojo.Module;
import com.chen.itproject.services.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ModuleServiceImpl implements ModuleService {

    @Autowired
    private ModuleMapper moduleMapper;

    @Override
    public int addModule(Module module) {

        int code = moduleMapper.insert(module);

        return code;
    }

    @Override
    public int update(Module module) {

        int code = moduleMapper.update(module);

        return code;
    }

    @Override
    public int delete(Module module) {

        int code = moduleMapper.delete(module);

        return code;
    }

    @Override
    public ArrayList<Module> list() {

        ArrayList<Module> modules = moduleMapper.list();

        return modules;
    }

    @Override
    public Module byId(Integer id) {

        Module moduleFound = moduleMapper.byId(id);

        return moduleFound;
    }
}
