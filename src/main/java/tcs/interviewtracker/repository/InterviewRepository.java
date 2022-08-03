package tcs.interviewtracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tcs.interviewtracker.persistence.Interview;

public interface InterviewRepository extends JpaRepository<Interview, Long> {

}
