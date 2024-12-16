package ru.sorokinad.projectmanagement;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ru.sorokinad.projectmanagement.model.Project;
import ru.sorokinad.projectmanagement.model.User;
import ru.sorokinad.projectmanagement.repository.ProjectRepository;
import ru.sorokinad.projectmanagement.repository.UserRepository;
import ru.sorokinad.projectmanagement.service.UserProjectService;

import java.time.LocalDate;

@SpringBootApplication
@EnableJpaRepositories
@EnableTransactionManagement
public class ProjectManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectManagementApplication.class, args);
    }

    /**
     * Инициализация тестовых данных при запуске приложения
     * Активируется только с профилем "dev"
     */
    @Bean
    @Profile("dev")
    public CommandLineRunner initData(UserRepository userRepository, ProjectRepository projectRepository, UserProjectService userProjectService) {
        return args -> {

            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword("20cQQubK3.HZWzG3YB1tlRy.fqvM"); // password: admin
            admin.setEmail("admin@example.com");
            admin.setRole("ADMIN");
            userRepository.save(admin);

            User user = new User();
            user.setUsername("user");
            user.setPassword("20cQQubK3.HZWzG3YB1tlRy.fqvM"); // password: user
            user.setEmail("user@example.com");
            user.setRole("USER");
            userRepository.save(user);

            Project project1 = new Project();
            project1.setName("Project 1");
            project1.setDescription("First test project");
            project1.setCreatedDate(LocalDate.now());
            projectRepository.save(project1);

            Project project2 = new Project();
            project2.setName("Project 2");
            project2.setDescription("Second test project");
            project2.setCreatedDate(LocalDate.now());
            projectRepository.save(project2);


            userProjectService.addUserToProject(admin.getId(), project1.getId());
            userProjectService.addUserToProject(admin.getId(), project2.getId());
            userProjectService.addUserToProject(user.getId(), project1.getId());
        };
    }


    @Bean
    @Profile("dev")
    public CommandLineRunner setupLogging() {
        return args -> {
            System.setProperty("logging.level.org.hibernate.SQL", "DEBUG");
            System.setProperty("logging.level.org.hibernate.type.descriptor.sql.BasicBinder", "TRACE");
        };
    }
}