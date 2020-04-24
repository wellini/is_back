package com.nonfallable.taskKnight.rest.tasks.converters;

import com.nonfallable.taskKnight.models.Task;
import com.nonfallable.taskKnight.rest.tasks.dto.TaskRequestDTO;
import com.nonfallable.taskKnight.utils.converters.ToDomainConverter;
import org.springframework.stereotype.Component;

@Component
public class TaskRequestConverter extends ToDomainConverter<TaskRequestDTO, Task> {

    @Override
    public Task toDomain(TaskRequestDTO dto) {
        return new Task().setTitle(dto.getTitle())
                .setText(dto.getText())
                .setImportance(dto.getImportance())
                .setUrgency(dto.getUrgency());
    }
}
