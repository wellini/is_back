package com.nonfallable.taskKnight.rest.tasks;

import com.nonfallable.taskKnight.exceptions.NotFoundException;
import com.nonfallable.taskKnight.models.Profile;
import com.nonfallable.taskKnight.models.Task;
import com.nonfallable.taskKnight.repositories.TaskRepository;
import com.nonfallable.taskKnight.rest.dto.ApiSuccessDTO;
import com.nonfallable.taskKnight.rest.dto.PageDTO;
import com.nonfallable.taskKnight.rest.tasks.converters.TaskRequestConverter;
import com.nonfallable.taskKnight.rest.tasks.converters.TaskResponseConverter;
import com.nonfallable.taskKnight.rest.tasks.dto.TaskRequestDTO;
import com.nonfallable.taskKnight.rest.tasks.dto.TaskResponseDTO;
import com.nonfallable.taskKnight.rest.tasks.validators.SaveTaskRequestValidator;
import com.nonfallable.taskKnight.services.ProfileService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;
import java.util.UUID;

import static org.springframework.http.ResponseEntity.ok;

@RestController
public class TasksController {

    private final TaskResponseConverter taskConverter;
    private final ProfileService profileService;
    private final TaskRepository taskRepository;
    private final TaskRequestConverter taskRequestConverter;
    private final SaveTaskRequestValidator saveTaskRequestValidator;

    public TasksController(TaskResponseConverter taskConverter, ProfileService profileService, TaskRepository taskRepository, TaskRequestConverter taskRequestConverter, SaveTaskRequestValidator saveTaskRequestValidator) {
        this.taskConverter = taskConverter;
        this.profileService = profileService;
        this.taskRepository = taskRepository;
        this.taskRequestConverter = taskRequestConverter;
        this.saveTaskRequestValidator = saveTaskRequestValidator;
    }

    @GetMapping("/1.0/listTasks")
    public ResponseEntity<PageDTO<TaskResponseDTO>> listTasks(@PageableDefault Pageable pageable) {
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

    @PostMapping("/1.0/createTask")
    public ResponseEntity<TaskResponseDTO> createTask(@RequestBody TaskRequestDTO dto) {
        saveTaskRequestValidator.validate(dto);
        Profile author = profileService.getAuthorizedUserProfile();
        Task task = taskRequestConverter.toDomain(dto);
        task.setAuthor(author);
        return ok(taskConverter.fromDomain(taskRepository.save(task)));
    }

    @PutMapping("/1.0/updateTask/{taskId}")
    @Transactional
    public ResponseEntity<TaskResponseDTO> updateTask(@PathVariable UUID taskId, @RequestBody TaskRequestDTO dto) {
        Profile author = profileService.getAuthorizedUserProfile();
        taskRepository.findByIdAndAuthor(taskId, author)
                .orElseThrow(() -> new NotFoundException("Task not found"));
        saveTaskRequestValidator.validate(dto);
        Task task = taskRequestConverter.toDomain(dto);
        task.setId(taskId)
            .setAuthor(author)
            .setUpdatedAt(ZonedDateTime.now());
        return ok(taskConverter.fromDomain(taskRepository.save(task)));
    }

    @DeleteMapping("/1.0/deleteTask/{taskId}")
    @Transactional
    public ResponseEntity<ApiSuccessDTO> deleteTask(@PathVariable UUID taskId) {
        Profile author = profileService.getAuthorizedUserProfile();
        taskRepository.findByIdAndAuthor(taskId, author)
                .orElseThrow(() -> new NotFoundException("Task not found"));
        taskRepository.deleteById(taskId);
        return ok(new ApiSuccessDTO().setMessage("Success deletion"));
    }
}
