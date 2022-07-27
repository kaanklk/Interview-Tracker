package tcs.interviewtracker.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import tcs.interviewtracker.exceptions.ResourceAlreadyExistsException;
import tcs.interviewtracker.exceptions.ResourceNotFoundException;
import tcs.interviewtracker.persistence.Candidate;
import tcs.interviewtracker.persistence.StatusChange;
import tcs.interviewtracker.repository.CandidateRepository;
import tcs.interviewtracker.repository.StatusChangeRepository;

@Service
@AllArgsConstructor
public class CandidateService {
    private CandidateRepository candidateRepository;
    private StatusChangeRepository statusChangeRepository;

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

    public Candidate delete(Long id) throws ResourceNotFoundException {
        Candidate toDelete = candidateRepository.getReferenceById(id);
        if (null == toDelete) {
            throw new ResourceNotFoundException();
        }
        candidateRepository.delete(toDelete);
        return toDelete;
    }

    public List<StatusChange> getCandidateHistory(Long candidateId) throws ResourceNotFoundException {
        Candidate candidate = candidateRepository.getReferenceById(candidateId);
        if (null == candidate) {
            throw new ResourceNotFoundException();
        }
        return statusChangeRepository.getByCandidate(candidate);
    }

    public StatusChange getCandidateHistoryById(Long candidateId, Long statusChangeId) 
                            throws ResourceNotFoundException {
        Candidate candidate = candidateRepository.getReferenceById(candidateId);
        if (null == candidate) {
            throw new ResourceNotFoundException();
        }
        List<StatusChange> history = statusChangeRepository.getByCandidate(candidate);
        for (StatusChange statusChange : history) {
            if (statusChange.getCandidate().equals(candidate)) {
                return statusChange;
            }
        }
        throw new ResourceNotFoundException();
    }

}
