package ru.sorokinad.projectmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sorokinad.projectmanagement.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
