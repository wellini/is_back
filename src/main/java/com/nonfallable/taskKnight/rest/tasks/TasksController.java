package com.nonfallable.taskKnight.rest.tasks;

import com.nonfallable.taskKnight.exceptions.NotFoundException;
import com.nonfallable.taskKnight.models.Profile;
import com.nonfallable.taskKnight.models.Task;
import com.nonfallable.taskKnight.repositories.TaskRepository;
import com.nonfallable.taskKnight.rest.tasks.converters.TaskConverterRequest;
import com.nonfallable.taskKnight.rest.tasks.dto.TaskResponseDTO;
import com.nonfallable.taskKnight.services.ProfileService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static org.springframework.http.ResponseEntity.ok;

@RestController
public class TasksController {

    private final TaskConverterRequest taskConverter;
    private final ProfileService profileService;
    private final TaskRepository taskRepository;

    public TasksController(TaskConverterRequest taskConverter, ProfileService profileService, TaskRepository taskRepository) {
        this.taskConverter = taskConverter;
        this.profileService = profileService;
        this.taskRepository = taskRepository;
    }

    @GetMapping("/1.0/listTasks")
    public ResponseEntity<Page<TaskResponseDTO>> listTasks(Pageable pageable) {
        Profile author = profileService.getAuthorizedUserProfile();
        return ok(taskConverter.fromDomain(taskRepository.findByAuthor(author, pageable)));
    }

    @GetMapping("/1.0/getTask/{taskId}")
    public ResponseEntity<TaskResponseDTO> getTask(@PathVariable UUID taskId) {
        Profile author = profileService.getAuthorizedUserProfile();
        Task task = taskRepository.findByIdAndAuthor(taskId, author)
                .orElseThrow(() -> new NotFoundException("Task not found"));
        return ok(taskConverter.fromDomain(task));
    }
}
