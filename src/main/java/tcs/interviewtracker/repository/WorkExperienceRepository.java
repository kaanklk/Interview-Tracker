package tcs.interviewtracker.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import tcs.interviewtracker.persistence.Candidate;
import tcs.interviewtracker.persistence.WorkExperience;

public interface WorkExperienceRepository extends JpaRepository<WorkExperience, Long> {
    public List<WorkExperience> getByCandidate(Candidate candidate);
    public Page<WorkExperience> getByCandidate(Candidate candidate, PageRequest request);
}
