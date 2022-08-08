package tcs.interviewtracker.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tcs.interviewtracker.persistence.Candidate;
import tcs.interviewtracker.persistence.Interview;
import tcs.interviewtracker.persistence.Project;

@Repository
public interface InterviewRepository extends JpaRepository<Interview, Long> {
    Optional<Interview> getByUuid(UUID uuid);
    List<Interview> findByProject(Project project);
    Optional<Interview> getByCandidate(Candidate candidate);
}
