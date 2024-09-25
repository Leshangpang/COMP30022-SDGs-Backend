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

    /**
     * Create a new community quiz
     * @param communityQuiz The quiz object to be created
     * @return Result object indicating success or failure
     */
    @PutMapping
    public Result create(@RequestBody CommunityQuiz communityQuiz) {

        int code = communityService.addQuiz(communityQuiz);

        if (code == 1) {
            return Result.successResult(null);
        } else {
            return Result.errorResult();
        }
    }

    /**
     * Update an existing community quiz
     * @param communityQuiz The quiz object with updated information
     * @return Result object indicating success or failure
     */
    @PostMapping
    public Result update(@RequestBody CommunityQuiz communityQuiz) {
        int code = communityService.update(communityQuiz);

        if (code == 1) {
            return Result.successResult(null);
        } else {
            return Result.errorResult();
        }
    }

    /**
     * Get a list of quizzes for a specific module
     * @param moduleId The ID of the module
     * @param jwt The JWT token from the request header for user authentication
     * @return Result object containing the list of quizzes or an error
     */
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

    /**
     * Get a quiz by its ID
     * @param id The ID of the quiz
     * @return Result object containing the quiz data or an error
     */
    @GetMapping("/{id}")
    public  Result byId(@PathVariable Integer id) {

        CommunityQuiz communityQuizSearched = communityService.byId(id);

        if (communityQuizSearched != null) {
            return Result.successResult(communityQuizSearched);
        } else {
            return Result.errorResult();
        }
    }

    /**
     * Delete a specific quiz
     * @param communityQuiz The quiz object to be deleted
     * @return Result object indicating success or failure
     */
    @DeleteMapping
    public Result delete(@RequestBody CommunityQuiz communityQuiz) {

        int code = communityService.delete(communityQuiz);

        if (code == 1) {
            return Result.successResult(null);
        } else {
            return Result.errorResult();
        }
    }

    /**
     * Rate a quiz and update its average rating
     * @param questionId The ID of the question to be rated
     * @param personalRating The rating provided by the user
     * @param jwt The JWT token from the request header for user authentication
     * @return Result object indicating success or failure
     */
    @PostMapping("/rate")
    public Result rate(Integer questionId, Float personalRating, @RequestHeader("token") String jwt) {

        // Extract user ID from the JWT token
        Claims claims = JwtUtils.parseJwt(jwt);
        Integer loginUserId = claims.get("id", Integer.class);

        CommunityQuiz communityQuiz = communityService.byId(questionId);
        Float oldRating = communityService.getPersonalRating(questionId, loginUserId);
        int code = communityService.rate(questionId, loginUserId, personalRating);

        // If the rating is successful, update the average rating
        if (code == 1) {
            // If the quiz has no existing average rating, initialize it
            if (communityQuiz.getAverageRating() == null) {
                CommunityQuiz buffer = new CommunityQuiz();
                buffer.setAverageRating(personalRating);
                buffer.setRatedNum(communityQuiz.getRatedNum()+1);
                buffer.setQuestionId(questionId);
                communityService.update(buffer);
            } else {
                // Calculate the new average rating
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
            // If the user is updating their existing rating, recalculate the average
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
