package tcs.interviewtracker.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tcs.interviewtracker.persistence.Position;
import tcs.interviewtracker.persistence.Project;

@Repository
public interface PositionRepository extends JpaRepository<Position, Long> {
    public Optional<Position> findByUuid(UUID id);

    public void deleteByUuid(UUID id);
}
