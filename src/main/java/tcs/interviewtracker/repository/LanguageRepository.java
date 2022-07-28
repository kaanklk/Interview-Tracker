package tcs.interviewtracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tcs.interviewtracker.persistence.Candidate;
import tcs.interviewtracker.persistence.Language;

public interface LanguageRepository extends JpaRepository<Language, Long> {
    public List<Language> getByCandidate(Candidate candidate);
}
