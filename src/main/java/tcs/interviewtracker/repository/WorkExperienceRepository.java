package tcs.interviewtracker.repository;

import org.springframework.data.repository.CrudRepository;

import tcs.interviewtracker.persistence.WorkExperience;

public interface WorkExperienceRepository extends CrudRepository<WorkExperience, Long> {
    
}
