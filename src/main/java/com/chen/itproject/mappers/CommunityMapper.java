package com.chen.itproject.mappers;

import com.chen.itproject.pojo.CommunityQuiz;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

@Mapper
public interface CommunityMapper {

    @Insert("insert community_quiz(user_id, module_id, question, choice, answer) VALUES " +
            "(#{userId}, #{moduleId}, #{question}, #{choice}, #{answer})")
    int create(CommunityQuiz communityQuiz);

    ArrayList<CommunityQuiz> list(Integer moduleId, Integer loginUserId);

    int update(CommunityQuiz communityQuiz);

    @Select("select * from community_quiz where question_id = #{questionId}")
    CommunityQuiz byId(Integer questionId);

    @Delete("delete from community_quiz where question_id = #{questionId}")
    int delete(CommunityQuiz communityQuiz);

    int rate(Integer questionId, Integer userId, Float personalRating);

    @Select("select personal_rating from personal_rate where question_id = #{questionId} AND user_id = #{userId}")
    Float getPersonalRating(Integer questionId, Integer userId);
}
