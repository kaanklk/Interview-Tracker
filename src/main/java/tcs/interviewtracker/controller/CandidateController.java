package tcs.interviewtracker.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import tcs.interviewtracker.DTOs.CandidateDTO;
import tcs.interviewtracker.persistence.Candidate;
import tcs.interviewtracker.persistence.Person;
import tcs.interviewtracker.service.CandidateService;
import tcs.interviewtracker.service.PersonService;

@RestController
@RequestMapping("/candidates")
@RequiredArgsConstructor
public class CandidateController {
    private ModelMapper candidateModelMapper;

    @NonNull
    private CandidateService candidateService;
    @NonNull
    private PersonService personService;

    @PostConstruct
    void init() {
        candidateModelMapper = new ModelMapper();
        var typeMapToDTO = candidateModelMapper.createTypeMap(Candidate.class, CandidateDTO.class);
        typeMapToDTO.addMapping(source-> {return source.getPosition().getUuid(); }, (dto, value) -> {dto.setPositionId((UUID)value);});
        // Name:
        typeMapToDTO.addMapping(source-> {return source.getPerson().getFirstName(); }, (dto, value) -> {dto.setFirstName((String)value);});
        typeMapToDTO.addMapping(source-> {return source.getPerson().getMiddleName(); }, (dto, value) -> {dto.setMiddleName((String)value);});
        typeMapToDTO.addMapping(source-> {return source.getPerson().getLastName(); }, (dto, value) -> {dto.setLastName((String)value);});
        typeMapToDTO.addMapping(source-> {return source.getPerson().getEmail(); }, (dto, value) -> {dto.setEmail((String)value);});
        typeMapToDTO.addMapping(source-> {return source.getPerson().getPhone(); }, (dto, value) -> {dto.setPhone((String)value);});
        typeMapToDTO.addMapping(source-> {return ; }, (dto, value) -> {dto.setPhone((String)value);});

        var typeMapToEntity = candidateModelMapper.createTypeMap(CandidateDTO.class, Candidate.class);
        //TODO
    }

    @GetMapping
    public ResponseEntity<List<CandidateDTO>> getCandidates() {
        var candidates = candidateService.findAll();
        var dtos = new ArrayList<CandidateDTO>();
        for (var candidate : candidates) {
            var person = personService.getById(candidate.getPerson().getId());
            dtos.add(candidateEntityToDto(candidate));
        }
        return new ResponseEntity<List<CandidateDTO>>(dtos, HttpStatus.OK);
    }

    private CandidateDTO candidateEntityToDto(Candidate candidate) {
        return candidateModelMapper.map(candidate, CandidateDTO.class);
    }

    private Candidate candidateDtoToEntity(CandidateDTO candidateDto) {
        return candidateModelMapper.map(candidateDto, Candidate.class);
    }
}
