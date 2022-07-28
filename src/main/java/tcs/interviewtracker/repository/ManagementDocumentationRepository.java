package tcs.interviewtracker.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import tcs.interviewtracker.persistence.ManagementDocumentation;

public interface ManagementDocumentationRepository extends JpaRepository<ManagementDocumentation, Long> {

    public ManagementDocumentation getReferenceByUuid(UUID uuid);
}
