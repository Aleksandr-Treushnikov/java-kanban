package ru.yandex.study.kanban.repository;

import java.util.HashMap;
import java.util.List;
import ru.yandex.study.kanban.model.Epic;
import ru.yandex.study.kanban.model.Task;

public class EpicRepository implements Repository<Epic> {

  private final HashMap<Long, Epic> allEpics = new HashMap<>();
  private final SequenceGenerator sequenceGenerator = new SequenceGenerator();

  @Override
  public Epic save(Epic task) {
    if (task.getId() == null || !allEpics.containsKey(task.getId())) {
      long id = sequenceGenerator.getCurrentId();
      task.setId(id);
      allEpics.put(id, task);
      return task;
    }
    Epic existingTask = allEpics.get(task.getId());
    existingTask.setName(task.getName());
    existingTask.setDescription(task.getDescription());
    existingTask.setStatus(task.getStatus());

    return existingTask;
  }

  @Override
  public void delete(long id) {
    allEpics.remove(id);
  }

  @Override
  public Epic findById(long id) {
    return allEpics.get(id);
  }

  @Override
  public List<Epic> findAll() {
    return (List<Epic>) allEpics.values();
  }

  @Override
  public void deleteAll() {
    allEpics.clear();
  }
}
