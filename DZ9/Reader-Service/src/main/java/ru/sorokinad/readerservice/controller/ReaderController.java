package ru.sorokinad.readerservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.sorokinad.readerservice.model.Reader;
import ru.sorokinad.readerservice.service.ReaderService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/readers")
@Tag(name = "Readers", description = "API для управления читателями")
//@RequiredArgsConstructor
public class ReaderController {

    private final ReaderService readerService;

    public ReaderController(ReaderService readerService) {
        this.readerService = readerService;
    }


    @Operation(summary = "Получить список всех читателей", description = "Возвращает полный список читателей")
    @GetMapping
    public ResponseEntity<List<Reader>> getAllReaders() {
        return ResponseEntity.ok(readerService.getAllReaders());
    }

    @Operation(summary = "Получить данные читателя", description = "Возвращает данные читателя по ID.")
    @GetMapping("/{id}")
    public ResponseEntity<Reader> getReaderById(@PathVariable Long id) {
        Optional<Reader> reader = readerService.getReaderById(id);
        return reader.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @Transactional
    @Operation(summary = "Добавить нового читателя", description = "Добавляет нового читателя в библиотеку")
    @PostMapping
    public ResponseEntity<Reader> addReader(@RequestBody Reader reader) {
        return ResponseEntity.ok(readerService.saveReader(reader));
    }
    @Transactional
    @Operation(summary = "Удалить читателя", description = "Удаляет читателя из библиотеки")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReader(@PathVariable Long id) {
        readerService.deleteReaderAndUnassignBooks(id);
        return ResponseEntity.noContent().build();
    }
}