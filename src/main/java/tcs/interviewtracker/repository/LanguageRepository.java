package tcs.interviewtracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tcs.interviewtracker.persistence.Language;

public interface LanguageRepository extends JpaRepository<Language, Long> {

}
