package ru.sorokinad.dz12;

import org.junit.jupiter.api.Test;
import ru.sorokinad.dz12.manager.TaskManager;

import static org.junit.jupiter.api.Assertions.*;

public class TaskManagerTest {
    @Test
    public void testSingletonInstance() {
        TaskManager instance1 = TaskManager.getInstance();
        TaskManager instance2 = TaskManager.getInstance();
        assertSame(instance1, instance2);
    }
}
