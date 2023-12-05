package ru.yandex.study.kanban.model;



public class Task {


   private long id;
  private   String name;
  private   String description;
  private  TaskStatus status;

  public Task(String name, String description, TaskStatus status) {

    this.name = name;
    this.description = description;
    this.status = status;
  }

  public Long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public TaskStatus getStatus() {
    return status;
  }

  public void setStatus(TaskStatus status) {
    this.status = status;
  }
}