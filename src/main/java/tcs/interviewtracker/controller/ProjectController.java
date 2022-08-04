package tcs.interviewtracker.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

    @GetMapping("/")
    public ResponseEntity<List<Project>> getAllProjects(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "5") Integer pageSize,
            @RequestParam(defaultValue = "name") String sortBy) throws ResourceNotFoundException {
        List<Project> list = projectService.getAllProjects(pageNo, pageSize, sortBy);
        return new ResponseEntity<List<Project>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("/")
    public Project createNewProject(@Validated @RequestBody Project project) throws ResourceNotFoundException {
        return projectService.saveProject(project);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjectsById(@PathVariable(value = "id") UUID uuid)
            throws ResourceNotFoundException {
        Project project = projectService.getByUuid(uuid);
        return ResponseEntity.ok().body(project);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Project> updateExistingProject(@PathVariable(value = "id") UUID uuid,
            @Validated @RequestBody Project projectDetails)
            throws ResourceAlreadyExistsException {

        Project project = projectService.getByUuid(uuid);

        final Project updatedProject = projectService.updateProject(project, projectDetails);
        projectService.saveProject(updatedProject);
        return ResponseEntity.ok(updatedProject);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity <Project> deleteProject(@PathVariable(value = "id") UUID uuid)
            throws ResourceNotFoundException {
        Project project = projectService.getByUuid(uuid);

        projectService.deleteProject(project);
        return ResponseEntity.ok(project);
    }

    @GetMapping("/{id}/recruiters")
    public ResponseEntity<List<UUID>> getAllRecruiters(@PathVariable(value = "id") UUID uuid) {
        List<User> recruiters = projectService.fetchRecruiters(uuid);
        List<UUID> uuids = new ArrayList<UUID>();
        for (User user : recruiters) {
            uuids.add(user.getUuid());
        }
        return new ResponseEntity<List<UUID>>(uuids, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}/sourcers")
    public ResponseEntity<List<UUID>> getAllSourcers(@PathVariable(value = "id") UUID uuid) {
        List<User> sourcers = projectService.fetchSourcers(uuid);
        List<UUID> uuids = new ArrayList<UUID>();
        for (User user : sourcers) {
            uuids.add(user.getUuid());
        }
        return new ResponseEntity<List<UUID>>(uuids, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}/projectmanager")
    public ResponseEntity<UUID> getProjectManager(@PathVariable(value = "id") UUID uuid) {
        User projectManager = projectService.fetchProjectManager(uuid);
        try {
            UUID uuidPm = projectManager.getUuid();
            return new ResponseEntity<UUID>(uuidPm, new HttpHeaders(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<UUID>(null, new HttpHeaders(), HttpStatus.OK);
        }
    }

    @GetMapping(value = "/{id}/positions")
    public ResponseEntity<List<Position>> getProjectPositions(@PathVariable(value = "id") UUID uuid,
            @RequestParam(name = "project") Long projectId) {
        List<Position> projectPositions = projectService.fetchProjectPositions(uuid);
        return new ResponseEntity<List<Position>>(projectPositions, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/position-count")
    public ResponseEntity<Integer> getPositionCount(@PathVariable(value = "id") UUID uuid,
            @RequestParam(name = "project") Long projectId)
            throws ResourceNotFoundException {
        int positionCount = projectService.fetchProjectPositionsCount(uuid);
        return new ResponseEntity<Integer>(positionCount, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/assosciate-count")
    public ResponseEntity<Integer> getAssosicateCount(@PathVariable(value = "id") UUID uuid,
            @RequestParam(name = "project") Long projectId)
            throws ResourceNotFoundException {
        int associateCount = projectService.fetchProjectAssocicateCount(uuid);
        return new ResponseEntity<Integer>(associateCount, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/incomplete-interviews")
    public ResponseEntity<List<Interview>> getInCompleteInterviews(@PathVariable(value = "id") UUID uuid,
            @RequestParam(name = "project", required = true) Long projectId)
            throws ResourceNotFoundException {
        List<Interview> interviews = projectService.fetchIncompletedInterviews(uuid);
        return new ResponseEntity<List<Interview>>(interviews, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/complete-interviews")
    public ResponseEntity<List<Interview>> getCompletedInterviews(@PathVariable(value = "id") UUID uuid,
            @RequestParam(name = "project") Long projectId)
            throws ResourceNotFoundException {
        List<Interview> interviews = projectService.fetchCompletedInterviews(uuid);
        return new ResponseEntity<List<Interview>>(interviews, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/pending-candidates")
    public ResponseEntity<List<Candidate>> getPendingCandidates(@PathVariable(value = "id") UUID uuid,
            @RequestParam(name = "project") Long projectId)
            throws ResourceNotFoundException {
        List<Candidate> pendingCandidates = projectService.fetchPendingCandidates(uuid);
        return new ResponseEntity<List<Candidate>>(pendingCandidates, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/rejected-candidates")
    public ResponseEntity<List<Candidate>> getRejectedCandidates(@PathVariable(value = "id") UUID uuid,
            @RequestParam(name = "project") Long projectId)
            throws ResourceNotFoundException {
        List<Candidate> rejectedCandidates = projectService.fetchRejectedCandidates(uuid);
        return new ResponseEntity<List<Candidate>>(rejectedCandidates, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/accepted-candidates")
    public ResponseEntity<List<Candidate>> getAcceptedCandidates(@PathVariable(value = "id") UUID uuid,
            @RequestParam(name = "project") Long projectId)
            throws ResourceNotFoundException {
        List<Candidate> acceptedCandidates = projectService.fetchAcceptedCandidates(uuid);
        return new ResponseEntity<List<Candidate>>(acceptedCandidates, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/techical-interview-count")
    public ResponseEntity<Integer> getTechnicalInterviewCount(@PathVariable(value = "id") UUID uuid,
            @RequestParam(name = "project") Long projectId)
            throws ResourceNotFoundException {
        Integer technicalInterviewCount = projectService.fetchTecnicalInterviewCount(uuid);
        return new ResponseEntity<Integer>(technicalInterviewCount, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/management-interview-count")
    public ResponseEntity<Integer> getManagementInterviewCount(@PathVariable(value = "id") UUID uuid,
            @RequestParam(name = "project") Long projectId)
            throws ResourceNotFoundException {
        Integer managemenInterviewCount = projectService.fetchManagementInterviewCount(uuid);
        return new ResponseEntity<Integer>(managemenInterviewCount, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/upcoming-tecnical-interviews")
    public ResponseEntity<List<Interview>> getUpcomingTechnicalInterviews(@PathVariable(value = "id") UUID uuid,
            @RequestParam(name = "project") Long projectId)
            throws ResourceNotFoundException {
        List<Interview> upcomingTechInterviews = projectService.fetchUpcomingTecnicalInterviews(uuid);
        return new ResponseEntity<List<Interview>>(upcomingTechInterviews, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/upcoming-management-interviews")
    public ResponseEntity<List<Interview>> getUpcomingManagementInterviews(@PathVariable(value = "id") UUID uuid,
            @RequestParam(name = "project") Long projectId)
            throws ResourceNotFoundException {
        List<Interview> upcomingManagemeInterviews = projectService.fetchUpcomingManagementInterviews(uuid);
        return new ResponseEntity<List<Interview>>(upcomingManagemeInterviews, new HttpHeaders(), HttpStatus.OK);
    }

}