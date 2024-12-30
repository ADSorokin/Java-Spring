-- Создание схемы базы данных
CREATE SCHEMA IF NOT EXISTS DZ12DB;

-- Переход к схеме
SET SCHEMA DZ12DB;

-- Создание таблицы внутри схемы
CREATE TABLE IF NOT EXISTS task (
                                    id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                    title VARCHAR(255) NOT NULL,
                                    description VARCHAR(255),
                                    urgent BOOLEAN DEFAULT FALSE
                                );