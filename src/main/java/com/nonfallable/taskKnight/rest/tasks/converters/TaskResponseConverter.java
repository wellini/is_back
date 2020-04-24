package com.nonfallable.taskKnight.rest.tasks.converters;

import com.nonfallable.taskKnight.models.Task;
import com.nonfallable.taskKnight.rest.tasks.dto.TaskResponseDTO;
import com.nonfallable.taskKnight.utils.converters.FromDomainConverter;
import org.springframework.stereotype.Component;

@Component
public class TaskResponseConverter extends FromDomainConverter<TaskResponseDTO, Task> {

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
