package tcs.interviewtracker.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Sort;

import lombok.AllArgsConstructor;
import tcs.interviewtracker.DTOs.InterviewDTO;
import tcs.interviewtracker.persistence.Interview;
import tcs.interviewtracker.service.InterviewService;
import tcs.interviewtracker.service.ManagementDocumentationService;
import tcs.interviewtracker.service.TechnicalDocumentationService;

@RestController
@RequestMapping("/interviews")
@AllArgsConstructor
public class InterviewController {
    private InterviewService interviewService; 
    private TechnicalDocumentationService technicalDocumentationService;
    private ManagementDocumentationService managementDocumentationService;

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



    private Interview convertToEntity(InterviewDTO src) {
        var dest = new Interview();
        //TODO
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
            dest.setProjectId(timeslot.getUuid());
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
