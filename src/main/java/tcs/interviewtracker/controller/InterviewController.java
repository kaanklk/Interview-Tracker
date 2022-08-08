package tcs.interviewtracker.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Sort;

import lombok.AllArgsConstructor;
import tcs.interviewtracker.DTOs.InterviewDTO;
import tcs.interviewtracker.exceptions.ResourceNotFoundException;
import tcs.interviewtracker.persistence.Interview;
import tcs.interviewtracker.service.CandidateService;
import tcs.interviewtracker.service.InterviewService;
import tcs.interviewtracker.service.ManagementDocumentationService;
import tcs.interviewtracker.service.ProjectService;
import tcs.interviewtracker.service.TechnicalDocumentationService;
import tcs.interviewtracker.service.TimeslotService;
import tcs.interviewtracker.service.UserService;

@RestController
@RequestMapping("/interviews")
@AllArgsConstructor
public class InterviewController {
    private InterviewService interviewService; 
    private TechnicalDocumentationService technicalDocumentationService;
    private ManagementDocumentationService managementDocumentationService;
    private CandidateService candidateService;
    private ProjectService projectService;
    private TimeslotService timeslotService;
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<InterviewDTO>> getInterviews
    (
        @RequestParam(required = false, defaultValue = "10") Integer pagesize,
        @RequestParam(required = false, defaultValue = "0") Integer offset,
        @RequestParam(required = false, defaultValue = "id") String orderBy,
        @RequestParam(required = false, defaultValue = "ascending") String orderDirection
    ) {
        PageRequest request = PageRequest.of(offset, pagesize,
                        (orderDirection.toLowerCase().equals("ascending"))? Sort.by(orderBy).ascending() : Sort.by(orderBy).descending());
        var entities = interviewService.findInterviewsPaginated(request);
        List<InterviewDTO> dtos = new ArrayList<InterviewDTO>();
        for (var entity : entities) {
            dtos.add(convertToDTO(entity));
        }
        return new ResponseEntity<List<InterviewDTO>>(dtos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<InterviewDTO> postInterview(
        @RequestBody(required = true) InterviewDTO dto
    ) throws ResourceNotFoundException 
    {
        var interview = convertToEntity(dto);
        interview = interviewService.saveInterview(interview);
        var responseDTO = convertToDTO(interview);
        return new ResponseEntity<InterviewDTO>(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{interviewId}")
    public ResponseEntity<InterviewDTO> getInterview(
                @PathVariable(name = "interviewId", required = true) UUID interviewUuid
    ) throws ResourceNotFoundException {

        var interview = interviewService.getInterviewByUuid(interviewUuid);
        var responseDto = convertToDTO(interview);
        return new ResponseEntity<InterviewDTO>(responseDto, HttpStatus.OK);
    }

    @PutMapping("/{interviewId}")
    public ResponseEntity<InterviewDTO> putInterview(
                @PathVariable(name = "interviewId", required = true) UUID interviewUuid,
                @RequestBody InterviewDTO interviewDTO
    ) throws ResourceNotFoundException {
        var interview = convertToEntity(interviewDTO);
        interview = interviewService.updateInterview(interviewUuid, interview);
        var responseDto = convertToDTO(interview);
        return new ResponseEntity<InterviewDTO>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{interviewId}")
    public ResponseEntity<InterviewDTO> deleteInterview(
                @PathVariable(name = "interviewId", required = true) UUID interviewUuid
    ) throws ResourceNotFoundException {
        var interview = interviewService.deleteInterview(interviewUuid);
        var responseDto = convertToDTO(interview);
        return new ResponseEntity<InterviewDTO>(responseDto, HttpStatus.OK);
    }

    //TODO
    //Other endpoints


    private Interview convertToEntity(InterviewDTO src) {
        var dest = new Interview();
        dest.setUuid(src.getUuid());
        var candidate = candidateService.getByUuid(src.getCandidateId());
        if (null != candidate) {
            dest.setCandidate(candidate);
        }
        var project = projectService.getByUuid(src.getProjectId());
        if (null != project) {
            dest.setProject(project);
        }
        var timeslot = timeslotService.findTimeslotByUuid(src.getTimeslotId());
        if (null != timeslot) {
            dest.setTimeslot(timeslot);
        }
        var type = interviewService.findInterviewTypeByName(src.getType());
        if (null != type) {
            dest.setType(type);
        }
        var interviewer1 = userService.getUserById(src.getInterviewerOneId());
        if (null != interviewer1) {
            dest.setInterviewerOne(interviewer1);
        }
        var interviewer2 = userService.getUserById(src.getInterviewerTwoId());
        if (null != interviewer2) {
            dest.setInterviewerTwo(interviewer2);
        }
        
        dest.setIsCompleted(src.getIsCompleted());

        return dest;
    }

    private InterviewDTO convertToDTO(Interview src) {
        var dest = new InterviewDTO();
        dest.setUuid(src.getUuid());
        var candidate = src.getCandidate();
        if (null != candidate) {
            dest.setCandidateId(candidate.getUuid());
        }
        var project = src.getProject();
        if (null != project) {
            dest.setProjectId(project.getUuid());
        }
        var timeslot = src.getTimeslot();
        if (null != timeslot) {
            dest.setTimeslotId(timeslot.getUuid());
        }
        var type = src.getType();
        if (null != type) {
            dest.setType(type.getTypeName());
        }
        var interviewer1 = src.getInterviewerOne();
        if (null != interviewer1) {
            dest.setInterviewerOneId(interviewer1.getUuid());
        }
        var interviewer2 = src.getInterviewerTwo();
        if (null != interviewer2) {
            dest.setInterviewerTwoId(interviewer2.getUuid());
        }

        // DocumentationId:
        if (dest.getType().toLowerCase().equals("technical")) {
            var techDoc = technicalDocumentationService.getTechnicalDocByCandidate(candidate);
            if (techDoc.isPresent()) {
                dest.setDocumentationId(techDoc.get().getUuid());
            }
        }
        else if (dest.getType().toLowerCase().equals("management")) {
            var manDoc = managementDocumentationService.getManageDocByCandidate(candidate);
            if (manDoc.isPresent()) {
                dest.setDocumentationId(manDoc.get().getUuid());
            }
        }
        
        dest.setIsCompleted(src.getIsCompleted());

        return dest;
    }
}
