package tcs.interviewtracker.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import tcs.interviewtracker.persistence.Position;

public interface PositionRepository extends JpaRepository<Position, Long> {
    public Position getReferenceByUuid(UUID uuid);
}
