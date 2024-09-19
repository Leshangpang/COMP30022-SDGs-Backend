# API document

## Login related

###  The project will use JWT tokens to achieve session tracking and login authentication，please store the JWT token in the *local storage* when login for the first time. In the subsequent requests, please add the token into the request header in the format *token：“JWT”*.

### the token will record the userId，and the signature algorithm is HS256。（HEADER and PAYLOAD can be directly decoded by Base64）==users can access other functions except login only after the token has been successfully verified.==

### If token resolution fails, which means the token is expired or tampered with, the server will return the following message

```json
{
    "code":0,
    "msg":"NOT_LOGIN",
    "data":null
}
```

### ==***<u>Please let the user login again by jumping to the login page when front-end receive the msg as above.</u>***==

### 1. Login

- request path: /login

- request method: POST

- request arguments type: Json

  - name: must

  - password: must

  - ```json
    //sample
    
    {
        "name":"test",
        "password":"123qwe"
    }
    ```

- response:

  - ```json
    //sample
    
    {
        "code":1,
        "msg":"success",
        "data":"eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoibW95dWVyIiwiaWQiOjEsImV4cCI6MTcyMzcxMjQ2NX0.2pW4GSyLR_HiB2GbU3wkQfTs4HgwuKtWGUeVqHBaEVs" 
    }
    ```



### 2. Sign up

- request path: /login

- request method: PUT

- request arguments type: json

  - name: must

  - password: must

  - ```json
    //sample
    
    {
        "name":"moyuer",
        "password":"123qwe"
    }
    ```

- response:

  - ```json
    //sample
    
    {
        "code":1,
        "msg":"success",
        "data":null
    }
    ```



## User related

### 1. Update user

- request path: /users

- request method: POST

- request arguments type: json

  - userId: must

  - name: unnecessary

  - password: unnecessary

  - ```json
    //sample
    
    {
        "userId":1,
        
        //transfer if needed
        "password":"123456"
    }
    ```
  
- response:

  - ```json
    //sample
    
    {
        "code":1,
        "msg":"success",
        "data":null
    }
    ```



### 2. Get user by id

- request path: /users

- request method: GET

- request arguments type: Within URL

  - userId: must

  - ```json
    //sample
    
    https://sample.com/users?userId=1
    ```
  
- response:

  - ```json
    //sample
    
    {
        "code":1,
        "msg":"success",
        "data":{
            "name":"moyuer",
            "password":"123qwe",
            //Indicate earned badges by number, separated by commas
            "emblem":"1,5,7",
            //0 means no rewards received
            "rewardInfo":0
        }
    }
    ```



### 3. Delete user

- request path: /users

- request method: DELETE

- request arguments type: json

  - userId: must

  - ```json
    //sample
    
    {
        "userId":1
    }
    ```

- response:

  - ```json
    //sample
    
    {
        "code":1,
        "msg":"success",
        "data":null
    }
    ```



## Modules related 

### 1. Get module list

- request path: /modules

- request method: GET

- request arguments type: no arguments

  - ```json
    https://sample.com/modules
    ```

    

- response:

  - ```json
    //sample
    
    {
        "code": 1,
        "msg": "success",
        "data": [
            {
                "moduleId": 1,
                "moduleName": "arfg",
                "quizNum": 10,
                "resourceNum": 5,
                "cardNum":10
            },
            {
                "moduleId": 2,
                "moduleName": "arfv",
                "quizNum": 10,
                "resourceNum": 5,
                "cardNum":10
            }
        ]
    }
    ```



### 2. Get module by id

- request path: /modules

- request method: GET

- request arguments type: path argument

  - moduleId: must

  - ```json
    https://sample.com/modules/{id}
    ```

    

- response:

  - ```json
    //sample
    
    {
        "code": 1,
        "msg": "success",
        "data": {
            "moduleId": 1,
            "moduleName": "arfg",
            "quizNum": 10,
            "resourceNum": 5,
            "cardNum":10
        }
    }
    ```



## community questions related

### 1. Get question list

- request path: /community

- request method: GET

- request arguments type: Within URL

  - moduleId: must

  - ```json
    https://sample.com/community?moduleId=3
    ```
  
    

- response:

  - ```json
    //sample
    
    {
        "code":1,
        "msg":"success",
        "data":[
            {
                "questionId": 2,
                "userId": 3,
                "moduleId": 3,
                "question": "samplesamplesamplesamplesamplesample",
                //choices are separated by three commas
                "choice": "samplesample,,,samplesample,,,samplesample,,,samplesample",
                "answer": 1,
                //the max rating is 5
                "averageRating": 4.2,
                "personalRating": 5.0
            },
            {
                "questionId": 3,
                "userId": 3,
                "moduleId": 3,
                "question": "samplesamplesamplesamplesamplesample",
                "choice": "samplesample,,,samplesample,,,samplesample,,,samplesample",
                "answer": 1,
                //if no one has ever rated this question, it will be null
                "averageRating": null,
                // if the user didn't rate this question, it will be null
                "personalRating": null
            }
        ]
    }
    ```



### 2. Add question 

- request path: /community

- request method: PUT

- request arguments type: json

  - userId: must

  - moduleId: must

  - question: must

  - choice: must

  - answer: must

  - ```json
    //sample
    
    {
        "userId":1,
        "moduleId":1,
        "question":"samplesamplesamplesamplesamplesample",
        "choice":"samplesample,,,samplesample,,,samplesample,,,samplesample",
        "answer":1
    }
    ```
  
- response:

  - ```json
    //sample
    
    {
        "code":1,
        "msg":"success",
        "data":null
    }
    ```



### 3. Update question 

- request path: /community

- request method: POST

- request arguments type: json

  - questionId: must

  - moduleId: unnecessary

  - question: unnecessary

  - choice: unnecessary

  - answer: unnecessary

  - ```json
    //sample
    
    {
        "questionId":2,
        
        //transfer if needed
        "moduleId":3,
        "question":"testsample",
        "choice":"testsample,,,testsample,,,testsample,,,testsample",
        "answer":2
    }
    ```
  
- response:

  - ```json
    //sample
    
    {
        "code":1,
        "msg":"success",
        "data":null
    }
    ```



### 4. Get question by id

- request path: /community

- request method: GET

- request arguments type: path argument

  - questionId: must

  - ```json
    https://sample.com/community/{id}
    ```

    

- response:

  - ```json
    //sample
    
    {
        "code": 1,
        "msg": "success",
        "data": {
            "questionId": 3,
            "userId": 3,
            "moduleId": 3,
            "question": "samplesamplesamplesamplesamplesample",
            "choice": "samplesample,,,samplesample,,,samplesample,,,samplesample",
            "answer": 1,
            "ratedNum": 2,
            "averageRating": 5.0
        }
    }
    ```



### 5. Delete question

- request path: /community

- request method: DELETE

- request arguments type: json

  - questionId: must

  - ```json
    //sample
    
    {
        "questionId":3
    }
    ```

- response:

  - ```json
    //sample
    
    {
        "code":1,
        "msg":"success",
        "data":null
    }
    ```



### 6. Rate question by id

**<u>The average rating will be automatically updated</u>**

- request path: /community/rate

- request method: POST

- request arguments type: within URL

  - questionId: must

  - personalRating: must

  - ```json
    https://sample.com/community/rate?questionId=3&personalRating=4.0
    ```

    

- response:

  - ```json
    //sample
    
    {
        "code": 1,
        "msg": "success",
        "data":null
    }
    ```



## Quiz related

### 1. Get communityQuiz by serial number and module id

- request path: /communityQuiz

- request method: GET

- request arguments type: json

  - quizSN: must

  - moduleId: must

  - ```json
    //sample
    
    {
        "moduleId":3,
        "quizSN":1
    }
    ```

    

- response:

  - ```json
    //sample
    
    {
        "code": 1,
        "msg": "success",
        "data": {
            "quizSN": 3,
            "moduleId": 3,
            "question": "samplesamplesamplesamplesamplesample",
            "choice": "samplesample,,,samplesample,,,samplesample,,,samplesample",
            "answer": 1,
        }
    }
    ```





## Learning resource related

### 1. Get resource list

- request path: /resource

- request method: GET

- request arguments type: Within URL

  - moduleId: must

  - ```json
    https://sample.com/resource?moduleId=1
    ```

    

- response:

  - ```json
    //sample
    
    {
        "code":1,
        "msg":"success",
        "data":[
            {
                "resourceId":1,
                "moduleId":1,
                "title":"samplesamplesamplesample",
                "author":"samplesample,samplesample",
                "content":"samplesamplesamplesamplesamplesamplesample"
            },
            {
                "resourceId":2,
                "moduleId":1,
                "title":"samplesamplesamplesample",
                "author":"samplesample,samplesample",
                "content":"samplesamplesamplesamplesamplesamplesample"
            },
            {
                "resourceId":3,
                "moduleId":1,
                "title":"samplesamplesamplesample",
                "author":"samplesample,samplesample",
                "content":"samplesamplesamplesamplesamplesamplesample"
            }
        ]
    }
    ```



### 2. Get resource by serial number and module id

- request path: /resource

- request method: GET

- request arguments type: json

  - resourceSN: must

  - moduleId: must

  - ```json
    //sample
    
    {
        "moduleId":3,
        "resourceSN":1
    }
    ```

    

- response:

  - ```json
    //sample
    
    {
        "code": 1,
        "msg": "success",
        "data": {
            "resourceSN": 1,
            "moduleId": 3,
            "title":"samplesamplesamplesample",
            "author":"samplesample,samplesample",
            "content":"samplesamplesamplesamplesamplesamplesample"
        }
    }
    ```



## Flash cards related

### 1. Get card by serial number and module id

- request path: /card

- request method: GET

- request arguments type: json

  - cardSN: must

  - moduleId: must

  - ```json
    //sample
    
    {
        "moduleId":3,
        "cardSN":1
    }
    ```

    

- response:

  - ```json
    //sample
    
    {
        "code": 1,
        "msg": "success",
        "data": {
            "cardSN": 1,
            "moduleId": 3,
            "question": "samplesamplesamplesamplesamplesample",
            "answer": "samplesample"
        }
    }
    ```



## Process related

### 1. Get user's all processes

- request path: /process

- request method: GET

- request arguments type: path argument

  - userId: must

  - ```json
    https://sample.com/process/{id}
    ```

    

- response:

  - ```json
    //sample
    
    {
        "code": 1,
        "msg": "success",
        "data": [
            {
                "moduleId": 1,
                "resourcesFinised": "1,4,5",
                "cardsfinishedNum": 10,
                "quizPassed": 5
            },
            {
                "moduleId": 2,
                "resourcesFinised": "1,4,5",
                "cardsfinishedNum": 10,
                "quizPassed": 5
            },
            {
                "moduleId": 3,
                "resourcesFinised": "1,4,5",
                "cardsfinishedNum": 10,
                "quizPassed": 5
            },
            {
                "moduleId": 4,
                "resourcesFinised": "1,4,5",
                "cardsfinishedNum": 10,
                "quizPassed": 5
            }
        ]
    }
    ```



### 2. Get user's process by module id

- request path: /process

- request method: GET

- request arguments type: json

  - userId: must

  - moduleId: must

  - ```json
    //sample
    
    {
        "moduleId":3,
        "userId":2
    }
    ```

    

- response:

  - ```json
    //sample
    
    {
        "code": 1,
        "msg": "success",
        "data": {
            "moduleId": 3,
            "resourcesFinised": "1,4,5",
            "cardsfinishedNum": 10,
            "quizPassed": 5
        }
    }
    ```



### 3. Update user's process

- request path: /process

- request method: POST

- request arguments type: json

  - userId: must

  - moduleId: must

  - resourcesFinised: unnecessary

  - cardsfinishedNum: unnecessary

  - quizPassed: unnecessary

  - ```json
    //sample
    
    {
        "moduleId": 3,
        "userId": 2,
        
        //transfer if needed
        
        //only put the SN of resource needed to be added
        "resourcesFinised": "4",
        "cardsfinishedNum": 14,
        "quizPassed": 10
    }
    ```

    

- response:

  - ```json
    //sample
    
    {
        "code":1,
        "msg":"success",
        "data":null
    }
    ```

