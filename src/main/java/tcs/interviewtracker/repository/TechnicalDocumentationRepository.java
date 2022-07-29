package tcs.interviewtracker.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tcs.interviewtracker.persistence.TechnicalDocumentation;

@Repository
public interface TechnicalDocumentationRepository extends JpaRepository<TechnicalDocumentation, Long> {
 
    
Optional<TechnicalDocumentation> findByUuid(@Param("uuid") UUID uuid);

}
