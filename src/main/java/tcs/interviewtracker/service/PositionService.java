package tcs.interviewtracker.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import tcs.interviewtracker.DTOs.PositionDTO;
import tcs.interviewtracker.persistence.Candidate;
import tcs.interviewtracker.persistence.Position;
import tcs.interviewtracker.repository.PositionRepository;

@Service
public class PositionService {

    PositionRepository positionRepository;


    public PositionService(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }


    public List<Position> findAll() {
        return positionRepository.findAll();
    }


    public Position findById(UUID uuid) {
        return positionRepository.getReferenceByUuid(uuid);
    }


    public Position save(Position position) {
        return positionRepository.save(position);
    }

    public void delete(Long id){
        positionRepository.deleteById(id);
    }

    public void update(Position position){

        positionRepository.save(position);
    }

    /*public int[] getTotalAndHiredCanddateCount(Long positionId, ArrayList<Candidate> candidates){
        var total = 0;
        var hired = 0;

        for (Candidate candidate : candidates) {
            if(candidate.getPosition().getId() == positionId){
                total++;
               // if(candidate.getStatus())
            }

        }

        return {total; hired};
    }*/




}
