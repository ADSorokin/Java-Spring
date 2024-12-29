package ru.sorokinad.dz12.observer;

import ru.sorokinad.dz12.task.Task;

public class LogObserver implements TaskObserver {
    @Override
    public void update(Task task) {
        System.out.println("[Log] Задача обновлена: " + task.getTitle());
    }
}
