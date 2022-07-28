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
import tcs.interviewtracker.persistence.Person;
import tcs.interviewtracker.service.CandidateService;
import tcs.interviewtracker.service.PersonService;

@RestController
@RequestMapping("/candidates")
@AllArgsConstructor
public class CandidateController {
    private ModelMapper modelMapper;
    private CandidateService candidateService;
    private PersonService personService;

    @GetMapping
    public ResponseEntity<List<CandidateDTO>> getCandidates() {
        var candidates = candidateService.findAll();
        var dtos = new ArrayList<CandidateDTO>();
        for (var candidate : candidates) {
            var person = personService.getById(candidate.getPerson().getId());
            dtos.add(candidateEntityToDto(candidate, person));
        }
        return new ResponseEntity<List<CandidateDTO>>(dtos, HttpStatus.OK);
    }


    private CandidateDTO candidateEntityToDto(Candidate candidate, Person person) {
        return modelMapper.map(candidate, CandidateDTO.class);
    }

    private Candidate candidateDtoToEntity(CandidateDTO candidateDto) {
        return modelMapper.map(candidateDto, Candidate.class);
    }
}
