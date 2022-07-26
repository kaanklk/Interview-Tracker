package tcs.interviewtracker.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tcs.interviewtracker.persistence.TechnicalDocumentation;

@Repository
public interface TechnicalDocumentationRepository extends CrudRepository<TechnicalDocumentation, Integer> {
    
}
