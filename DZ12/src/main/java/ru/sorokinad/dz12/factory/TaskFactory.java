package ru.sorokinad.dz12.factory;

import ru.sorokinad.dz12.task.Task;

public interface TaskFactory {
    Task createTask(String title, String description);
}
