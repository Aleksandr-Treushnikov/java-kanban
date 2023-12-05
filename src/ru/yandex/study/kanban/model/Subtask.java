package ru.yandex.study.kanban.model;

public class Subtask extends Task {
  
  private Long epicId;

  public Subtask( String name, String description, TaskStatus status, Long epicId) {
    super(name, description, status);
    this.epicId = epicId ;
  }

  public long getEpicId() {
    return epicId;
  }

  public void setEpicId(Long epicId) {
    this.epicId = epicId;
  }
}
