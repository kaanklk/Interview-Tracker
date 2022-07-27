package tcs.interviewtracker.repository;

import org.springframework.data.repository.CrudRepository;

import tcs.interviewtracker.persistence.Language;

public interface LanguageRepository extends CrudRepository<Language, Long> {
    
}
