package tcs.interviewtracker.repository;

<<<<<<< HEAD
=======
import java.util.Optional;
>>>>>>> origin/dev
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import tcs.interviewtracker.persistence.User;

public interface UserRepository extends JpaRepository<User, Long> {
<<<<<<< HEAD
    public User getReferenceByUuid(UUID uuid);
=======
    Optional<User> findByUuid(UUID uuid);
    Optional<User> findByEmail(String email);
>>>>>>> origin/dev
}
