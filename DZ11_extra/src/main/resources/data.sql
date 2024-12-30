
SET SCHEMA DZ12DB;

-- Вставка тестовых данных в таблицу task
INSERT INTO TASK (title, description, urgent) VALUES ('Подготовить отчет', 'Закончить отчёт до завтра', false);
INSERT INTO TASK (title, description, urgent) VALUES ('Позвонить клиенту', 'Договориться о презентации', true);
INSERT INTO TASK (title, description, urgent) VALUES ('Отправить документы', 'Отправить документы по почте', false);
