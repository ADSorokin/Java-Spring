package ru.sorokinad.dz11_extra.factory;

import ru.sorokinad.dz11_extra.task.Task;

public interface TaskFactory {
    Task createTask(String title, String description);
}
