package ru.sorokinad.dz12.observer;


import ru.sorokinad.dz12.task.Task;

public interface TaskObserver {
    void update(Task task);
}