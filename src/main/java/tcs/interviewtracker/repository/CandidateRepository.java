package tcs.interviewtracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tcs.interviewtracker.persistence.Candidate;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {
    
}
