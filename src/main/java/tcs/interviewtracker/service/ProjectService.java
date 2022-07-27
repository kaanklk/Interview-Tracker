package tcs.interviewtracker.service;

import java.util.List;

import javax.persistence.criteria.Order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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

}
