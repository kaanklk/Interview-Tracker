package tcs.interviewtracker.controller;

import java.util.List;

import org.apache.catalina.connector.Response;
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

import tcs.interviewtracker.persistence.Position;
import tcs.interviewtracker.persistence.Project;
import tcs.interviewtracker.persistence.Timeslot;
import tcs.interviewtracker.persistence.User;
import tcs.interviewtracker.service.ProjectService;
import tcs.interviewtracker.service.UserService;

@RestController
@RequestMapping("api/projects")
public class ProjectController {

    private ProjectService projectService;
    private UserService userService;
    // private PositionService positionService;

    private static final String PM = "Project Manager";
    private static final String SOURCER = "Sourcer";
    private static final String RECRUITER = "Project Manager";

    public ProjectController(@Autowired ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/")
    public List<Project> getAllDoctors() {
        return projectService.getAllProjects();
    }

    @PostMapping("/")
    public Project createNewProject(@Validated @RequestBody Project project) {
        return projectService.saveProject(project);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjectsById(@PathVariable(value = "id") Long projectId)
            throws Exception {
        Project project = projectService.getById(projectId);
        return ResponseEntity.ok().body(project);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Project> updateExistingProject(@PathVariable(value = "id") Long id,
            @Validated @RequestBody Project projectDetails)
            throws Exception {

        Project project = projectService.getById(id);
        User projectManager = userService.getUserWithSpesificRole(projectDetails.getId(), PM);
        User sourcer = userService.getUserWithSpesificRole(projectDetails.getId(), SOURCER);
        User recruiter = userService.getUserWithSpesificRole(projectDetails.getId(), RECRUITER);

        project.setName(projectDetails.getName());
        project.setProjectManager(projectManager);
        project.setDescription(projectDetails.getDescription());
        project.setRecruiter(recruiter);
        project.setSourcer(sourcer);
        project.setDeadline(projectDetails.getDeadline());

        final Project updatedProject = projectService.saveProject(project);
        return ResponseEntity.ok(updatedProject);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Project> deleteProject(@PathVariable(value = "id") Long id) throws Exception {
        Project project = projectService.getById(id);

        projectService.deleteProject(project);
        return ResponseEntity.ok(project);
    }

    @GetMapping(value = "/{id}/positions")
    public List<Position> fetchProjectPositions(@PathVariable(value = "id") Long id)
            throws Exception {
        return projectService.fetchProjectPositions(id);
    }

    @GetMapping(value = "/{id}/position-count")
    public ResponseEntity<Integer> fetchPositionCount(@PathVariable(value = "id") Long id) {
        int positionCount = projectService.fetchProjectPositionsCount(id);
        return ResponseEntity.ok(positionCount);
    }

    @GetMapping(value = "/{id}/assosciate-count")
    public ResponseEntity<Integer> fetchAssosicateCount(@PathVariable(value = "id") Long id) {
        int associateCount = projectService.fetchProjectAssocicateCount(id);
        return ResponseEntity.ok(associateCount);
    }

    @GetMapping(value = "/{id}/incomplete-interviews")
    public ResponseEntity<List<Timeslot>> fetchInCompleteInterviews(@PathVariable(value = "id") Long id) {
        List<Timeslot> timeslots = projectService.fetchIncompletedInterviews(id);
        return ResponseEntity.ok(timeslots);
    }

    @GetMapping(value = "/{id}/complete-interviews")
    public ResponseEntity<List<Timeslot>> fetchCompletedInterviews(@PathVariable(value = "id") Long id) {
        List<Timeslot> timeslots = projectService.fetchCompletedInterviews(id);
        return ResponseEntity.ok(timeslots);
    }

    // @GetMapping(value = "/{id}/pending-candidates")
    // public SomeData getMethodName(@RequestParam String param) {
    // return new SomeData();
    // }

    // @GetMapping(value = "/{id}/rejected-candidates")
    // public SomeData getMethodName(@RequestParam String param) {
    // return new SomeData();
    // }

    // @GetMapping(value="/{id}/accepted-candidates)
    // public SomeData getMethodName(@RequestParam String param) {
    // return new SomeData();
    // }

    // @GetMapping(value = "/{id}/techical-interview-count")
    // public SomeData getMethodName(@RequestParam String param) {
    // return new SomeData();
    // }

    // @GetMapping(value = "/{id}/management-interview-count")
    // public SomeData getMethodName(@RequestParam String param) {
    // return new SomeData();
    // }

    // @GetMapping(value = "/{id}/upcoming-tecnical-interviews")
    // public SomeData getMethodName(@RequestParam String param) {
    // return new SomeData();
    // }

    // @GetMapping(value = "/{id}/upcoming-manegement-interviews")
    // public SomeData getMethodName(@RequestParam String param) {
    // return new SomeData();
    // }

}