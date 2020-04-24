package com.nonfallable.taskKnight.rest.tasks.converters;

import com.nonfallable.taskKnight.models.Task;
import com.nonfallable.taskKnight.rest.tasks.dto.TaskResponseDTO;
import com.nonfallable.taskKnight.utils.converters.RequestDtoConverter;
import org.springframework.stereotype.Component;

@Component
public class TaskConverterRequest extends RequestDtoConverter<TaskResponseDTO, Task> {

    @Override
    public TaskResponseDTO fromDomain(Task task) {
        return new TaskResponseDTO(
                task.getId(),
                task.getTitle(),
                task.getTitle(),
                task.getImportance(),
                task.getUrgency(),
                task.getAuthor(),
                task.getCreatedAt(),
                task.getUpdatedAt()
        );
    }
}
