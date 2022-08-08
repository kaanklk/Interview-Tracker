package tcs.interviewtracker.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import tcs.interviewtracker.persistence.InterviewType;

public interface InterviewTypeRepository extends JpaRepository<InterviewType, Long> {
}
