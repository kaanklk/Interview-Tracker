package tcs.interviewtracker.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import tcs.interviewtracker.persistence.UserRoles;

public interface UserRolesRepository extends JpaRepository<UserRoles,Long> {


    Optional<UserRoles> findByUuid(UUID uuid);
    List<UserRoles> findByUserUuid(UUID userUuid);

}
