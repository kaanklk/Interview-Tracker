package tcs.interviewtracker.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import tcs.interviewtracker.persistence.UserRoles;

public interface UserRolesRepository extends JpaRepository<UserRoles,Long> {

    List<UserRoles> findByUserId(Long userId);
    Optional<UserRoles> findByUserIdAndProjectId(Long userId, Long projectId);
}
