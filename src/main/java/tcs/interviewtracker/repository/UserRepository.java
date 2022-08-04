package tcs.interviewtracker.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import tcs.interviewtracker.persistence.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUuid(UUID uuid);

    Optional<User> findByEmail(String email);

}
