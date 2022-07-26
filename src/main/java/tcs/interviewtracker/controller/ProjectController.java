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
import tcs.interviewtracker.repository.ProjectRepository;
import tcs.interviewtracker.service.ProjectService;

@RestController
@RequestMapping("api/projects")
public class ProjectController {

    private ProjectService projectService;
    private ProjectRepository projectRepository;

    public ProjectController(@Autowired ProjectService projectService, @Autowired ProjectRepository projectRepository) {
        this.projectService = projectService;
        this.projectRepository = projectRepository;
    }

    @GetMapping("/")
    public List<Project> getAllDoctors() {
        return projectService.getAllAProjects();
    }

    @PostMapping("/")
    public Project createNewProject(@Validated @RequestBody Project project) {
        return projectRepository.save(project);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjectsById(@PathVariable(value = "id") Long projectId)
            throws Exception {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new Exception("Project not found with id: " + projectId));
        return ResponseEntity.ok().body(project);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Project> updateExistingProject(@PathVariable(value = "id") Long id,
            @Validated @RequestBody Project projectDetails)
            throws Exception {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new Exception("Project not found with id: " + id));

        project.setName(projectDetails.getName());
        project.setProjectManagerId(projectDetails.getProjectManagerId());
        project.setDescription(projectDetails.getDescription());
        project.setRecruiterId(projectDetails.getRecruiterId());
        project.setSourcerId(projectDetails.getSourcerId());
        project.setDeadline(projectDetails.getDeadline());

        final Project updatedProject = projectRepository.save(project);
        return ResponseEntity.ok(updatedProject);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Project> deleteProject(@PathVariable(value = "id") Long id) throws Exception {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new Exception("Project not found with id: " + id));
        projectRepository.delete(project);
        return ResponseEntity.ok(project);
    }

}
