package ru.sorokinad.dz10.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.sorokinad.dz10.model.Book;
import ru.sorokinad.dz10.model.Reader;
import ru.sorokinad.dz10.service.BookService;
import ru.sorokinad.dz10.service.ReaderBookService;
import ru.sorokinad.dz10.service.ReaderService;

@Controller
@RequestMapping("/readers")
public class ReaderController {

    private final ReaderService readerService;
    private final BookService bookService;
    private final ReaderBookService readerBookService;

    public ReaderController(ReaderService readerService, BookService bookService, ReaderBookService readerBookService) {
        this.readerService = readerService;
        this.bookService = bookService;
        this.readerBookService = readerBookService;
    }

    @GetMapping
    public String getReadersPage(Model model) {
        model.addAttribute("readers", readerService.getAllReaders());
        return "readers"; // Возвращает страницу со списком читателей
    }
//
//    @PostMapping("/add")
//    public String addReader(@Valid @ModelAttribute Reader reader) {
//        readerService.saveReader(reader);
//        return "redirect:/readers"; // Перенаправление после добавления
//    }


    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("reader", new Reader());
        return "readers";
    }

    @PostMapping("/add")
    public String addReader(@ModelAttribute Reader reader, RedirectAttributes redirectAttributes) {
        try {
            ReaderService.saveReder(reader);
            redirectAttributes.addFlashAttribute("successMessage", "Читатель успешно добавлена");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Ошибка при добавлении читателя");
        }
        return "redirect:/readers";
    }



    @GetMapping("/edit/{id}")
    public String editReaderForm(@PathVariable Long id, Model model) {
        Reader reader = readerService.getReaderById(id);
        model.addAttribute("reader", reader);
        return "edit-reader"; // Возвращает страницу редактирования читателя
    }

    @PostMapping("/edit/{id}")
    public String editReader(@PathVariable Long id, @Valid @ModelAttribute Reader reader) {
        reader.setId(id); // Устанавливаем ID для обновления
        readerService.saveReader(reader);
        return "redirect:/readers"; // Перенаправление после редактирования
    }



    @PostMapping("/delete/{id}")
    public String deleteReader(@PathVariable Long id) {
        readerService.deleteReader(id);
        return "redirect:/readers";
    }

    @GetMapping("/reader-books")
    public String issueReaderForm(@PathVariable Long id, Model model) {
        Reader reader = readerService.getReaderById(id);
        model.addAttribute("reader", reader);
        return "edit-reader"; // Возвращает страницу редактирования читателя
    }

    @PostMapping("/{readerId}/borrow/{bookId}")
    public String borrowBook(@PathVariable Long readerId, @PathVariable Long bookId, Model model) {
        try {


            model.addAttribute("message", "Книга успешно заимствована!");
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
        }
        return "redirect:/readers";
    }

    @PostMapping("/{readerId}/return/{bookId}")
    public String returnBook(@PathVariable Long readerId, @PathVariable Long bookId, Model model) {
        try {
            Reader reader = readerService.getReaderById(readerId);
            Book book = bookService.getBookById(bookId);

            readerBookService.returnBook(bookId,readerId);


            model.addAttribute("message", "Книга успешно возвращена в библиотеку!");
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
        }
        return "redirect:/readers";
    }
}
