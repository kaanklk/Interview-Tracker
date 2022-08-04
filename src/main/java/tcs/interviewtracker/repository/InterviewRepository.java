package tcs.interviewtracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tcs.interviewtracker.persistence.Interview;

@Repository
public interface InterviewRepository extends JpaRepository<Interview, Long> {
    List<Interview> findByProjectId(Long projectId);
}
