package com.chen.itproject.services;

import com.chen.itproject.pojo.Process;

import java.util.ArrayList;

public interface ProcessService {

    int init(Integer userId);
    ArrayList<Process> list(Integer userId);
    int update(Process process);
    Process byId(Integer moduleId, Integer userId);
}
