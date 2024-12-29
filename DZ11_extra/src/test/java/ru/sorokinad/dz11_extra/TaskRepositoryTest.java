package ru.sorokinad.dz11_extra;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.sorokinad.dz11_extra.task.Task;
import ru.sorokinad.dz11_extra.task.TaskRepository;

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