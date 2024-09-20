CREATE TABLE users (
                       user_id INT PRIMARY KEY AUTO_INCREMENT,
                       name VARCHAR(255) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       emblem VARCHAR(255) NOT NULL default '',
                       reward_info INT DEFAULT 0
);

CREATE TABLE module (
                      module_id INT PRIMARY KEY AUTO_INCREMENT,
                      module_name VARCHAR(255) NOT NULL UNIQUE,
                      quiz_num INT NOT NULL DEFAULT 0,
                      resource_num INT NOT NULL DEFAULT 0,
                      card_num INT NOT NULL DEFAULT 0
);

CREATE TABLE process (
                       resource_id INT PRIMARY KEY AUTO_INCREMENT,
                       user_id INT NOT NULL,
                       module_id INT NOT NULL,
                       title VARCHAR(255) NOT NULL,
                       author VARCHAR(255) NOT NULL,
                       content TEXT,
                       FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
                       FOREIGN KEY (module_id) REFERENCES module(module_id) ON DELETE CASCADE
);


CREATE TABLE community_quiz (
                               question_id INT PRIMARY KEY AUTO_INCREMENT,
                               user_id INT NOT NULL,
                               module_id INT NOT NULL,
                               question VARCHAR(255) NOT NULL,
                               choice VARCHAR(600) NOT NULL,
                               answer INT NOT NULL,
                               average_rating FLOAT,
                               rated_num INT DEFAULT 0,
                               FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
                               FOREIGN KEY (module_id) REFERENCES module(module_id) ON DELETE CASCADE
);

CREATE TABLE personal_rate (
                               user_id INT NOT NULL,
                               question_id INT NOT NULL,
                               personal_rating FLOAT NOT NULL,
                               PRIMARY KEY (user_id, question_id),
                               FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
                               FOREIGN KEY (question_id) REFERENCES community_quiz(question_id) ON DELETE CASCADE
);

CREATE TABLE process (
                               user_id INT NOT NULL,
                               module_id INT NOT NULL,
                               resources_finished VARCHAR(255) default '',
                               cards_finished_num INT default 0,
                               quiz_passed INT default 0,
                               PRIMARY KEY (user_id, module_id),
                               FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
                               FOREIGN KEY (module_id) REFERENCES module(module_id) ON DELETE CASCADE
);

CREATE TABLE certificate (
                             certificate_id INT PRIMARY KEY AUTO_INCREMENT,
                             user_id INT NOT NULL,
                             receive_date DATETIME NOT NULL,
                             FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);



