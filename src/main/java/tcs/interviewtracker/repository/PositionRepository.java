package tcs.interviewtracker.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import tcs.interviewtracker.persistence.Position;
import tcs.interviewtracker.persistence.Project;

public interface PositionRepository extends JpaRepository<Position, Long> {
    public Optional<Position> findByUuid(UUID id);

    public Optional<Position> findbyProject(Project project);

    public void deleteByUuid(UUID id);
}
