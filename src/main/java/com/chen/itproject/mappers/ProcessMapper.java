package com.chen.itproject.mappers;

import com.chen.itproject.pojo.Process;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

@Mapper
public interface ProcessMapper {

    @Insert("insert process(user_id, module_id) VALUES (#{userId}, #{moduleId})")
    int create(Integer userId, Integer moduleId);

    @Select("select * from process where user_id = #{userId}")
    ArrayList<Process> list(Integer userId);

    int update(Process process);

    @Select("select * from process where module_id = #{moduleId} AND user_id = #{userId}")
    Process byId(Integer moduleId, Integer userId);
}
