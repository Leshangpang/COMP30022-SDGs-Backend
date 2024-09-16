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

CREATE TABLE resource (
                       resource_id INT PRIMARY KEY AUTO_INCREMENT,
                       user_id INT NOT NULL,
                       module_id INT NOT NULL,
                       title VARCHAR(255) NOT NULL,
                       author VARCHAR(255) NOT NULL,
                       content TEXT,
                       FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
                       FOREIGN KEY (module_id) REFERENCES module(module_id) ON DELETE CASCADE
);


CREATE TABLE quiz (
                          quiz_id INT PRIMARY KEY AUTO_INCREMENT,
                          user_id INT NOT NULL,
                          module_id INT NOT NULL,
                          question VARCHAR(255) NOT NULL,
                          choice VARCHAR(600) NOT NULL,
                          answer INT NOT NULL,
                          FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
                          FOREIGN KEY (module_id) REFERENCES module(module_id) ON DELETE CASCADE
);

CREATE TABLE user_quiz (
                           quiz_id INT PRIMARY KEY AUTO_INCREMENT,
                           user_id INT NOT NULL,
                           module_id INT NOT NULL,
                           question VARCHAR(255) NOT NULL,
                           choice VARCHAR(600) NOT NULL,
                           answer INT NOT NULL,
                           rating INT default 0,
                           FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
                           FOREIGN KEY (module_id) REFERENCES module(module_id) ON DELETE CASCADE
);

CREATE TABLE certificate (
                             certificate_id INT PRIMARY KEY AUTO_INCREMENT,
                             user_id INT NOT NULL,
                             receive_date DATETIME NOT NULL,
                             FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);