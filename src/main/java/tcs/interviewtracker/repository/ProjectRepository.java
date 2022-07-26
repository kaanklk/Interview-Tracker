package tcs.interviewtracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tcs.interviewtracker.persistence.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    Project findByName(String name);
}
