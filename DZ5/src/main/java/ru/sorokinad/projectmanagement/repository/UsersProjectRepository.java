package ru.sorokinad.projectmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sorokinad.projectmanagement.model.UsersProject;

import java.util.List;



@Repository
public interface UsersProjectRepository extends JpaRepository<UsersProject, Long> {
    List<UsersProject> findByProjectId(Long projectId);
    List<UsersProject> findByUserId(Long userId);
    List<UsersProject> findByUserIdAndProjectId(Long userId, Long projectId);
    void deleteByUserIdAndProjectId(Long userId, Long projectId);
}