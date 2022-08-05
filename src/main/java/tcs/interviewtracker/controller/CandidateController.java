package tcs.interviewtracker.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import tcs.interviewtracker.DTOs.CandidateDTO;
import tcs.interviewtracker.DTOs.EducationDTO;
import tcs.interviewtracker.DTOs.LanguageDTO;
import tcs.interviewtracker.DTOs.StatusChangeDTO;
import tcs.interviewtracker.DTOs.TimeslotDTO;
import tcs.interviewtracker.DTOs.WorkExperienceDTO;
import tcs.interviewtracker.exceptions.ResourceNotFoundException;
import tcs.interviewtracker.mappers.StatusChangeMapper;
import tcs.interviewtracker.persistence.Candidate;
import tcs.interviewtracker.persistence.Education;
import tcs.interviewtracker.persistence.Language;
import tcs.interviewtracker.persistence.Person;
import tcs.interviewtracker.persistence.Timeslot;
import tcs.interviewtracker.persistence.WorkExperience;
import tcs.interviewtracker.service.CandidateService;
import tcs.interviewtracker.service.ManagementDocumentationService;
import tcs.interviewtracker.service.PersonService;
import tcs.interviewtracker.service.PositionService;
import tcs.interviewtracker.service.ProjectService;
import tcs.interviewtracker.service.TechnicalDocumentationService;

@RestController
@RequestMapping("/candidates")
@RequiredArgsConstructor
public class CandidateController {
    @NonNull
    private CandidateService candidateService;
    @NonNull
    private PersonService personService;
    @NonNull
    private PositionService positionService;
    @NonNull
    private ProjectService projectService;
    @NonNull
    private ManagementDocumentationService managementDocumentationService;
    @NonNull
    private TechnicalDocumentationService technicalDocumentationService;

    @GetMapping
    public ResponseEntity<List<CandidateDTO>> getCandidates(
                @RequestParam(required = false, defaultValue = "10") Integer pagesize,
                @RequestParam(required = false, defaultValue = "0") Integer offset,
                @RequestParam(required = false, defaultValue = "id") String orderBy,
                @RequestParam(required = false, defaultValue = "ascending") String orderDirection)
    {
        PageRequest request = PageRequest.of(offset, pagesize,
                        (orderDirection.equals("ascending"))? Sort.by(orderBy).ascending() : Sort.by(orderBy).descending());
        var candidates = candidateService.findPaginated(request);
        var dtos = new ArrayList<CandidateDTO>();
        for (var candidate : candidates) {
            dtos.add(convertToDTO(candidate));
        }
        return new ResponseEntity<List<CandidateDTO>>(dtos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CandidateDTO> postCandidate(
        @RequestBody(required = true) CandidateDTO dto
    ) {

        var candidate = convertToEntity(dto);
        candidate = candidateService.save(candidate);
        saveRelatedRecords(dto, candidate);
        var responseDTO = convertToDTO(candidate);
        return new ResponseEntity<CandidateDTO>(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{candidateId}")
    public ResponseEntity<CandidateDTO> getCandidates(
                @PathVariable(name = "candidateId", required = true) UUID candidateUuid
    ) throws ResourceNotFoundException {

        var candidate = candidateService.getByUuid(candidateUuid);
        var responseDto = convertToDTO(candidate);
        return new ResponseEntity<CandidateDTO>(responseDto, HttpStatus.OK);
    }

    @PutMapping("/{candidateId}")
    public ResponseEntity<CandidateDTO> putCandidate(
                @PathVariable(name = "candidateId", required = true) UUID candidateUuid,
                @RequestBody CandidateDTO candidateDTO
    ) throws ResourceNotFoundException {
        var candidate = convertToEntity(candidateDTO);
        candidate = candidateService.update(candidateUuid, candidate);
        saveRelatedRecords(candidateDTO, candidate);
        var responseDto = convertToDTO(candidate);
        return new ResponseEntity<CandidateDTO>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{candidateId}")
    public ResponseEntity<CandidateDTO> deleteCandidate(
                @PathVariable(name = "candidateId", required = true) UUID candidateUuid
    ) throws ResourceNotFoundException {
        var deletedCandidate = candidateService.delete(candidateUuid);
        var responseDto = convertToDTO(deletedCandidate);
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

    private void saveRelatedRecords(CandidateDTO src, Candidate dest) {
        var workExperienceDTOs = src.getWorkExperiences();
        for (var dto : workExperienceDTOs) {
            var experience = new WorkExperience();
            experience.setCandidate(dest);
            experience.setStartDate(java.sql.Date.valueOf(dto.getStart()));
            experience.setEndDate(java.sql.Date.valueOf(dto.getEnd()));
            experience.setInstitution(dto.getInstitution());
            experience.setSummary(dto.getSummary());
            candidateService.saveWorkExperience(experience);
        }
        var educationDTOs = src.getEducations();
        for (var dto : educationDTOs) {
            var education = new Education();
            education.setCandidate(dest);
            education.setStartDate(java.sql.Date.valueOf(dto.getStart()));
            education.setEndDate(java.sql.Date.valueOf(dto.getEnd()));
            education.setInstitution(dto.getInstitution());
            education.setInformation(dto.getInformation());
            candidateService.saveEducation(education);
        }
        var languageDTOs = src.getLanguages();
        for (var dto : languageDTOs) {
            var language = new Language();
            language.setCandidate(dest);
            language.setLanguage(dto.getLanguage());
            language.setLevel(dto.getLevel());
            candidateService.saveLanguage(language);
        }
    }

    private Candidate convertToEntity(CandidateDTO src) {
        Candidate dest = new Candidate();

        dest.setUuid(src.getUuid());
        dest.setStatus(src.getStatus());
        dest.setPosition(positionService.findByUuid(src.getPositionId()).get());
        dest.setProject(projectService.getByUuid(src.getProjectId()));
        var person = new Person();
        person.setFirstName(src.getFirstName());
        person.setLastName(src.getLastName());
        person.setMiddleName(src.getMiddleName());
        person.setEmail(src.getEmail());
        person.setPhone(src.getPhone());
        person.setDateOfBirth(java.sql.Date.valueOf(src.getDateOfBirth()));
        person = personService.save(person);
        dest.setPerson(person);
        
        dest.setCvPath(src.getCvPath());

        return dest;
    }

    private CandidateDTO convertToDTO(Candidate src) {
        CandidateDTO dest = new CandidateDTO();
        dest.setUuid(src.getUuid());
        dest.setStatus(src.getStatus());
        var position = src.getPosition();
        if (null != position) {
            dest.setPositionId(position.getUuid());
        }
        var project = src.getProject();
        if (null != project) {
            dest.setProjectId(project.getUuid());
        }
        Person person = src.getPerson();
        if (null != person) {
            dest.setFirstName(person.getFirstName());
            dest.setLastName(person.getLastName());
            dest.setMiddleName(person.getMiddleName());
            dest.setEmail(person.getEmail());
            dest.setPhone(person.getPhone());
            dest.setDateOfBirth(src.getPerson().getDateOfBirth().toString());
        }

        List<WorkExperience> workExperiences;
        try {
            workExperiences = candidateService.findWorkExperiences(src.getUuid());
        }
        catch (ResourceNotFoundException e) {
            workExperiences = new ArrayList<WorkExperience>();
        }
        var workExperienceDTOs = new ArrayList<WorkExperienceDTO>();
        for (var experience : workExperiences) {
            var experienceDTO = new WorkExperienceDTO();
            experienceDTO.setStart(experience.getStartDate().toString());
            experienceDTO.setEnd(experience.getEndDate().toString());
            experienceDTO.setInstitution(experience.getInstitution());
            experienceDTO.setSummary(experience.getSummary());
            workExperienceDTOs.add(experienceDTO);
        }
        dest.setWorkExperiences(workExperienceDTOs);

        List<Education> educations;
        try {
            educations = candidateService.findEducation(src.getUuid());
        }
        catch (ResourceNotFoundException e) {
            educations = new ArrayList<Education>();
        }
        var educationDTOs = new ArrayList<EducationDTO>();
        for (var education : educations) {
            var educationDTO = new EducationDTO();
            educationDTO.setStart(education.getStartDate().toString());
            educationDTO.setEnd(education.getEndDate().toString());
            educationDTO.setInstitution(education.getInstitution());
            educationDTO.setInformation(education.getInformation());
            educationDTOs.add(educationDTO);
        }
        dest.setEducations(educationDTOs);

        List<Language> languages;
        try {
            languages = candidateService.findLanguages(src.getUuid());
        }
        catch (ResourceNotFoundException e) {
            languages = new ArrayList<Language>();
        }
        var languageDTOs = new ArrayList<LanguageDTO>();
        for (var language : languages) {
            var languageDTO = new LanguageDTO();
            languageDTO.setLanguage(language.getLanguage());
            languageDTO.setLevel(language.getLevel());
            languageDTOs.add(languageDTO);
        }
        dest.setLanguages(languageDTOs);

        var managementDoc = managementDocumentationService.getManageDocByCandidate(src);
        if (managementDoc.isPresent()) {
            dest.setManagementDocumentationId(managementDoc.get().getUuid());
            var interviewer1 = managementDoc.get().getInterviewer1();
            if (null != interviewer1) {
                dest.setManagementInterviewerId(interviewer1.getUuid());
            }
            var interviewer2 = managementDoc.get().getInterviewer2();
            if (null != interviewer2) {
                dest.setManagementInterviewerId2(interviewer2.getUuid());
            }
        }

        var techDoc = technicalDocumentationService.getTechnicalDocByCandidate(src);
        if (techDoc.isPresent()) {
            dest.setTechnicalDocumentationId(techDoc.get().getUuid());
            if (null != techDoc.get().getInterviewerOne()) {
                dest.setTechnicalInterviewerId(techDoc.get().getInterviewerOne().getUuid());
            }
            if (null != techDoc.get().getInterviewerTwo()) {
                dest.setTechnicalInterviewerId2(techDoc.get().getInterviewerTwo().getUuid());
            }
        }

        var timeslots = new ArrayList<TimeslotDTO>();
        //TODO
        dest.setPossibleTimeslots(timeslots);

        dest.setCvPath(src.getCvPath());

        //var dto = candidateMapper.map(entity, CandidateDTO.class);
        //return dto;
        return dest;
    }
}
