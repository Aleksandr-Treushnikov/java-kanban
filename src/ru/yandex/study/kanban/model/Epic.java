package ru.yandex.study.kanban.model;

public class Epic extends Task {

  public Epic(String name, String description) {
    super(name, description, TaskStatus.NEW);
  }
}