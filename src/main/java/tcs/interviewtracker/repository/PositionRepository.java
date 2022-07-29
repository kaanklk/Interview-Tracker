package tcs.interviewtracker.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import tcs.interviewtracker.persistence.Position;

public interface PositionRepository extends JpaRepository<Position, Long> {
    public Optional<Position> findByUuid(UUID id);

    public void deleteByUuid(UUID id);
}
