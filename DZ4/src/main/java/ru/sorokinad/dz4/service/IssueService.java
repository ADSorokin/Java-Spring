package ru.sorokinad.dz4.service;

import org.springframework.stereotype.Service;
import ru.sorokinad.dz4.model.Book;
import ru.sorokinad.dz4.model.Issue;
import ru.sorokinad.dz4.model.Reader;
import ru.sorokinad.dz4.repository.IssueRepository;

import java.time.LocalDate;
import java.util.List;


@Service
public class IssueService {
    private final IssueRepository issueRepository;

    public IssueService(IssueRepository issueRepository) {
        this.issueRepository = issueRepository;
    }


    public List<Issue> getAllIssues() {
        return issueRepository.findAll();
    }

    public Issue save(Issue issue) {
        return issueRepository.save(issue);
    }


    public Issue issueBook(Book book, Reader reader) {
        Issue issue = new Issue(book, reader, LocalDate.now(), null);
        return issueRepository.save(issue);
    }
}
