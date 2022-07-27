package tcs.interviewtracker.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import tcs.interviewtracker.DTOs.PositionDTO;
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


    public Optional<Position> findById(Long id) {
        return positionRepository.findById(id);
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


}
