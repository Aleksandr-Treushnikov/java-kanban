package ru.yandex.study.kanban.model;

import java.util.Objects;

public class Subtask extends Task {

    private final Long epicId;

    public Subtask(String name, String description, TaskStatus status, Long epicId) {
        super(name, description, status);
        this.epicId = epicId;
    }

    public long getEpicId() {
        return epicId;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Subtask subtask = (Subtask) object;
        return Objects.equals(epicId, subtask.epicId) && Objects.equals(super.getId(),
            subtask.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.getId(), epicId);
    }
}
