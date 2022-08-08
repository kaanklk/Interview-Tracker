package tcs.interviewtracker.repository;


import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tcs.interviewtracker.persistence.Candidate;
import tcs.interviewtracker.persistence.WorkExperience;

@Repository
public interface WorkExperienceRepository extends JpaRepository<WorkExperience, Long> {
    public List<WorkExperience> getByCandidate(Candidate candidate);
    public Page<WorkExperience> getByCandidate(Candidate candidate, PageRequest request);
}
