package com.chen.itproject.pojo;

import lombok.Data;

@Data
public class CommunityQuiz {
    Integer questionId;
    Integer userId;
    Integer moduleId;
    String question;
    String choice;
    Integer answer;
    Integer ratedNum;
    Float averageRating;
    Float personalRating;
}
