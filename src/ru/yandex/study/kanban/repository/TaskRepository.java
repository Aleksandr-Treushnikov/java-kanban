package ru.yandex.study.kanban.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import ru.yandex.study.kanban.model.Task;

public class TaskRepository implements Repository<Task> {

    private final HashMap<Long, Task> allTasks = new HashMap<>();
    private final SequenceGenerator sequenceGenerator = new SequenceGenerator();


    @Override
    public Task save(Task task) {
        if (task.getId() == null || !allTasks.containsKey(task.getId())) {
            long id = sequenceGenerator.getCurrentId();
            task.setId(id);
            allTasks.put(id, task);
            return task;
        }
        Task existingTask = allTasks.get(task.getId());
        existingTask.setName(task.getName());
        existingTask.setDescription(task.getDescription());
        existingTask.setStatus(task.getStatus());
        return existingTask;
    }

    @Override
    public void delete(long id) {

        allTasks.remove(id);
    }

    @Override
    public Task findById(long id) {

        return allTasks.get(id);
    }

    @Override
    public List<Task> findAll() {
        return new ArrayList<>(allTasks.values());
    }

    @Override
    public void deleteAll() {
        allTasks.clear();
    }
}
