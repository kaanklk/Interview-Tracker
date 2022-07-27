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

import tcs.interviewtracker.persistence.Project;
import tcs.interviewtracker.persistence.User;
import tcs.interviewtracker.service.ProjectService;
import tcs.interviewtracker.service.UserService;

@RestController
@RequestMapping("api/projects")
public class ProjectController {

    private ProjectService projectService;
    private UserService userService;
    // positionService

    public ProjectController(@Autowired ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/")
    public List<Project> getAllDoctors() {
        return projectService.getAllAProjects();
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
        User projectManager = userService.getProjectManager(projectDetails.getId());
        // User user = userService.getSourcer()?
        // User user = userService.getRecruiter()?

        project.setName(projectDetails.getName());
        // project.setProjectManagerId(projectDetails.getProjectManager());
        project.setDescription(projectDetails.getDescription());
        // project.setRecruiterId(projectDetails.getRecruiter());
        // project.setSourcerId(projectDetails.getSourcer());
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

    // TODO: IMPLEMENT POSITIONS

    // @GetMapping(value = "/{id}/positions")
    // public List<Position> getMethodName(@PathVariable(value = "id") Long id)
    // throws Exception {

    // }

    // TODO: IMPLMENT POSITION COUNT
    // @GetMapping(value="/{id}/position-count")
    // public SomeData getMethodName(@RequestParam String param) {
    // return new SomeData();
    // }

    // @GetMapping(value = "/{id}/assosciate-count")
    // public SomeData getMethodName(@RequestParam String param) {
    // return new SomeData();
    // }

    // @GetMapping(value = "/{id}/incomplete-interviews")
    // public SomeData getMethodName(@RequestParam String param) {
    // return new SomeData();
    // }

    // @GetMapping(value = "/{id}/complete-interviews")
    // public SomeData getMethodName(@RequestParam String param) {
    // return new SomeData();
    // }

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