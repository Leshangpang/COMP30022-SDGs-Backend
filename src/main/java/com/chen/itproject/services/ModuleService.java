package com.chen.itproject.services;

import com.chen.itproject.pojo.Module;

import java.util.ArrayList;

public interface ModuleService {

    int addModule(Module module);
    int update(Module module);
    int delete(Module module);
    ArrayList<Module> list();
    Module byId(Integer id);
}
