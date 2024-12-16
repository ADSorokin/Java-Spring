package ru.sorokinad.projectmanagement.service;

import org.springframework.stereotype.Service;
import ru.sorokinad.projectmanagement.model.Project;
import ru.sorokinad.projectmanagement.model.User;
import ru.sorokinad.projectmanagement.model.UsersProject;
import ru.sorokinad.projectmanagement.repository.ProjectRepository;
import ru.sorokinad.projectmanagement.repository.UserRepository;
import ru.sorokinad.projectmanagement.repository.UsersProjectRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserProjectService {

    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final UsersProjectRepository usersProjectRepository;

    public UserProjectService(UserRepository userRepository, ProjectRepository projectRepository, UsersProjectRepository usersProjectRepository) {
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
        this.usersProjectRepository = usersProjectRepository;
    }

    public List<User> getUsersByProjectId(Long projectId) {
        List<UsersProject> usersProjects = usersProjectRepository.findByProjectId(projectId);
        return usersProjects.stream()
                .map(up -> userRepository.findById(up.getUserId()))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    public List<Project> getProjectsByUserId(Long userId) {
        List<UsersProject> usersProjects = usersProjectRepository.findByUserId(userId);
        return usersProjects.stream()
                .map(up -> projectRepository.findById(up.getProjectId()))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    public void addUserToProject(Long userId, Long projectId) {
        if (!userRepository.existsById(userId)) {
            throw new IllegalArgumentException("User with id " + userId + " does not exist");
        }
        if (!projectRepository.existsById(projectId)) {
            throw new IllegalArgumentException("Project with id " + projectId + " does not exist");
        }

        UsersProject usersProject = new UsersProject();
        usersProject.setUserId(userId);
        usersProject.setProjectId(projectId);
        usersProjectRepository.save(usersProject);
    }

    public void removeUserFromProject(Long userId, Long projectId) {
        if (!userRepository.existsById(userId)) {
            throw new IllegalArgumentException("User with id " + userId + " does not exist");
        }
        if (!projectRepository.existsById(projectId)) {
            throw new IllegalArgumentException("Project with id " + projectId + " does not exist");
        }

        usersProjectRepository.deleteByUserIdAndProjectId(userId, projectId);
    }
}
