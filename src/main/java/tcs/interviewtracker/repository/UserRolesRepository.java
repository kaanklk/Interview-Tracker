package tcs.interviewtracker.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import tcs.interviewtracker.persistence.Project;
import tcs.interviewtracker.persistence.User;
import tcs.interviewtracker.persistence.UserRoles;

public interface UserRolesRepository extends JpaRepository<UserRoles, Long> {

    List<UserRoles> findByUser(User user);

    Optional<UserRoles> findByUserAndProject(User user, Project project);

    List<UserRoles> findByProject(Project project);

}
