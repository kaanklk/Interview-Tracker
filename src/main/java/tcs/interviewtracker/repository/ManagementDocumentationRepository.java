package tcs.interviewtracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tcs.interviewtracker.persistence.ManagementDocumentation;

public interface ManagementDocumentationRepository extends JpaRepository<ManagementDocumentation, Long> {

}
