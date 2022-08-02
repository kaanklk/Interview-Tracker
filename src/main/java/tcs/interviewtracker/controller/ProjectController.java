package tcs.interviewtracker.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
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
import tcs.interviewtracker.persistence.Timeslot;
import tcs.interviewtracker.service.ProjectService;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    private ProjectService projectService;

    public ProjectController(@Autowired ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/")
    public List<Project> getAllDoctors() throws ResourceNotFoundException {
        return projectService.getAllProjects();
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

    @GetMapping(value = "/{id}/positions")
    public List<Position> getProjectPositions(@PathVariable(value = "id") UUID uuid,
            @RequestParam(name = "project") Long projectId)
            throws ResourceNotFoundException {
        return projectService.fetchProjectPositions(uuid);
    }

    @GetMapping(value = "/{id}/position-count")
    public ResponseEntity<Integer> getPositionCount(@PathVariable(value = "id") UUID uuid,
            @RequestParam(name = "project") Long projectId)
            throws ResourceNotFoundException {
        int positionCount = projectService.fetchProjectPositionsCount(uuid);
        return ResponseEntity.ok(positionCount);
    }

    @GetMapping(value = "/{id}/assosciate-count")
    public ResponseEntity<Integer> getAssosicateCount(@PathVariable(value = "id") UUID uuid,
            @RequestParam(name = "project") Long projectId)
            throws ResourceNotFoundException {
        int associateCount = projectService.fetchProjectAssocicateCount(uuid);
        return ResponseEntity.ok(associateCount);
    }

    @GetMapping(value = "/{id}/incompleteinterviews")
    public ResponseEntity<List<Timeslot>> getInCompleteInterviews(@PathVariable(value = "id") UUID uuid,
            @RequestParam(name = "project", required = true) Long projectId)
            throws ResourceNotFoundException {
        List<Timeslot> timeslots = projectService.fetchIncompletedInterviews(uuid);
        return ResponseEntity.ok(timeslots);
    }

    @GetMapping(value = "/{id}/complete-interviews")
    public ResponseEntity<List<Timeslot>> getCompletedInterviews(@PathVariable(value = "id") UUID uuid,
            @RequestParam(name = "project") Long projectId)
            throws ResourceNotFoundException {
        List<Timeslot> timeslots = projectService.fetchCompletedInterviews(uuid);
        return ResponseEntity.ok(timeslots);
    }

    @GetMapping(value = "/{id}/pending-candidates")
    public ResponseEntity<List<Candidate>> getPendingCandidates(@PathVariable(value = "id") UUID uuid,
            @RequestParam(name = "project") Long projectId)
            throws ResourceNotFoundException {
        List<Candidate> pendingCandidates = projectService.fetchPendingCandidates(uuid);
        return ResponseEntity.ok(pendingCandidates);
    }

    @GetMapping(value = "/{id}/rejected-candidates")
    public ResponseEntity<List<Candidate>> getRejectedCandidates(@PathVariable(value = "id") UUID uuid,
            @RequestParam(name = "project") Long projectId)
            throws ResourceNotFoundException {
        List<Candidate> rejectedCandidates = projectService.fetchRejectedCandidates(uuid);
        return ResponseEntity.ok(rejectedCandidates);
    }

    @GetMapping(value = "/{id}/accepted-candidates")
    public ResponseEntity<List<Candidate>> getAcceptedCandidates(@PathVariable(value = "id") UUID uuid,
            @RequestParam(name = "project") Long projectId)
            throws ResourceNotFoundException {
        List<Candidate> acceptedCandidates = projectService.fetchAcceptedCandidates(uuid);
        return ResponseEntity.ok(acceptedCandidates);
    }

    @GetMapping(value = "/{id}/techical-interview-count")
    public ResponseEntity<Integer> getTechnicalInterviewCount(@PathVariable(value = "id") UUID uuid,
            @RequestParam(name = "project") Long projectId)
            throws ResourceNotFoundException {
        Integer technicalInterviewCount = projectService.fetchTecnicalInterviewCount(uuid);
        return ResponseEntity.ok(technicalInterviewCount);
    }

    @GetMapping(value = "/{id}/management-interview-count")
    public ResponseEntity<Integer> getManagementInterviewCount(@PathVariable(value = "id") UUID uuid,
            @RequestParam(name = "project") Long projectId)
            throws ResourceNotFoundException {
        Integer managemenInterviewCount = projectService.fetchManagementIntervewCount(uuid);
        return ResponseEntity.ok(managemenInterviewCount);
    }

    @GetMapping(value = "/{id}/upcoming-tecnical-interviews")
    public ResponseEntity<List<Interview>> getUpcomingTechnicalInterviews(@PathVariable(value = "id") UUID uuid,
            @RequestParam(name = "project") Long projectId)
            throws ResourceNotFoundException {
        List<Interview> upcomingTechInterviews = projectService.fetchUpcomingTecnicalInterviews(uuid);
        return ResponseEntity.ok(upcomingTechInterviews);
    }

    @GetMapping(value = "/{id}/upcoming-management-interviews")
    public ResponseEntity<List<Interview>> getUpcomingManagementInterviews(@PathVariable(value = "id") UUID uuid,
            @RequestParam(name = "project") Long projectId)
            throws ResourceNotFoundException {
        List<Interview> upcomingManagemeInterviews = projectService.fetchUpcomingManagementInterviews(uuid);
        return ResponseEntity.ok(upcomingManagemeInterviews);
    }

}