package ru.yandex.study.kanban.repository;

import java.util.List;
import ru.yandex.study.kanban.model.Task;

public interface Repository<T extends Task> {


  T save(T task);

  void delete(long id);

  T findById(long id);

  List<T> findAll();

  void deleteAll();


}
