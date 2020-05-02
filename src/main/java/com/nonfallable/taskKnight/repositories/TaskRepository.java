package com.nonfallable.taskKnight.repositories;

import com.nonfallable.taskKnight.models.Profile;
import com.nonfallable.taskKnight.models.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {

    Page<Task> findByAuthor(Profile author, Pageable pageable);

    Optional<Task> findByIdAndAuthor(UUID id, Profile author);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value = "1000")})
    @Transactional(propagation = Propagation.MANDATORY)
    Optional<Task> findAndLockByIdAndAuthor(UUID id, Profile author);

    void deleteAllByAuthor(Profile author);
}
