package tcs.interviewtracker.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import tcs.interviewtracker.persistence.WorkExperience;

public interface WorkExperienceRepository extends JpaRepository<WorkExperience, Long> {

}
