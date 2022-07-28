package tcs.interviewtracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tcs.interviewtracker.persistence.Position;
import tcs.interviewtracker.persistence.Project;
import tcs.interviewtracker.repository.ProjectRepository;

@Service
public class ProjectService {

    private ProjectRepository projectRepository;

    public ProjectService(@Autowired ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Project getById(Long id) {
        return projectRepository.getReferenceById(id);
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Project saveProject(Project project) {
        return projectRepository.save(project);
    }

    public void deleteProject(Project project) {
        projectRepository.delete(project);
    }

    public List<Position> fetchProjectPositions(Long projectId) {
        Project project = projectRepository.getReferenceById(projectId);
        List<Position> projectPositions = project.getProjectPositions();
        return projectPositions;
    }

    public int fetchProjectPositionsCount(Long projectId) {
        List<Position> positions = fetchProjectPositions(projectId);
        return positions.size();
    }

    public int fetchProjectAssocicateCount(Long projectId) {
        Project project = projectRepository.getReferenceById(projectId);
        List<Position> projePositions = project.getProjectPositions();
        return projePositions.size();
    }

}
