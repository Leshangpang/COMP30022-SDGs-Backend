package com.chen.itproject.mappers;

import com.chen.itproject.pojo.Module;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

@Mapper
public interface ModuleMapper {

    @Insert("insert module(module_name, quiz_num, resource_num, card_num) value (#{moduleName}, #{quizNum}, " +
            "#{resourceNum}, #{cardNum})")
    int insert(Module module);

    int update(Module module);

    @Select("select * from module")
    ArrayList<Module> list();

    @Select("select * from module where module_id = #{id}")
    Module byId(Integer id);

    @Delete("delete from module where module_id = #{moduleId}")
    int delete(Module module);
}
