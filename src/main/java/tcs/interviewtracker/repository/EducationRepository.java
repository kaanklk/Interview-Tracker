package tcs.interviewtracker.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tcs.interviewtracker.persistence.Candidate;
import tcs.interviewtracker.persistence.Education;

@Repository
public interface EducationRepository extends JpaRepository<Education, Long> {
    public Education getByUuid(UUID uuid);
    public List<Education> getByCandidate(Candidate candidate);
    public Page<Education> getByCandidate(Candidate candidate, PageRequest request);
}
