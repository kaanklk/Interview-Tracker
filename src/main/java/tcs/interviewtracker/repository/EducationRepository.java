package tcs.interviewtracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tcs.interviewtracker.persistence.Education;

public interface EducationRepository extends JpaRepository<Education, Long> {

}
