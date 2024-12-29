package ru.sorokinad.dz11_extra.observer;


import ru.sorokinad.dz11_extra.task.Task;

public interface TaskObserver {
    void update(Task task);
}