package tcs.interviewtracker.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import tcs.interviewtracker.exceptions.ResourceNotFoundException;
import tcs.interviewtracker.persistence.Candidate;
import tcs.interviewtracker.repository.CandidateRepository;

@Service
@AllArgsConstructor
public class CandidateService {
    private CandidateRepository candidateRepository;

    public List<Candidate> getAll() {
        return candidateRepository.findAll();
    }

    public Page<Candidate> getPaginated(PageRequest request) {
        return candidateRepository.findAll(request);
    }

    public Candidate getById(Long id) {
        return candidateRepository.getReferenceById(id);
    }

    public Candidate save(Candidate candidate) {
        return candidateRepository.save(candidate);        
    }

    public Candidate update(Long id, Candidate candidate) throws ResourceNotFoundException {
        Optional<Candidate> oldCandidate = candidateRepository.findById(id);
        if (oldCandidate.isEmpty()) {
            throw new ResourceNotFoundException();
        }
        candidate.setId(id);
        return candidateRepository.save(candidate);
    }
    

}
