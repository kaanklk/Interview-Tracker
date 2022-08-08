package tcs.interviewtracker.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tcs.interviewtracker.persistence.Candidate;
import tcs.interviewtracker.persistence.Language;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Long> {
    public List<Language> getByCandidate(Candidate candidate);
    public Page<Language> getByCandidate(Candidate candidate, PageRequest request);
}
