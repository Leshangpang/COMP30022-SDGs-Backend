package com.chen.itproject.mappers;

import com.chen.itproject.pojo.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UsersMapper{

    @Insert("insert into users(name, password) values(#{name}, #{password})")
    int create(User user);

    int update(User user);

    @Select("select * from users where user_id = #{userId}")
    User searchById(User user);

    @Delete("delete from users where user_id = #{userId}")
    int delete(User user);

    @Select("select * from users where name = #{name} and password = #{password}")
    User login(User user);
}
