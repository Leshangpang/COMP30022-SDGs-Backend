package com.chen.itproject.services.impl;

import com.chen.itproject.mappers.CommunityMapper;
import com.chen.itproject.pojo.CommunityQuiz;
import com.chen.itproject.services.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CommunityServiceImpl implements CommunityService {

    @Autowired
    private CommunityMapper communityMapper;

    @Override
    public int addQuiz(CommunityQuiz communityQuiz) {

        int code = communityMapper.create(communityQuiz);

        return code;
    }

    @Override
    public ArrayList<CommunityQuiz> list(Integer moduleId, Integer loginUserId) {

        ArrayList<CommunityQuiz> list = communityMapper.list(moduleId, loginUserId);

        return list;
    }

    @Override
    public int update(CommunityQuiz communityQuiz) {

        int code = communityMapper.update(communityQuiz);

        return code;
    }

    @Override
    public CommunityQuiz byId(Integer id) {

        CommunityQuiz communityQuiz = communityMapper.byId(id);

        return communityQuiz;
    }

    @Override
    public int delete(CommunityQuiz communityQuiz) {

        int code = communityMapper.delete(communityQuiz);

        return code;
    }

    @Override
    public int rate(Integer questionId, Integer userId, Float personalRating) {

        int code = communityMapper.rate(questionId, userId, personalRating);

        return code;
    }

    @Override
    public Float getPersonalRating(Integer questionId, Integer userId) {
        return communityMapper.getPersonalRating(questionId, userId);
    }

}
