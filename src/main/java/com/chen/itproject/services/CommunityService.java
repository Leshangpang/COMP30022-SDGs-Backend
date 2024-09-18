package com.chen.itproject.services;

import com.chen.itproject.pojo.CommunityQuiz;

import java.util.ArrayList;

public interface CommunityService {

    int addQuiz(CommunityQuiz communityQuiz);
    ArrayList<CommunityQuiz> list(Integer moduleId, Integer loginUserId);
    int update(CommunityQuiz communityQuiz);
    CommunityQuiz byId(Integer id);
    int delete(CommunityQuiz communityQuiz);
    int rate(Integer questionId, Integer userId, Float personalRating);
    Float getPersonalRating(Integer questionId, Integer userId);
}
