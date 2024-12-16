package ru.sorokinad.projectmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sorokinad.projectmanagement.model.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
}