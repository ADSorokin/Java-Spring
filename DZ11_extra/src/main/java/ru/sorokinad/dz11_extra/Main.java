package ru.sorokinad.dz11_extra;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.sorokinad.dz11_extra.factory.RegularTaskFactory;
import ru.sorokinad.dz11_extra.factory.TaskFactory;
import ru.sorokinad.dz11_extra.factory.UrgentTaskFactory;
import ru.sorokinad.dz11_extra.manager.TaskManager;
import ru.sorokinad.dz11_extra.observer.EmailNotificationObserver;
import ru.sorokinad.dz11_extra.observer.LogObserver;
import ru.sorokinad.dz11_extra.task.Task;

@SpringBootApplication
public class Main {


    public static void main(String[] args) {

        SpringApplication.run(Main.class, args);
//        // 1. Получаем единственный экземпляр TaskManager (Singleton)
//        TaskManager taskManager = TaskManager.getInstance();
//
//        // 2. Добавляем наблюдателей для уведомления
//        taskManager.subscribe(new EmailNotificationObserver());
//        taskManager.subscribe(new LogObserver());
//
//        // 3. Создаем фабрики задач
//        TaskFactory regularTaskFactory = new RegularTaskFactory();
//        TaskFactory urgentTaskFactory = new UrgentTaskFactory();
//
//        // 4. Создаем задачи через фабрики
//        Task regularTask = regularTaskFactory.createTask("Регулярная задача", "Описание обычной задачи");
//        Task urgentTask = urgentTaskFactory.createTask("Срочная задача", "Описание срочной задачи");
//
//        // 5. Добавляем задачи в TaskManager
//        System.out.println("Добавляем задачи:");
//        taskManager.addTask(regularTask);
//        taskManager.addTask(urgentTask);
//
//        // 6. Выводим все задачи
//        System.out.println("\nВсе задачи:");
//        for (Task task : taskManager.getTasks()) {
//            System.out.println("- " + task.getTitle() + (task.isUrgent() ? " (Срочная)" : " (Обычная)"));
//        }
    }
}
