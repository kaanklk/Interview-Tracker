package tcs.interviewtracker.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tcs.interviewtracker.persistence.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    Optional<Project> findByUuid(UUID uuid);

    Optional<Project> getByUuid(UUID projectUuid);

    Optional<Project> getReferenceByUuid(UUID uuid);
}
