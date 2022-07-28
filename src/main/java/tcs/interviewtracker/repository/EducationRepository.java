package tcs.interviewtracker.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import tcs.interviewtracker.persistence.Candidate;
import tcs.interviewtracker.persistence.Education;

public interface EducationRepository extends JpaRepository<Education, Long> {
    public List<Education> getByCandidate(Candidate candidate);
    public Page<Education> getByCandidate(Candidate candidate, PageRequest request);
}
