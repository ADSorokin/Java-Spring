package ru.sorokinad.dz12.factory;

import ru.sorokinad.dz12.task.Task;

public class UrgentTaskFactory implements TaskFactory {
    @Override
    public Task createTask(String title, String description) {
        return new Task(title, description, true);
    }
}
