package tcs.interviewtracker.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tcs.interviewtracker.persistence.Candidate;
import tcs.interviewtracker.persistence.TechnicalDocumentation;

@Repository
public interface TechnicalDocumentationRepository extends JpaRepository<TechnicalDocumentation, Long> {
    public TechnicalDocumentation getReferenceByUuid(UUID uuid);
    public TechnicalDocumentation getReferenceByCandidate(Candidate candidate);
}
