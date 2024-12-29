package ru.sorokinad.dz11_extra.manager;

import ru.sorokinad.dz11_extra.observer.TaskObserver;
import ru.sorokinad.dz11_extra.task.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private static TaskManager instance = new TaskManager(); // Singleton

    private List<Task> tasks = new ArrayList<>();
    private List<TaskObserver> observers = new ArrayList<>();

    private TaskManager() {}

    public static TaskManager getInstance() {
        return instance;
    }

    public void addTask(Task task) {
        tasks.add(task);
        notifyObservers(task); // Уведомить всех
    }

    public void subscribe(TaskObserver observer) {
        observers.add(observer);
    }

    public void unsubscribe(TaskObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers(Task task) {
        for (TaskObserver observer : observers) {
            observer.update(task);
        }
    }

    public List<Task> getTasks() {
        return new ArrayList<>(tasks);
    }
}
