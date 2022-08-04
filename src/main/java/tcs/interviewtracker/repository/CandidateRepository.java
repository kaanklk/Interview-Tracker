package tcs.interviewtracker.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tcs.interviewtracker.persistence.Candidate;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {
    Candidate getByUuid(UUID uuid);
}
