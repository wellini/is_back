package com.nonfallable.taskKnight.repositories;

import com.nonfallable.taskKnight.models.PasswordChanging;
import com.nonfallable.taskKnight.models.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PasswordChangingRepository extends JpaRepository<PasswordChanging, UUID> {
    Optional<PasswordChanging> findBySubject(Profile subject);
}
