package ru.yandex.study.kanban;

import ru.yandex.study.kanban.model.Epic;
import ru.yandex.study.kanban.model.Subtask;
import ru.yandex.study.kanban.model.Task;
import ru.yandex.study.kanban.model.TaskStatus;
import ru.yandex.study.kanban.repository.TaskRepository;
import ru.yandex.study.kanban.service.Manager;

public class Main {

  public static void main(String[] args) {
    Manager manager = new Manager();
    manager.create(new Task("Переезд", "Собрать коробки", TaskStatus.NEW));
    manager.create(new Task("Почистить зубы", "надо -значит надо", TaskStatus.NEW));

    manager.create(new Epic("Задача 1", "Упаковать посуду в коробки", TaskStatus.NEW));
    manager.create(new Subtask("Задача 1.1", "Позвонить грузчикам", TaskStatus.NEW, 1L));

    manager.create(new Epic("Задача 2", "Погрузить в машину", TaskStatus.NEW));
    manager.create(new Subtask("Задача 2.1", "Погрузить хрупкое", TaskStatus.NEW, 1L));
    manager.create(new Subtask("Задача 2.2", "Погрузить важное", TaskStatus.NEW, 1L));

    System.out.println(manager);

  }
}