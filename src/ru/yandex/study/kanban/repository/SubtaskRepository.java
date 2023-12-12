package ru.yandex.study.kanban.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import ru.yandex.study.kanban.model.Subtask;

public class SubtaskRepository implements Repository<Subtask> {

    private final HashMap<Long, Subtask> allSubtasks = new HashMap<>();
    private final HashMap<Long, Set<Subtask>> subtasksByEpicId = new HashMap<>();
    private final SequenceGenerator sequenceGenerator = new SequenceGenerator();

    public Set<Subtask> findByEpicId(Long epicId) {
        return subtasksByEpicId.get(epicId);

    }

    @Override
    public Subtask save(Subtask task) {
        if (task.getId() == null || !allSubtasks.containsKey(task.getId())) {
            long id = sequenceGenerator.getCurrentId();
            task.setId(id);
            allSubtasks.put(id, task);
            addToEpicMap(task);
            return task;
        }
        Subtask existingTask = allSubtasks.get(task.getId());
        existingTask.setName(task.getName());
        existingTask.setDescription(task.getDescription());
        existingTask.setStatus(task.getStatus());
        return existingTask;
    }

    private void addToEpicMap(Subtask task) {
        subtasksByEpicId.compute(task.getEpicId(), (key, value) -> {
            if (value == null) {
                HashSet<Subtask> tasks = new HashSet<>();
                tasks.add(task);
                return tasks;
            }
            value.add(task);
            return value;
        });
    }

    @Override
    public void delete(long id) {
        allSubtasks.remove(id);
    }

    public void deleteSubtaskByEpicId(Long epicId) {
        Set<Subtask> existingSubtask = subtasksByEpicId.get(epicId);
        for (Subtask subtask : existingSubtask) {
            allSubtasks.remove(subtask.getId());
        }
        subtasksByEpicId.remove(epicId);
    }


    @Override
    public Subtask findById(long id) {
        return allSubtasks.get(id);
    }

    @Override
    public List<Subtask> findAll() {
        return new ArrayList<>(allSubtasks.values());
    }

    @Override
    public void deleteAll() {
        allSubtasks.clear();
    }
}
