package com.chen.itproject.controller;

import com.chen.itproject.pojo.CommunityQuiz;
import com.chen.itproject.pojo.Result;
import com.chen.itproject.services.CommunityService;
import com.chen.itproject.util.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RequestMapping("/community")
@RestController
public class CommunityController {

    @Autowired
    private CommunityService communityService;

    @PutMapping
    public Result create(@RequestBody CommunityQuiz communityQuiz) {

        int code = communityService.addQuiz(communityQuiz);

        if (code == 1) {
            return Result.successResult(null);
        } else {
            return Result.errorResult();
        }
    }

    @PostMapping
    public Result update(@RequestBody CommunityQuiz communityQuiz) {
        int code = communityService.update(communityQuiz);

        if (code == 1) {
            return Result.successResult(null);
        } else {
            return Result.errorResult();
        }
    }


    @GetMapping
    public  Result list(Integer moduleId, @RequestHeader("token") String jwt) {

        Claims claims = JwtUtils.parseJwt(jwt);
        Integer loginUserId = claims.get("id", Integer.class);

        ArrayList<CommunityQuiz> quizzesSearched = communityService.list(moduleId, loginUserId);

        if (quizzesSearched != null) {
            return Result.successResult(quizzesSearched);
        } else {
            return Result.errorResult();
        }
    }

    @GetMapping("/{id}")
    public  Result byId(@PathVariable Integer id) {

        CommunityQuiz communityQuizSearched = communityService.byId(id);

        if (communityQuizSearched != null) {
            return Result.successResult(communityQuizSearched);
        } else {
            return Result.errorResult();
        }
    }

    @DeleteMapping
    public Result delete(@RequestBody CommunityQuiz communityQuiz) {

        int code = communityService.delete(communityQuiz);

        if (code == 1) {
            return Result.successResult(null);
        } else {
            return Result.errorResult();
        }
    }

    @PostMapping("/rate")
    public Result rate(Integer questionId, Float personalRating, @RequestHeader("token") String jwt) {

        Claims claims = JwtUtils.parseJwt(jwt);
        Integer loginUserId = claims.get("id", Integer.class);

        CommunityQuiz communityQuiz = communityService.byId(questionId);
        Float oldRating = communityService.getPersonalRating(questionId, loginUserId);
        int code = communityService.rate(questionId, loginUserId, personalRating);

        if (code == 1) {

            if (communityQuiz.getAverageRating() == null) {
                CommunityQuiz buffer = new CommunityQuiz();
                buffer.setAverageRating(personalRating);
                buffer.setRatedNum(communityQuiz.getRatedNum()+1);
                buffer.setQuestionId(questionId);
                communityService.update(buffer);
            } else {
                Float newRating = (communityQuiz.getAverageRating()*communityQuiz.getRatedNum() + personalRating)/
                        (communityQuiz.getRatedNum()+1);
                CommunityQuiz buffer = new CommunityQuiz();
                buffer.setAverageRating(newRating);
                buffer.setRatedNum(communityQuiz.getRatedNum()+1);
                buffer.setQuestionId(questionId);
                communityService.update(buffer);
            }

            return Result.successResult(null);
        } else if (code == 2) {

            Float newRating = (communityQuiz.getAverageRating()*communityQuiz.getRatedNum()-oldRating+personalRating)/
                    communityQuiz.getRatedNum();
            CommunityQuiz buffer = new CommunityQuiz();
            buffer.setAverageRating(newRating);
            buffer.setQuestionId(questionId);
            communityService.update(buffer);

            return Result.successResult(null);
        } else {
            return Result.errorResult();
        }
    }

}
