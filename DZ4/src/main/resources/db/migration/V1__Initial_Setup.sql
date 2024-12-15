CREATE DATABASE librarydb;
USE librarydb;
CREATE TABLE book (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      title VARCHAR(255) NOT NULL,
                      author VARCHAR(255) NOT NULL,
                      year INT NOT NULL
);


CREATE TABLE reader (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        name VARCHAR(255) NOT NULL
);


CREATE TABLE issue (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       book_id BIGINT,
                       reader_id BIGINT,
                       borrowed_date DATE NOT NULL,
                       returned_date DATE,
                       FOREIGN KEY (book_id) REFERENCES book(id),
                       FOREIGN KEY (reader_id) REFERENCES reader(id)
);