package ru.sorokinad.projectmanagement.model;

import jakarta.persistence.*;
import lombok.Data;


import jakarta.persistence.*;

@Entity
@Table(name = "users_projects")
public class UsersProject extends EntityWithRelation {
    @Column(nullable = false)
    private Long projectId;

    @Column(nullable = false)
    private Long userId;


    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
