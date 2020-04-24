package com.nonfallable.taskKnight.repositories;

import com.nonfallable.taskKnight.models.Profile;
import com.nonfallable.taskKnight.models.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {

    Page<Task> findByAuthor(Profile author, Pageable pageable);

    Optional<Task> findByIdAndAuthor(UUID id, Profile author);
}
