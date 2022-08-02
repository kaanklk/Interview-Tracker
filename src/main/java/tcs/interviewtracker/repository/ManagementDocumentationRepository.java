package tcs.interviewtracker.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import tcs.interviewtracker.persistence.Candidate;
import tcs.interviewtracker.persistence.ManagementDocumentation;

public interface ManagementDocumentationRepository extends JpaRepository<ManagementDocumentation, Long> {

    public Optional<ManagementDocumentation> getReferenceByUuid(UUID uuid);
    public ManagementDocumentation getReferenceByCandidate(Candidate candidate);
    public ManagementDocumentation save(Optional<ManagementDocumentation> updateDoc);
    public void delete(Optional<ManagementDocumentation> manageDoc);
}
