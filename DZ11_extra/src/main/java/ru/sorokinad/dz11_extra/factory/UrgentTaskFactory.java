package ru.sorokinad.dz11_extra.factory;

import ru.sorokinad.dz11_extra.task.Task;

public class UrgentTaskFactory implements TaskFactory {
    @Override
    public Task createTask(String title, String description) {
        return new Task(title, description, true);
    }
}
