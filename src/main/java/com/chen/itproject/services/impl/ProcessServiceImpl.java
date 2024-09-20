package com.chen.itproject.services.impl;

import com.chen.itproject.mappers.ModuleMapper;
import com.chen.itproject.mappers.ProcessMapper;
import com.chen.itproject.pojo.Module;
import com.chen.itproject.pojo.Process;
import com.chen.itproject.services.ProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ProcessServiceImpl implements ProcessService {

    @Autowired
    private ProcessMapper processMapper;

    @Autowired
    private ModuleMapper moduleMapper;

    @Override
    public int init(Integer userId) {

        int code = 0;
        ArrayList<Module> modules = moduleMapper.list();
        for(int i=0; i<modules.size(); i++) {
            int result = processMapper.create(userId, modules.get(i).getModuleId());
            code += result;
        }

        if (code == modules.size()) {
            code = 1;
        } else {
            code = 0;
        }

        return code;
    }

    @Override
    public ArrayList<Process> list(Integer userId) {

        ArrayList<Process> list = processMapper.list(userId);

        return list;
    }

    @Override
    public int update(Process process) {

        int code = processMapper.update(process);

        return code;
    }

    @Override
    public Process byId(Integer moduleId, Integer userId) {

        Process process = processMapper.byId(moduleId, userId);

        return process;
    }

}
