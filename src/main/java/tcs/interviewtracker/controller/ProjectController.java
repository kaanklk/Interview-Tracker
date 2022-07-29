package tcs.interviewtracker.controller;

import java.util.List;

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
@RequestMapping("api/projects")
public class ProjectController {

    private ProjectService projectService;
    // private UserService userService;
    // private PositionService positionService;

    // private static final String PM = "Project Manager";
    // private static final String SOURCER = "Sourcer";
    // private static final String RECRUITER = "Project Manager";

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
    public ResponseEntity<Project> getProjectsById(@PathVariable(value = "id") Long projectId)
            throws ResourceNotFoundException {
        Project project = projectService.getById(projectId);
        return ResponseEntity.ok().body(project);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Project> updateExistingProject(@PathVariable(value = "id") Long id,
            @Validated @RequestBody Project projectDetails)
            throws ResourceAlreadyExistsException {

        Project project = projectService.getById(id);
        // User projectManager =
        // userService.getUserWithSpesificRole(projectDetails.getId(), PM);
        // User sourcer = userService.getUserWithSpesificRole(projectDetails.getId(),
        // SOURCER);
        // User recruiter = userService.getUserWithSpesificRole(projectDetails.getId(),
        // RECRUITER);

        // project.setName(projectDetails.getName());
        // project.setProjectManager(projectManager);
        // project.setDescription(projectDetails.getDescription());
        // project.setRecruiter(recruiter);
        // project.setSourcer(sourcer);
        // project.setDeadline(projectDetails.getDeadline());

        final Project updatedProject = projectService.saveProject(project);
        return ResponseEntity.ok(updatedProject);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Project> deleteProject(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        Project project = projectService.getById(id);

        projectService.deleteProject(project);
        return ResponseEntity.ok(project);
    }

    @GetMapping(value = "/{id}/positions")
    public List<Position> getProjectPositions(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        return projectService.fetchProjectPositions(id);
    }

    @GetMapping(value = "/{id}/position-count")
    public ResponseEntity<Integer> getPositionCount(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        int positionCount = projectService.fetchProjectPositionsCount(id);
        return ResponseEntity.ok(positionCount);
    }

    @GetMapping(value = "/{id}/assosciate-count")
    public ResponseEntity<Integer> getAssosicateCount(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        int associateCount = projectService.fetchProjectAssocicateCount(id);
        return ResponseEntity.ok(associateCount);
    }

    @GetMapping(value = "/{id}/incomplete-interviews")
    public ResponseEntity<List<Timeslot>> getInCompleteInterviews(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        List<Timeslot> timeslots = projectService.fetchIncompletedInterviews(id);
        return ResponseEntity.ok(timeslots);
    }

    @GetMapping(value = "/{id}/complete-interviews")
    public ResponseEntity<List<Timeslot>> getCompletedInterviews(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        List<Timeslot> timeslots = projectService.fetchCompletedInterviews(id);
        return ResponseEntity.ok(timeslots);
    }

    @GetMapping(value = "/{id}/pending-candidates")
    public ResponseEntity<List<Candidate>> getPendingCandidates(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        List<Candidate> pendingCandidates = projectService.fetchPendingCandidates(id);
        return ResponseEntity.ok(pendingCandidates);
    }

    @GetMapping(value = "/{id}/rejected-candidates")
    public ResponseEntity<List<Candidate>> getRejectedCandidates(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        List<Candidate> rejectedCandidates = projectService.fetchRejectedCandidates(id);
        return ResponseEntity.ok(rejectedCandidates);
    }

    @GetMapping(value = "/{id}/accepted-candidates")
    public ResponseEntity<List<Candidate>> getAcceptedCandidates(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        List<Candidate> acceptedCandidates = projectService.fetchAcceptedCandidates(id);
        return ResponseEntity.ok(acceptedCandidates);
    }

    @GetMapping(value = "/{id}/techical-interview-count")
    public ResponseEntity<Integer> getTechnicalInterviewCount(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        Integer technicalInterviewCount = projectService.fetchTecnicalInterviewCount(id);
        return ResponseEntity.ok(technicalInterviewCount);
    }

    @GetMapping(value = "/{id}/management-interview-count")
    public ResponseEntity<Integer> getManagementInterviewCount(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        Integer managemenInterviewCount = projectService.fetchManagementIntervewCount(id);
        return ResponseEntity.ok(managemenInterviewCount);
    }

    @GetMapping(value = "/{id}/upcoming-tecnical-interviews")
    public ResponseEntity<List<Interview>> getUpcomingTechnicalInterviews(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        List<Interview> upcomingTechInterviews = projectService.fetchUpcomingTecnicalInterviews(id);
        return ResponseEntity.ok(upcomingTechInterviews);
    }

    @GetMapping(value = "/{id}/upcoming-management-interviews")
    public ResponseEntity<List<Interview>> getUpcomingManagementInterviews(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        List<Interview> upcomingManagemeInterviews = projectService.fetchUpcomingManagementInterviews(id);
        return ResponseEntity.ok(upcomingManagemeInterviews);
    }

}