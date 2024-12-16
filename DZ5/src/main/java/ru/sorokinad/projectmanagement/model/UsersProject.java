package ru.sorokinad.projectmanagement.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "users_projects")
public class UsersProject extends EntityWithRelation {
    @Column(nullable = false)
    private Long projectId;

    @Column(nullable = false)
    private Long userId;
}
