package tcs.interviewtracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tcs.interviewtracker.persistence.TechnicalDocumentation;

@Repository
public interface TechnicalDocumentationRepository extends JpaRepository<TechnicalDocumentation, Long> {
    
}
