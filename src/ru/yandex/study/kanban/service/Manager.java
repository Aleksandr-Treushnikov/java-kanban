package ru.yandex.study.kanban.service;

import java.util.List;
import ru.yandex.study.kanban.model.Epic;
import ru.yandex.study.kanban.model.Subtask;
import ru.yandex.study.kanban.model.Task;
import ru.yandex.study.kanban.repository.EpicRepository;
import ru.yandex.study.kanban.repository.Repository;
import ru.yandex.study.kanban.repository.SubtaskRepository;
import ru.yandex.study.kanban.repository.TaskRepository;

public class Manager {

  private final TaskRepository taskRepository = new TaskRepository();
  private final EpicRepository epicRepository = new EpicRepository();
  private final SubtaskRepository subtaskRepository = new SubtaskRepository();

  public Task create(Task task) {

    return getRepository(task.getClass()).save(task);
  }

  public Task update(Task task) {

    return null;
  }

  public void delete(long id) {

  }

  public Task getById(long id) {
    return null;
  }

  public List<Task> getAll() {
    return null;
  }

  public void deleteAll() {

  }

  public List<Task> getByEpicId(long epicId) {
    return null;
  }

  private Repository<? extends Task> getRepository(Class<? extends Task> taskClass) {
    if (taskClass.equals(Epic.class)) {
      return epicRepository;
    } else if (taskClass.equals(Subtask.class)) {
      return subtaskRepository;
    } else {
      return taskRepository;
    }
  }
}

