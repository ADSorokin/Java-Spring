package ru.sorokinad.dz11;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;

import static org.aspectj.bridge.MessageUtil.fail;
import static org.junit.jupiter.api.Assertions.assertNotNull;




    @SpringBootTest
    class MySQLConnectionTest {

        @Autowired
        private DataSource dataSource;

        @Test
        void testConnection() {
            assertNotNull(dataSource, "DataSource не должен быть null");

            try (var connection = dataSource.getConnection()) {
                assertNotNull(connection, "Подключение к базе данных не установлено");
                System.out.println("Успешное подключение к базе данных MySQL: " + connection.getMetaData().getURL());
            } catch (Exception e) {
                fail("Не удалось подключиться к базе данных: " + e.getMessage());
            }
        }
    }

