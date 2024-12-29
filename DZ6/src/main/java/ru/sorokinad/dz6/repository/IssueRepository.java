package ru.sorokinad.dz6.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.sorokinad.dz6.model.Issue;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Long> {
}