package tcs.interviewtracker.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
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
import tcs.interviewtracker.service.ManagementDocumentationService;
import tcs.interviewtracker.service.PersonService;
import tcs.interviewtracker.service.PositionService;
import tcs.interviewtracker.service.TechnicalDocumentationService;

@RestController
@RequestMapping("/candidates")
@RequiredArgsConstructor
public class CandidateController {
    @Nullable
    private ModelMapper candidateModelMapper;

    @NonNull
    private CandidateService candidateService;
    @NonNull
    private PersonService personService;

    @PostConstruct
    void init() {
        candidateModelMapper = new ModelMapper();
        var typeMapToDTO = candidateModelMapper.createTypeMap(Candidate.class, CandidateDTO.class);
        // Position UUID:
        typeMapToDTO.addMapping(source -> {return source.getPosition().getUuid(); }, (dto, value) -> { dto.setPositionId((UUID)value); });
        // Name:
        typeMapToDTO.addMapping(source -> {return source.getPerson().getFirstName(); }, (dto, value) -> { dto.setFirstName((String)value);});
        typeMapToDTO.addMapping(source -> {return source.getPerson().getMiddleName(); }, (dto, value) -> { dto.setMiddleName((String)value);});
        typeMapToDTO.addMapping(source -> {return source.getPerson().getLastName(); }, (dto, value) -> { dto.setLastName((String)value);});
        typeMapToDTO.addMapping(source -> {return source.getPerson().getEmail(); }, (dto, value) -> { dto.setEmail((String)value); });
        typeMapToDTO.addMapping(source -> {return source.getPerson().getPhone(); }, (dto, value) -> { dto.setPhone((String)value); });
        // Interviewers:
        //TODO

        // Documentation:
        typeMapToDTO.addMapping(
            source -> {return candidateService.getTechnicalDocumentationOfCandidate(source.getUuid()).getUuid(); },
            (dto, value) -> { dto.setTechnicalDocumentationId((UUID)value); }
        );
        typeMapToDTO.addMapping(
            source -> {return candidateService.getManagementDocumentationByCandidate(source.getUuid()).getUuid(); },
            (dto, value) -> { dto.setManagementDocumentationId((UUID)value); }
        );
        // Timeslot:
        //TODO
        var typeMapToEntity = candidateModelMapper.createTypeMap(CandidateDTO.class, Candidate.class);
        // Position UUID:
        typeMapToEntity.addMapping(dto -> { return dto.getPositionId(); }, (entity, value) -> { entity.setPosition(candidateService.getPosition((UUID)value)); });
        // Name:
        typeMapToEntity.addMapping(dto -> { return dto.getFirstName(); }, (entity, value) -> {entity.getPerson().setFirstName((String)value);});
        typeMapToEntity.addMapping(dto -> {return dto.getMiddleName(); }, (entity, value) -> {entity.getPerson().setMiddleName((String)value);});
        typeMapToEntity.addMapping(dto -> {return dto.getLastName(); }, (entity, value) -> {entity.getPerson().setLastName((String)value);});
        typeMapToEntity.addMapping(source -> {return source.getEmail(); }, (dto, value) -> {dto.getPerson().setEmail((String)value);});
        typeMapToEntity.addMapping(source -> {return source.getPhone(); }, (dto, value) -> {dto.getPerson().setPhone((String)value);});
        //TODO

    }

    @GetMapping
    public ResponseEntity<List<CandidateDTO>> getCandidates() {
        var candidates = candidateService.findAll();
        var dtos = new ArrayList<CandidateDTO>();
        for (var candidate : candidates) {
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
