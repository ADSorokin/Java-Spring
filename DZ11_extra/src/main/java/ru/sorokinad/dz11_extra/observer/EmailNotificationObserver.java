package ru.sorokinad.dz11_extra.observer;


import ru.sorokinad.dz11_extra.task.Task;

public class EmailNotificationObserver implements TaskObserver {
    @Override
    public void update(Task task) {
        System.out.println("[Email] Уведомление: нова задача добавлена - " + task.getTitle());
    }


}