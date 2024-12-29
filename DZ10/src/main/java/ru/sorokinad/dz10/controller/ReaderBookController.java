package ru.sorokinad.dz10.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.sorokinad.dz10.service.BookService;
import ru.sorokinad.dz10.service.ReaderBookService;
import ru.sorokinad.dz10.service.ReaderService;


@Controller
@RequestMapping("/reader-books")
@RequiredArgsConstructor
public class ReaderBookController {
    private final ReaderBookService readerBookService;
    private final ReaderService readerService;
    private final BookService bookService;

    @GetMapping
    public String showReaderBooksPage(Model model) {
        model.addAttribute("readers", readerService.getAllReaders());
        model.addAttribute("availableBooks", bookService.getAvailableBooks());
        model.addAttribute("readerBooks", readerBookService.getAllReaderBooks());
        return "reader-books";
    }

    @PostMapping("/assign")
    public String assignBook(@RequestParam Long readerId, @RequestParam Long bookId, RedirectAttributes redirectAttributes) {
        try {
            readerBookService.assignBookToReader(readerId, bookId);
            redirectAttributes.addFlashAttribute("successMessage", "Книга успешно выдана читателю");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Ошибка при выдаче книги: " + e.getMessage());
        }
        return "redirect:/reader-books";
    }

    @PostMapping("/return")
    public String returnBook(@RequestParam Long readerId, @RequestParam Long bookId, RedirectAttributes redirectAttributes) {
        try {
            readerBookService.returnBook(readerId, bookId);
            redirectAttributes.addFlashAttribute("successMessage", "Книга успешно возвращена");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Ошибка при возврате книги: " + e.getMessage());
        }
        return "redirect:/reader-books";
    }

    @GetMapping("/reader/{readerId}")
    public String getReaderBooks(@PathVariable Long readerId, Model model) {
        model.addAttribute("readerBooks", readerBookService.getBooksByReader(readerId));
        return "reader-books";
    }

    @GetMapping("/book/{bookId}")
    public String getBookReaders(@PathVariable Long bookId, Model model) {
        model.addAttribute("readerBooks", readerBookService.getReadersByBook(bookId));
        return "reader-books";
    }
}


