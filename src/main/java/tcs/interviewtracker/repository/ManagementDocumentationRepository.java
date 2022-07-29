package tcs.interviewtracker.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;


import tcs.interviewtracker.persistence.Candidate;
import tcs.interviewtracker.persistence.ManagementDocumentation;

public interface ManagementDocumentationRepository extends JpaRepository<ManagementDocumentation, Long> {
    List<ManagementDocumentation> findByUuid(@Param("UUID") UUID uuid);

    public ManagementDocumentation getReferenceByUuid(UUID uuid);
    public ManagementDocumentation getReferenceByCandidate(Candidate candidate);
}
