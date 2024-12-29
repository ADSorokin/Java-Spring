package ru.sorokinad.dz10.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.sorokinad.dz10.model.Book;
import ru.sorokinad.dz10.service.BookService;

@Controller
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping
    public String listBooks(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        return "editbooks";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("book", new Book());
        return "editbooks";
    }

    @PostMapping("/add")
    public String addBook(@ModelAttribute Book book, RedirectAttributes redirectAttributes) {
        try {
            bookService.saveBook(book);
            redirectAttributes.addFlashAttribute("successMessage", "Книга успешно добавлена");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Ошибка при добавлении книги");
        }
        return "redirect:/books";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        try {
            model.addAttribute("book", bookService.getBookById(id));
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Книга не найдена");
        }
        return "/editbooks";
    }

    @PostMapping("/edit/{id}")
    public String updateBook(@PathVariable Long id, @ModelAttribute Book book,
                             RedirectAttributes redirectAttributes) {
        try {
            bookService.updateBook(id, book);
            redirectAttributes.addFlashAttribute("successMessage", "Книга успешно обновлена");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Ошибка при обновлении книги");
        }
        return "redirect:/books";
    }

    @PostMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            bookService.deleteBook(id);
            redirectAttributes.addFlashAttribute("successMessage", "Книга успешно удалена");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Ошибка при удалении книги");
        }
        return "redirect:/books";
    }
}