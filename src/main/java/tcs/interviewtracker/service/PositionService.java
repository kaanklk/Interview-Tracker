package tcs.interviewtracker.service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import tcs.interviewtracker.persistence.Candidate;
import tcs.interviewtracker.persistence.Position;
import tcs.interviewtracker.repository.PositionRepository;

@Service
public class PositionService {

    @Autowired
    PositionRepository positionRepository;

    @Autowired
    CandidateService candidateService;

    public PositionService(PositionRepository positionRepository, CandidateService candidateService) {
        this.positionRepository = positionRepository;
        this.candidateService = candidateService;
    }

    public Page<Position> findAll(Pageable paginationData) {
        return positionRepository.findAll(paginationData);
    }

    public Optional<Position> findByUuid(UUID id) {
        return positionRepository.findByUuid(id);
    }

    public Position save(Position position) {

        position.setUuid(UUID.randomUUID());
        return positionRepository.save(position);
    }

    public void delete(UUID id) {
        positionRepository.deleteByUuid(id);
    }

    public Position update(Position position) {

        var dbPosition = positionRepository.findByUuid(position.getUuid());
        position.setId(dbPosition.get().getId());
        return positionRepository.save(position);
    }

    public int getTotalCandidateCount(UUID positionUuid) {
        var total = 0;

        for (Candidate candidate : candidateService.findAll()) {
            if (candidate.getPosition().getUuid().equals(positionUuid))
                total++;

        }
        return total;
    }

    public int getHiredCandidateCount(Long positionId, ArrayList<Candidate> candidates) {
        var hired = 0;

        for (Candidate candidate : candidates) {
            /*
             * if (candidate.getPosition().getId() == positionId
             * && candidate.getStatus() == CandidateStatus.OFFER_ACCEPTED)
             * hired++;
             * 
             */
        }

        return hired;
    }

}