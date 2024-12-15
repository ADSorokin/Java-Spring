package ru.sorokinad.dz4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.sorokinad.dz4.model.Book;
import ru.sorokinad.dz4.model.Issue;
import ru.sorokinad.dz4.model.Reader;
import ru.sorokinad.dz4.service.BookService;
import ru.sorokinad.dz4.service.IssueService;
import ru.sorokinad.dz4.service.ReaderService;

import java.time.LocalDate;

@Controller
@RequestMapping("/ui")
public class UIController {

    private final BookService bookService;
    private final ReaderService readerService;
    private final IssueService issueService;

    public UIController(BookService bookService, ReaderService readerService, IssueService issueService) {
        this.bookService = bookService;
        this.readerService = readerService;
        this.issueService = issueService;
    }


    @GetMapping("/books")
    public String getBooksPage(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "books";
    }


    @GetMapping("/books/new")
    public String createBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "book-form";
    }


    @PostMapping("/books/new")
    public String saveBook(@ModelAttribute Book book) {
        bookService.save(book);
        return "redirect:/ui/books";
    }

    @GetMapping("/books/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.delete(id);
        return "redirect:/ui/books";
    }


    @GetMapping("/readers")
    public String getReadersPage(Model model) {
        model.addAttribute("readers", readerService.getAllReaders());
        return "readers";
    }


    @GetMapping("/readers/new")
    public String createReaderForm(Model model) {
        model.addAttribute("reader", new Reader());
        return "reader-form";
    }


    @PostMapping("/readers/new")
    public String saveReader(@ModelAttribute Reader reader) {
        readerService.save(reader);
        return "redirect:/ui/readers";
    }


    @GetMapping("/readers/delete/{id}")
    public String deleteReader(@PathVariable Long id) {
        readerService.delete(id);
        return "redirect:/ui/readers";
    }


    @GetMapping("/issues")
    public String getIssuesPage(Model model) {
        model.addAttribute("issues", issueService.getAllIssues());
        return "issues";
    }


    @GetMapping("/issues/new")
    public String createIssueForm(Model model) {
        model.addAttribute("readers", readerService.getAllReaders());
        model.addAttribute("books", bookService.findAll());
        model.addAttribute("issue", new Issue());
        return "issue-form";
    }


    @PostMapping("/issues/new")
    public String saveIssue(@RequestParam Long bookId, @RequestParam Long readerId) {
        Book book = bookService.findById(bookId).orElseThrow();
        Reader reader = readerService.findById(readerId).orElseThrow();
        Issue issue = new Issue(book, reader, LocalDate.now(), null);

        issueService.save(issue);
        return "redirect:/ui/issues";
    }
}
