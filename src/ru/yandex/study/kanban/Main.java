package ru.yandex.study.kanban;


import java.util.List;
import java.util.Set;
import ru.yandex.study.kanban.model.Epic;
import ru.yandex.study.kanban.model.Subtask;
import ru.yandex.study.kanban.model.Task;
import ru.yandex.study.kanban.model.TaskStatus;
import ru.yandex.study.kanban.service.Manager;

public class Main {

  public static void main(String[] args) {
    Manager manager = new Manager();
    System.out.println("Cоздаем 1-ю таску.");
    Task newTask = manager.create(new Task("Переезд", "Собрать коробки", TaskStatus.NEW));
    System.out.println(newTask.toString());
    System.out.println();
    System.out.println("Создаем 2-ю таску.");
    Task newTask2 = manager.create(new Task("Почистить зубы", "надо - значит надо", TaskStatus.NEW));
    System.out.println(newTask2.toString());
    System.out.println();
    System.out.println("Создаем  1-й эпик.");
    Epic newEpic = manager.create(new Epic("Задача 1", "Упаковать посуду в коробки"));
    System.out.println(newEpic);
    System.out.println();
    System.out.println("Создаем 1-ю сабтаску.");
    Subtask newSubtask = manager.create(new Subtask("Задача 1.1", "Позвонить грузчикам", TaskStatus.NEW, 0L));
    System.out.println(newSubtask);
    System.out.println();
    System.out.println("Создаем  2-й эпик.");
    Epic newEpic2 = manager.create(new Epic("Задача 2", "Погрузить в машину"));
    System.out.println(newEpic2);
    System.out.println();
    System.out.println("Создаем  1-ю сабтаску.");
    Subtask newSubtask2 = manager.create(new Subtask("Задача 2.1", "Погрузить хрупкое", TaskStatus.NEW, 1L));
    System.out.println(newSubtask2);
    System.out.println();
    System.out.println("Создаем  2-ю сабтаску.");
    Subtask newSubtask3 = manager.create(new Subtask("Задача 2.2", "Погрузить важное", TaskStatus.NEW, 1L));
    System.out.println(newSubtask3);
    System.out.println();
    System.out.println("Получаем по id существующую  таску 1");
    Task existingTask1 = manager.getById(newTask.getId(), Task.class);
    System.out.println(existingTask1);
    System.out.println();
    System.out.println("Получаем по id существующую  таску 2");
    Task existingTask2 = manager.getById(newTask2.getId(), Task.class);
    System.out.println(existingTask2);
    System.out.println();
    System.out.println("Обновляем таску 1");
    Task newTask1 = manager.update(newTask);
    System.out.println(newTask1);
    System.out.println();
    System.out.println("Обновляем эпик  1");
    Task newEpic1 = manager.update(newEpic);
    System.out.println(newEpic1);
    System.out.println();
    System.out.println("Обновляем сабтаску 1");
    Task newSubtask1 = manager.update(newSubtask);
    System.out.println(newSubtask1);
    System.out.println();
    System.out.println("Удаляем по id существующую  таску 1");
    manager.delete(newTask.getId(), Task.class);
    System.out.println();
    System.out.println("Получаем весь список тасок");
    List<Task> allTask = (List<Task>) manager.getAll(Task.class);
    System.out.println(allTask);
    System.out.println();
    System.out.println("Удаляем все таски");
    manager.deleteAll(Task.class);
    System.out.println();
    System.out.println("Получаем список сабтасков по получаемым id эпиков ");
    Set<Subtask> subtasks = manager.getByEpicId(existingTask1.getId());
    System.out.println(subtasks);
  }
}