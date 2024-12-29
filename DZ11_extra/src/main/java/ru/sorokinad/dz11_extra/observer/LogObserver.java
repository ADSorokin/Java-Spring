package ru.sorokinad.dz11_extra.observer;

import ru.sorokinad.dz11_extra.task.Task;

public class LogObserver implements TaskObserver {
    @Override
    public void update(Task task) {
        System.out.println("[Log] Задача обновлена: " + task.getTitle());
    }
}
