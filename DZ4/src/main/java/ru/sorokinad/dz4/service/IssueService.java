package ru.sorokinad.dz4.service;

import org.springframework.stereotype.Service;
import ru.sorokinad.dz4.model.Issue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class IssueService {
    private final List<Issue> issues = new ArrayList<>();

    public IssueService(BookService bookService, ReaderService readerService) {
        // Initial issues
        issues.add(new Issue(bookService.findAll().get(0), readerService.getAllReaders().get(0), LocalDate.now().minusDays(2), null));
    }

    public List<Issue> getAllIssues() {
        return issues;
    }
}
