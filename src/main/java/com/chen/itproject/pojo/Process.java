package com.chen.itproject.pojo;

import lombok.Data;

@Data
public class Process {
    Integer userId;
    Integer moduleId;
    String resourcesFinished;
    Integer cardsFinishedNum;
    Integer quizPassed;
}
