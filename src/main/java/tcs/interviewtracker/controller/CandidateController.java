package tcs.interviewtracker.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.websocket.server.PathParam;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import tcs.interviewtracker.DTOs.CandidateDTO;
import tcs.interviewtracker.DTOs.StatusChangeDTO;
import tcs.interviewtracker.exceptions.ResourceNotFoundException;
import tcs.interviewtracker.mappers.CandidateMapper;
import tcs.interviewtracker.mappers.StatusChangeMapper;
import tcs.interviewtracker.persistence.Candidate;
import tcs.interviewtracker.persistence.StatusChange;
import tcs.interviewtracker.service.CandidateService;
import tcs.interviewtracker.service.PersonService;

@RestController
@RequestMapping("/candidates")
@RequiredArgsConstructor
public class CandidateController {
    @NonNull
    private CandidateService candidateService;
    @NonNull
    private PersonService personService;

    @GetMapping
    public ResponseEntity<List<CandidateDTO>> getCandidates(
                @RequestParam(required = false, defaultValue = "2") Integer pagesize,
                @RequestParam(required = false, defaultValue = "0") Integer offset,
                @RequestParam(required = false, defaultValue = "id") String orderBy,
                @RequestParam(required = false, defaultValue = "ascending") String orderDirection) 
    {
        PageRequest request = PageRequest.of(offset, pagesize, 
                        (orderDirection.equals("ascending"))? Sort.by(orderBy).ascending() : Sort.by(orderBy).descending());
        var candidates = candidateService.findPaginated(request);
        var dtos = new ArrayList<CandidateDTO>();
        for (var candidate : candidates) {
            dtos.add(CandidateMapper.INSTANCE.toDTO(candidate));
        }
        return new ResponseEntity<List<CandidateDTO>>(dtos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CandidateDTO> postCandidate(
        @RequestBody(required = true) CandidateDTO dto
    ) {
        var candidate = CandidateMapper.INSTANCE.toEntity(dto);
        var responseDTO = CandidateMapper.INSTANCE.toDTO(candidateService.save(candidate));
        return new ResponseEntity<CandidateDTO>(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{candidateId}")
    public ResponseEntity<CandidateDTO> getCandidates(
                @PathVariable(name = "candidateId", required = true) UUID candidateUuid
    ) {

        var candidate = candidateService.getByUuid(candidateUuid);
        var responseDto = CandidateMapper.INSTANCE.toDTO(candidate);
        return new ResponseEntity<CandidateDTO>(responseDto, HttpStatus.OK);
    }

    @PutMapping("/{candidateId}")
    public ResponseEntity<CandidateDTO> putCandidate(
                @PathVariable(name = "candidateId", required = true) UUID candidateUuid,
                @RequestBody CandidateDTO candidateDTO
    ) throws ResourceNotFoundException {
        var candidate = CandidateMapper.INSTANCE.toEntity(candidateDTO);
        var updatedCandidate = candidateService.update(candidateUuid, candidate);
        var responseDto = CandidateMapper.INSTANCE.toDTO(updatedCandidate);
        return new ResponseEntity<CandidateDTO>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{candidateId}")
    public ResponseEntity<CandidateDTO> deleteCandidate(
                @PathVariable(name = "candidateId", required = true) UUID candidateUuid
    ) throws ResourceNotFoundException {
        var deletedCandidate = candidateService.delete(candidateUuid);
        var responseDto = CandidateMapper.INSTANCE.toDTO(deletedCandidate);
        return new ResponseEntity<CandidateDTO>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/{candidateId}/history")
    public ResponseEntity<List<StatusChangeDTO>> getHistory(
                @PathVariable(name = "candidateId", required = true) UUID candidateUuid
    ) throws ResourceNotFoundException {
        var history = candidateService.getCandidateHistory(candidateUuid);
        var dtos = new ArrayList<StatusChangeDTO>();
        for (var statusChange : history) {
            dtos.add(StatusChangeMapper.INSTANCE.toDTO(statusChange));
        }
        return new ResponseEntity<List<StatusChangeDTO>>(dtos, HttpStatus.OK);
    }

    @GetMapping("/{candidateId}/history/{statusChangeId}")
    public ResponseEntity<StatusChangeDTO> getHistory(
                @PathVariable(name = "candidateId", required = true) UUID candidateUuid,
                @PathVariable(name = "statusChangeId", required = true) UUID statusChangeUuid
    ) throws ResourceNotFoundException {
        var statusChange = candidateService.getCandidateHistoryByUuid(candidateUuid, statusChangeUuid);
        var responseDto = StatusChangeMapper.INSTANCE.toDTO(statusChange);
        return new ResponseEntity<StatusChangeDTO>(responseDto, HttpStatus.OK);
    }

}
