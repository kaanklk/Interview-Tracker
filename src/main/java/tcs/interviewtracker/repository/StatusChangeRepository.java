package tcs.interviewtracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tcs.interviewtracker.persistence.Candidate;
import tcs.interviewtracker.persistence.StatusChange;

public interface StatusChangeRepository extends JpaRepository<StatusChange, Long> {
    public List<StatusChange> getByCandidate(Candidate canidate);
}
