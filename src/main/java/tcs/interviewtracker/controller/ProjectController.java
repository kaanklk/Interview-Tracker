package tcs.interviewtracker.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tcs.interviewtracker.DTOs.CandidateDTO;
import tcs.interviewtracker.DTOs.InterviewDTO;
import tcs.interviewtracker.DTOs.PositionDTO;
import tcs.interviewtracker.DTOs.ProjectDTO;
import tcs.interviewtracker.DTOs.UserDTO;
import tcs.interviewtracker.exceptions.ResourceAlreadyExistsException;
import tcs.interviewtracker.exceptions.ResourceNotFoundException;
import tcs.interviewtracker.persistence.Candidate;
import tcs.interviewtracker.persistence.Interview;
import tcs.interviewtracker.persistence.Position;
import tcs.interviewtracker.persistence.Project;
import tcs.interviewtracker.persistence.User;
import tcs.interviewtracker.service.ProjectService;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    private ProjectService projectService;

    public ProjectController(@Autowired ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("")
    public ResponseEntity<List<ProjectDTO>> getAllProjects(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "5") Integer pageSize,
            @RequestParam(defaultValue = "name") String sortBy) throws ResourceNotFoundException {
        List<Project> list = projectService.getAllProjects(pageNo, pageSize, sortBy);
        List<ProjectDTO> dtoList = convertToProjectDtoList(list);
        return new ResponseEntity<List<ProjectDTO>>(dtoList, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Project> createNewProject(@Validated @RequestBody Project project)
            throws ResourceNotFoundException {
        return new ResponseEntity<Project>(projectService.saveProject(project), new HttpHeaders(), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDTO> getProjectsById(@PathVariable(value = "id") UUID uuid)
            throws ResourceNotFoundException {
        Project project = projectService.getByUuid(uuid);

        ProjectDTO projectDTO = convertToProjectDto(project);

        return new ResponseEntity<ProjectDTO>(projectDTO, new HttpHeaders(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectDTO> updateExistingProject(@PathVariable(value = "id") UUID uuid,
            @Validated @RequestBody Project projectDetails)
            throws ResourceAlreadyExistsException, ResourceNotFoundException {

        Project project = projectService.getByUuid(uuid);

        final Project updatedProject = projectService.updateProject(project, projectDetails);
        ProjectDTO projectDTO = convertToProjectDto(updatedProject);
        projectService.saveProject(updatedProject);

        return new ResponseEntity<ProjectDTO>(projectDTO, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Project> deleteProject(@PathVariable(value = "id") UUID uuid)
            throws ResourceNotFoundException {
        Project project = projectService.getByUuid(uuid);

        projectService.deleteProject(project);
        return ResponseEntity.ok(project);
    }

    @GetMapping("/{id}/recruiters")
    public ResponseEntity<List<UserDTO>> getAllRecruiters(@PathVariable(value = "id") UUID uuid) {
        List<User> recruiters = projectService.fetchRecruiters(uuid);
        List<UserDTO> userDtos = new ArrayList<UserDTO>();
        for (User usr : recruiters) {
            UserDTO userDto = UserDTO.builder().uuid(usr.getUuid()).firstName(usr.getFirstName())
                    .lastName(usr.getLastName()).middleName(usr.getMiddleName()).employeeId(usr.getEmployeeId())
                    .profilePicture(usr.getProfilePicture()).dateOfBirth(usr.getDateOfBirth()).email(usr.getEmail())
                    .phone(usr.getPhone()).admin(usr.getAdmin()).build();
            userDtos.add(userDto);
        }
        return new ResponseEntity<List<UserDTO>>(userDtos, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}/sourcers")
    public ResponseEntity<List<UserDTO>> getAllSourcers(@PathVariable(value = "id") UUID uuid) {
        List<User> sourcers = projectService.fetchSourcers(uuid);
        List<UserDTO> userDtos = new ArrayList<UserDTO>();
        for (User usr : sourcers) {
            UserDTO userDto = UserDTO.builder().uuid(usr.getUuid()).firstName(usr.getFirstName())
                    .lastName(usr.getLastName()).middleName(usr.getMiddleName()).employeeId(usr.getEmployeeId())
                    .profilePicture(usr.getProfilePicture()).dateOfBirth(usr.getDateOfBirth()).email(usr.getEmail())
                    .phone(usr.getPhone()).admin(usr.getAdmin()).build();
            userDtos.add(userDto);
        }
        return new ResponseEntity<List<UserDTO>>(userDtos, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}/projectmanager")
    public ResponseEntity<UserDTO> getProjectManager(@PathVariable(value = "id") UUID uuid) {
        User projectManager = projectService.fetchProjectManager(uuid);
        UserDTO userDto = UserDTO.builder().uuid(projectManager.getUuid()).firstName(projectManager.getFirstName())
                .lastName(projectManager.getLastName()).middleName(projectManager.getMiddleName())
                .employeeId(projectManager.getEmployeeId())
                .profilePicture(projectManager.getProfilePicture()).dateOfBirth(projectManager.getDateOfBirth())
                .email(projectManager.getEmail())
                .phone(projectManager.getPhone()).admin(projectManager.getAdmin()).build();
        return new ResponseEntity<UserDTO>(userDto, new HttpHeaders(), HttpStatus.OK);

    }

    @GetMapping(value = "/{id}/positions")
    public ResponseEntity<List<PositionDTO>> getProjectPositions(@PathVariable(value = "id") UUID uuid)
            throws ResourceNotFoundException {
        List<Position> projectPositions = projectService.fetchProjectPositions(uuid);
        List<PositionDTO> positionDtoList = new ArrayList<PositionDTO>();
        for (Position pos : projectPositions) {
            PositionDTO posDto = PositionDTO.builder().uuid(pos.getUuid()).positionName(pos.getPositionName())
                    .projectUuid(uuid).open(pos.getOpen()).hiredCount(pos.getHiredCount())
                    .totalCount(pos.getTotalCount())
                    .build();
            positionDtoList.add(posDto);
        }
        return new ResponseEntity<List<PositionDTO>>(positionDtoList, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/position-count")
    public ResponseEntity<Integer> getPositionCount(@PathVariable(value = "id") UUID uuid)
            throws ResourceNotFoundException {
        int positionCount = projectService.fetchProjectPositionsCount(uuid);
        return new ResponseEntity<Integer>(positionCount, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/assosciate-count")
    public ResponseEntity<Integer> getAssosicateCount(@PathVariable(value = "id") UUID uuid)
            throws ResourceNotFoundException {
        int associateCount = projectService.fetchProjectAssocicateCount(uuid);
        return new ResponseEntity<Integer>(associateCount, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/incomplete-interviews")
    public ResponseEntity<List<InterviewDTO>> getInCompleteInterviews(@PathVariable(value = "id") UUID uuid)
            throws ResourceNotFoundException {
        List<Interview> interviews = projectService.fetchIncompletedInterviews(uuid);
        List<InterviewDTO> interviewDtos = convertToInterviewDto(interviews);
        return new ResponseEntity<List<InterviewDTO>>(interviewDtos, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/complete-interviews")
    public ResponseEntity<List<InterviewDTO>> getCompletedInterviews(@PathVariable(value = "id") UUID uuid)
            throws ResourceNotFoundException {
        List<Interview> interviews = projectService.fetchCompletedInterviews(uuid);
        List<InterviewDTO> interviewDtos = convertToInterviewDto(interviews);
        return new ResponseEntity<List<InterviewDTO>>(interviewDtos, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/pending-candidates")
    public ResponseEntity<List<CandidateDTO>> getPendingCandidates(@PathVariable(value = "id") UUID uuid)
            throws ResourceNotFoundException {
        List<Candidate> candidates = projectService.fetchPendingCandidates(uuid);
        List<CandidateDTO> candidateDtos = convertToCandidateDto(candidates);
        return new ResponseEntity<List<CandidateDTO>>(candidateDtos, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/rejected-candidates")
    public ResponseEntity<List<CandidateDTO>> getRejectedCandidates(@PathVariable(value = "id") UUID uuid)
            throws ResourceNotFoundException {
        List<Candidate> candidates = projectService.fetchRejectedCandidates(uuid);
        List<CandidateDTO> candidateDtos = convertToCandidateDto(candidates);
        return new ResponseEntity<List<CandidateDTO>>(candidateDtos, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/accepted-candidates")
    public ResponseEntity<List<CandidateDTO>> getAcceptedCandidates(@PathVariable(value = "id") UUID uuid)
            throws ResourceNotFoundException {
        List<Candidate> candidates = projectService.fetchAcceptedCandidates(uuid);
        List<CandidateDTO> candidateDtos = convertToCandidateDto(candidates);
        return new ResponseEntity<List<CandidateDTO>>(candidateDtos, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/technical-interview-count")
    public ResponseEntity<Integer> getTechnicalInterviewCount(@PathVariable(value = "id") UUID uuid)
            throws ResourceNotFoundException {
        Integer technicalInterviewCount = projectService.fetchTecnicalInterviewCount(uuid);
        return new ResponseEntity<Integer>(technicalInterviewCount, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/management-interview-count")
    public ResponseEntity<Integer> getManagementInterviewCount(@PathVariable(value = "id") UUID uuid)
            throws ResourceNotFoundException {
        Integer managemenInterviewCount = projectService.fetchManagementInterviewCount(uuid);
        return new ResponseEntity<Integer>(managemenInterviewCount, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/upcoming-technical-interviews")
    public ResponseEntity<List<InterviewDTO>> getUpcomingTechnicalInterviews(@PathVariable(value = "id") UUID uuid)
            throws ResourceNotFoundException {
        List<Interview> interviews = projectService.fetchUpcomingTecnicalInterviews(uuid);
        List<InterviewDTO> interviewDtos = convertToInterviewDto(interviews);
        return new ResponseEntity<List<InterviewDTO>>(interviewDtos, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/upcoming-management-interviews")
    public ResponseEntity<List<InterviewDTO>> getUpcomingManagementInterviews(@PathVariable(value = "id") UUID uuid)
            throws ResourceNotFoundException {
        List<Interview> interviews = projectService.fetchUpcomingManagementInterviews(uuid);
        List<InterviewDTO> interviewDtos = convertToInterviewDto(interviews);
        return new ResponseEntity<List<InterviewDTO>>(interviewDtos, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/interviewers")
    public ResponseEntity<List<UserDTO>> getInterviewers(@PathVariable(name = "id") UUID uuid,
            @RequestParam(defaultValue = "all") String interviewType)
            throws ResourceNotFoundException {
        Set<User> interviewers = projectService.fetchInterviewers(uuid, interviewType);
        List<UserDTO> userDtos = new ArrayList<UserDTO>();
        for (User usr : interviewers) {
            UserDTO userDto = UserDTO.builder().uuid(usr.getUuid()).firstName(usr.getFirstName())
                    .lastName(usr.getLastName()).middleName(usr.getMiddleName()).employeeId(usr.getEmployeeId())
                    .profilePicture(usr.getProfilePicture()).dateOfBirth(usr.getDateOfBirth()).email(usr.getEmail())
                    .phone(usr.getPhone()).admin(usr.getAdmin()).build();
            userDtos.add(userDto);
        }
        return new ResponseEntity<List<UserDTO>>(userDtos, new HttpHeaders(), HttpStatus.OK);
    }

    private List<ProjectDTO> convertToProjectDtoList(List<Project> list) {
        List<ProjectDTO> dtoList = new ArrayList<ProjectDTO>();
        for (Project pj : list) {
            UUID pjuuid = pj.getUuid();
            String pjname = pj.getName();
            String pjdesc = pj.getDescription();
            Integer pjAssocicateCount = projectService.fetchProjectAssocicateCount(pjuuid);
            Integer pjPositionCount = projectService.fetchProjectPositionsCount(pjuuid);
            String pjPmName = projectService.fetchProjectManager(pjuuid).getFirstName()
                    + projectService.fetchProjectManager(pjuuid).getLastName();
            ProjectDTO pjDto = ProjectDTO.builder()
                    .uuid(pjuuid)
                    .name(pjname)
                    .description(pjdesc)
                    .numberOfAssociates(pjAssocicateCount.toString())
                    .numberOfPositions(pjPositionCount.toString())
                    .projectManagerName(pjPmName).build();
            dtoList.add(pjDto);
        }
        return dtoList;
    }

    private ProjectDTO convertToProjectDto(Project project) {
        UUID pjuuid = project.getUuid();
        String pjname = project.getName();
        String pjdesc = project.getDescription();
        Integer pjAssocicateCount = projectService.fetchProjectAssocicateCount(pjuuid);
        Integer pjPositionCount = projectService.fetchProjectPositionsCount(pjuuid);
        String pjPmName = projectService.fetchProjectManager(pjuuid).getFirstName()
                + projectService.fetchProjectManager(pjuuid).getLastName();

        ProjectDTO projectDTO = ProjectDTO.builder()
                .uuid(pjuuid)
                .name(pjname)
                .description(pjdesc)
                .numberOfAssociates(pjAssocicateCount.toString())
                .numberOfPositions(pjPositionCount.toString())
                .projectManagerName(pjPmName).build();
        return projectDTO;
    }

    private List<InterviewDTO> convertToInterviewDto(List<Interview> interviews) {
        List<InterviewDTO> interviewDtos = new ArrayList<InterviewDTO>();
        for (Interview inter : interviews) {
            InterviewDTO interviewDto = InterviewDTO.builder().uuid(inter.getUuid()).projectId(inter.getProject().getUuid())
                    .timeslotId(inter.getTimeslot().getUuid()).interviewerOneId(inter.getInterviewerOne().getUuid())
                    .interviewerTwoId(inter.getInterviewerTwo().getUuid())
                    .isCompleted(inter.getIsCompleted()).build();

            interviewDtos.add(interviewDto);
        }
        return interviewDtos;
    }

    private List<CandidateDTO> convertToCandidateDto(List<Candidate> candidates) {
        List<CandidateDTO> candidateDtos = new ArrayList<CandidateDTO>();
        for (Candidate cand : candidates) {
            CandidateDTO candidateDto = CandidateDTO.builder().uuid(cand.getUuid())
                    .positionId(cand.getPosition().getUuid()).firstName(cand.getPerson().getFirstName())
                    .middleName(cand.getPerson().getMiddleName()).lastName(cand.getPerson().getLastName())
                    .email(cand.getPerson().getEmail()).phone(cand.getPerson().getPhone()).languages(null)
                    .workExperiences(null).educations(null).status(cand.getStatus()).cvPath(cand.getCvPath())
                    .technicalInterviewerId(null).technicalInterviewerId2(null)
                    .managementInterviewerId(null).managementInterviewerId2(null).technicalDocumentationId(null)
                    .managementDocumentationId(null).possibleTimeslots(null).build();
            candidateDtos.add(candidateDto);
        }
        return candidateDtos;
    }

}