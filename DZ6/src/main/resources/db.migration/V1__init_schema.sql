-- Создание таблицы читателей (readers)
CREATE TABLE readers (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         name VARCHAR(255) NOT NULL,
                         email VARCHAR(255) NOT NULL UNIQUE
);

-- Создание таблицы книг (books)
CREATE TABLE books (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       title VARCHAR(255) NOT NULL,
                       author VARCHAR(255) NOT NULL,
                       publisher VARCHAR(255),
                       publication_year INT NOT NULL,
                       available_copies INT NOT NULL,
                       reader_id BIGINT,
                       FOREIGN KEY (reader_id) REFERENCES readers(id) ON DELETE SET NULL
);