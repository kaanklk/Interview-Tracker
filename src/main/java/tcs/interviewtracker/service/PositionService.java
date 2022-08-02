package tcs.interviewtracker.service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import tcs.interviewtracker.persistence.Candidate;
import tcs.interviewtracker.persistence.CandidateStatus;
import tcs.interviewtracker.persistence.Position;
import tcs.interviewtracker.repository.PositionRepository;

@Service
public class PositionService {

    PositionRepository positionRepository;

    public PositionService(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    public Page<Position> findAll(Pageable paginationData) {
        return positionRepository.findAll(paginationData);
    }

    public Optional<Position> findById(UUID id) {
        return positionRepository.findByUuid(id);
    }

    public Position save(Position position) {
        return positionRepository.save(position);
    }

    public void delete(UUID id) {
        positionRepository.deleteByUuid(id);
    }

    public void update(Position position) {

        positionRepository.save(position);
    }

    public int getTotalCanddateCount(Long positionId, ArrayList<Candidate> candidates) {
        var total = 0;

        for (Candidate candidate : candidates) {
            if (candidate.getPosition().getId() == positionId)
                total++;

        }
        return total;
    }

    public int getHiredCandidateCount(Long positionId, ArrayList<Candidate> candidates) {
        var hired = 0;

        for (Candidate candidate : candidates) {
            if (candidate.getPosition().getId() == positionId
                    && candidate.getStatus() == CandidateStatus.OFFER_ACCEPTED)
                hired++;
        }

        return hired;
    }
}
