package ru.sorokinad.dz12;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.sorokinad.dz12.task.Task;
import ru.sorokinad.dz12.task.TaskRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TaskRepositoryTest {

    @Autowired
    private TaskRepository taskRepository;

    @Test
    public void testCreateTask() {
        Task task = new Task("Test Task", "Description", true);
        Task savedTask = taskRepository.save(task);

        assertNotNull(savedTask.getId());
    }
}