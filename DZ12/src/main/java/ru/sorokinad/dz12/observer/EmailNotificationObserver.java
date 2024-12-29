package ru.sorokinad.dz12.observer;


import ru.sorokinad.dz12.task.Task;

public class EmailNotificationObserver implements TaskObserver {
    @Override
    public void update(Task task) {
        System.out.println("[Email] Уведомление: нова задача добавлена - " + task.getTitle());
    }


}