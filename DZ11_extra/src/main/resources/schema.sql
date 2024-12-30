-- Создание схемы базы данных
CREATE SCHEMA IF NOT EXISTS testdb;

-- Переход к схеме
SET SCHEMA testdb;

-- Создание таблицы внутри схемы
CREATE TABLE IF NOT EXISTS task (
                                    id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                    title VARCHAR(255) NOT NULL,
                                    description VARCHAR(255),
                                    urgent BOOLEAN DEFAULT FALSE
                                );