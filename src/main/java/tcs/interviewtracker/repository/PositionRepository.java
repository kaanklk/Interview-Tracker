package tcs.interviewtracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tcs.interviewtracker.persistence.Position;

public interface PositionRepository extends JpaRepository<Position, Long> {

}
