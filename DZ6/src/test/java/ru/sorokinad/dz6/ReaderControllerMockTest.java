package ru.sorokinad.dz6;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.sorokinad.dz6.conroller.ReaderController;

import ru.sorokinad.dz6.model.Reader;
import ru.sorokinad.dz6.service.ReaderService;

import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ReaderController.class) // Обязательно указываем, что тестируем ReaderController
class ReaderControllerMockTest {

 @Autowired
    private MockMvc mockMvc; // Для тестирования HTTP запросов к контроллеру

    @MockitoBean
    private ReaderService readerService; // Мокаем ReaderService

    @Test
    void test_getAllReaders() throws Exception {
        // Заготовляем мокированный ответ
        Mockito.when(readerService.getAllReaders()).thenReturn(Arrays.asList(
                new Reader(1L, "Alice", "alice@example.com", null),
                new Reader(2L, "Bob", "bob@example.com", null)
        ));

        // Выполняем GET запрос
        mockMvc.perform(get("/api/readers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2))) // Проверяем, что два элемента
                .andExpect(jsonPath("$[0].name", is("Alice")))
                .andExpect(jsonPath("$[1].email", is("bob@example.com")));

        // Убеждаемся, что сервис был вызван
        Mockito.verify(readerService, Mockito.times(1)).getAllReaders();
    }

    @Test
    void test_getReaderById() throws Exception {
        // Мокаем результат для ID = 1
        Mockito.when(readerService.getReaderById(1L))
                .thenReturn(Optional.of(new Reader(1L, "Charlie", "charlie@example.com", null)));

        // Выполняем GET запрос
        mockMvc.perform(get("/api/readers/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Charlie")))
                .andExpect(jsonPath("$.email", is("charlie@example.com")));

        // Убеждаемся, что сервис был вызван только один раз
        Mockito.verify(readerService, Mockito.times(1)).getReaderById(1L);
    }

    @Test
    void test_createReader() throws Exception {
        // Нового пользователя, который мы хотим создать
        Reader newReader = new Reader(null, "Dave", "dave@example.com", null);

        // Мокаем результат, когда вызывается saveReader
        Mockito.when(readerService.saveReader(any(Reader.class)))
                .thenReturn(new Reader(1L, "Dave", "dave@example.com", null));

        // Выполняем POST запрос
        mockMvc.perform(post("/api/readers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Dave\", \"email\":\"dave@example.com\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Dave")))
                .andExpect(jsonPath("$.email", is("dave@example.com")));

        // Проверяем, что `saveReader` был вызван с нужными параметрами
        Mockito.verify(readerService, Mockito.times(1)).saveReader(any(Reader.class));
    }


}
