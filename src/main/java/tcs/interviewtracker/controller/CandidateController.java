package tcs.interviewtracker.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import tcs.interviewtracker.DTOs.CandidateDTO;
import tcs.interviewtracker.persistence.Candidate;
import tcs.interviewtracker.repository.CandidateRepository;
import tcs.interviewtracker.service.CandidateService;

@RestController
@RequestMapping("/candidates")
@AllArgsConstructor
public class CandidateController {
    private CandidateService candidateService;
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<CandidateDTO>> getCandidates() {
        var entityList = candidateService.findAll();
        return new ResponseEntity<List<CandidateDTO>>(
                        candidateEntityListToDtoList(entityList),
                        HttpStatus.OK
                    );
    }




    private CandidateDTO candidateEntityToDto(Candidate candidate) {
        return modelMapper.map(candidate, CandidateDTO.class);
    }

    private Candidate candidateDtoToEntity(CandidateDTO candidateDto) {
        return modelMapper.map(candidateDto, Candidate.class);
    }

    private List<CandidateDTO> candidateEntityListToDtoList(List<Candidate> candidates) {
        var dtoList = new ArrayList<CandidateDTO>();
        candidates.forEach(e -> { dtoList.add(candidateEntityToDto(e)); });
        return dtoList;
    }

    private List<Candidate> candidateDtoListToEntityList(List<CandidateDTO> candidateDtos) {
        var entityList = new ArrayList<Candidate>();
        candidateDtos.forEach(dto -> { entityList.add(candidateDtoToEntity(dto)); });
        return entityList;
    }
}
