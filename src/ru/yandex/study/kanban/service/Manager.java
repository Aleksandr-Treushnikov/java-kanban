package ru.yandex.study.kanban.service;

import static ru.yandex.study.kanban.model.TaskStatus.*;

import java.util.List;
import java.util.Set;
import ru.yandex.study.kanban.model.Epic;
import ru.yandex.study.kanban.model.Subtask;
import ru.yandex.study.kanban.model.Task;
import ru.yandex.study.kanban.model.TaskStatus;
import ru.yandex.study.kanban.repository.EpicRepository;
import ru.yandex.study.kanban.repository.Repository;
import ru.yandex.study.kanban.repository.SubtaskRepository;
import ru.yandex.study.kanban.repository.TaskRepository;

public class Manager {

    private final TaskRepository taskRepository = new TaskRepository();
    private final EpicRepository epicRepository = new EpicRepository();
    private final SubtaskRepository subtaskRepository = new SubtaskRepository();

    public Task create(Task task) {
        return taskRepository.save(task);
    }

    public Subtask create(Subtask task) {
        validate(task);
        Subtask subtask = subtaskRepository.save(task);
        changeEpicStatusIfNeeded(subtask.getEpicId());
        return subtask;
    }

    public Epic create(Epic task) {
        return epicRepository.save(task);
    }

    public Task update(Task task) {
        return taskRepository.save(task);
    }

    public Subtask update(Subtask task) {
        validate(task);
        Subtask subtask = subtaskRepository.save(task);
        changeEpicStatusIfNeeded(subtask.getEpicId());
        return subtask;
    }

    public Epic update(Epic task) {
        return epicRepository.save(task);
    }

    public void delete(long id, Class<? extends Task> taskClass) {
        if (taskClass == Subtask.class) {
            Subtask task = (Subtask) getById(id, Subtask.class);
            subtaskRepository.delete(id);
            changeEpicStatusIfNeeded(task.getEpicId());
        } else if (taskClass == Epic.class) {
            subtaskRepository.deleteSubtaskByEpicId(id);
            epicRepository.delete(id);
        } else {
            taskRepository.delete(id);
        }
    }

    public Task getById(long id, Class<? extends Task> taskClass) {
        return getRepository(taskClass).findById(id);
    }

    public List<? extends Task> getAll(Class<? extends Task> taskClass) {
        return getRepository(taskClass).findAll();
    }

    public void deleteAll(Class<? extends Task> taskClass) {
        if (taskClass == Subtask.class) {
            subtaskRepository.deleteAll();
            List<Epic> all = epicRepository.findAll();
            for (Epic epic : all) {
                epic.setStatus(NEW);
            }
        } else if (taskClass == Epic.class) {
            epicRepository.deleteAll();
            subtaskRepository.deleteAll();
        } else if (taskClass == Task.class) {
            taskRepository.deleteAll();
        }
    }

    public Set<Subtask> getByEpicId(long epicId) {
        return subtaskRepository.findByEpicId(epicId);
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

    private void changeEpicStatusIfNeeded(Long epicId) {

        Set<Subtask> subtasks = subtaskRepository.findByEpicId(epicId);
        boolean allSubtaskDone = true;
        boolean allSubtaskNew = true;
        for (Subtask subtask : subtasks) {
            if (!(subtask.getStatus().equals(NEW))) {
                allSubtaskNew = false;
            }
            if (!(subtask.getStatus().equals(DONE))) {
                allSubtaskDone = false;
            }
        }
        TaskStatus epicStatus;
        if (subtasks.isEmpty() || allSubtaskNew) {
            epicStatus = NEW;
        } else if (allSubtaskDone) {
            epicStatus = DONE;
        } else {
            epicStatus = IN_PROGRESS;
        }
        Epic epic = epicRepository.findById(epicId);
        epic.setStatus(epicStatus);

    }

    private void validate(Subtask task) {
        long epicId = task.getEpicId();
        Epic epic = epicRepository.findById(epicId);
        if (epic == null) {
            throw new RuntimeException("Эпик с таким id не существует");
        }
    }
}




