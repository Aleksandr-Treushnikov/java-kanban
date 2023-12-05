package ru.yandex.study.kanban.repository;

public class SequenceGenerator {

  private long currentId=0L;


  public long getCurrentId() {
    synchronized (this){
      long id = currentId;
      currentId++;
      return id;
    }
  }
}
