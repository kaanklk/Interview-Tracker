package tcs.interviewtracker.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tcs.interviewtracker.persistence.Timeslot;

@Repository
public interface TimeslotRepository extends JpaRepository<Timeslot, Long> {
    Optional<Timeslot> getByUuid(UUID uuid);
}
