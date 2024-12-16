CREATE DATABASE project_management;
USE project_management;

CREATE TABLE users (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(50) NOT NULL UNIQUE,
                       password VARCHAR(100) NOT NULL,
                       email VARCHAR(100) NOT NULL UNIQUE,
                       role VARCHAR(50) NOT NULL
);


CREATE TABLE projects (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          name VARCHAR(100) NOT NULL,
                          description TEXT,
                          created_date DATE NOT NULL
);


CREATE TABLE users_projects (
                                id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                user_id BIGINT NOT NULL,
                                project_id BIGINT NOT NULL,
                                FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
                                FOREIGN KEY (project_id) REFERENCES projects(id) ON DELETE CASCADE
);
