package tcs.interviewtracker.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import tcs.interviewtracker.persistence.User;

public interface UserRepository extends JpaRepository<User, Long> {
    public User getReferenceByUuid(UUID uuid);
}
