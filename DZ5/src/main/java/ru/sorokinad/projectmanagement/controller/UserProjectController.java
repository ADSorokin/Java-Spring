package ru.sorokinad.projectmanagement.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sorokinad.projectmanagement.model.Project;
import ru.sorokinad.projectmanagement.model.User;
import ru.sorokinad.projectmanagement.service.UserProjectService;

import java.util.List;

@RestController
@RequestMapping("/api/user-projects")
public class UserProjectController {
    private final UserProjectService userProjectService;

    public UserProjectController(UserProjectService userProjectService) {
        this.userProjectService = userProjectService;
    }

    @GetMapping("/users/{projectId}")
    public ResponseEntity<List<User>> getUsersByProjectId(@PathVariable Long projectId) {
        List<User> users = userProjectService.getUsersByProjectId(projectId);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/projects/{userId}")
    public ResponseEntity<List<Project>> getProjectsByUserId(@PathVariable Long userId) {
        List<Project> projects = userProjectService.getProjectsByUserId(userId);
        return ResponseEntity.ok(projects);
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addUserToProject(
            @RequestParam("userId") Long userId,
            @RequestParam("projectId") Long projectId) {
        userProjectService.addUserToProject(userId, projectId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/remove")
    public ResponseEntity<Void> removeUserFromProject(
            @RequestParam("userId") Long userId,
            @RequestParam("projectId") Long projectId) {
        userProjectService.removeUserFromProject(userId, projectId);
        return ResponseEntity.ok().build();
    }
}