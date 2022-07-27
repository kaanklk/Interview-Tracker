package tcs.interviewtracker.repository;

import org.springframework.data.repository.CrudRepository;

import tcs.interviewtracker.persistence.Education;

public interface EducationRepository extends CrudRepository<Education, Long> {
    
}
