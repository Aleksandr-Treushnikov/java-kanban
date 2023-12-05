package ru.yandex.study.kanban.repository;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import ru.yandex.study.kanban.model.Subtask;
import ru.yandex.study.kanban.model.Task;

public  class SubtaskRepository implements Repository<Subtask>{

 private final HashMap<Long, Subtask> allSubtasks = new HashMap<>();
 private final SequenceGenerator sequenceGenerator = new SequenceGenerator();

public List<Subtask> findByEpicId(Long epicId){
 List<Subtask> searh = new ArrayList<>();
 for (Subtask value : allSubtasks.values()) {
  if(value.getEpicId() == epicId){
   searh.add(value);
  }
 }
 return searh;
}


 @Override
 public Subtask save(Subtask task) {
  if(task.getId() == null || !allSubtasks.containsKey(task.getId())) {
   long id = sequenceGenerator.getCurrentId();
   task.setId(id);
   allSubtasks.put(id, task);
   return task;
  }
  Subtask existingTask = allSubtasks.get(task.getId());
  existingTask.setName(task.getName());
  existingTask.setDescription(task.getDescription());
  existingTask.setStatus(task.getStatus());
  return existingTask;
 }


 @Override
 public void delete(long id) {
  allSubtasks.remove(id);
 }

 @Override
 public Subtask findById(long id) {
 return allSubtasks.get(id);
 }

 @Override
 public List<Subtask> findAll() {
  return (List<Subtask>) allSubtasks.values();
 }

 @Override
 public void deleteAll() {
   allSubtasks.clear();
 }
}
