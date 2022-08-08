package tcs.interviewtracker.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import tcs.interviewtracker.persistence.InterviewType;

public class InterviewTypeRepository extends JpaRepository<InterviewType, Long> {
    Optional<InterviewType> getByUuid(UUID uuid);
}
