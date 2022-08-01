package tcs.interviewtracker.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import tcs.interviewtracker.persistence.Candidate;
import tcs.interviewtracker.persistence.Interview;
import tcs.interviewtracker.persistence.Position;
import tcs.interviewtracker.persistence.Project;
import tcs.interviewtracker.persistence.Timeslot;
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

    public List<Timeslot> fetchCompletedInterviews(Long projectId) {
        Project project = projectRepository.getReferenceById(projectId);
        List<Timeslot> timeslots = projectRepository.findByIsCompletedTrue(project);
        return timeslots;
    }

    public List<Timeslot> fetchIncompletedInterviews(Long projectId) {
        Project project = projectRepository.getReferenceById(projectId);
        List<Timeslot> timeslots = projectRepository.findByIsCompletedFalse(project);
        return timeslots;
    }

    public List<Candidate> fetchPendingCandidates(Long projectId) {
        Project project = projectRepository.getReferenceById(projectId);
        List<Candidate> candidates = projectRepository.findPendingCandidates(project);
        return candidates;
    }

    public List<Candidate> fetchRejectedCandidates(Long projectId) {
        Project project = projectRepository.getReferenceById(projectId);
        List<Candidate> candidates = projectRepository.findRejectedCandidates(project);
        return candidates;
    }

    public List<Candidate> fetchAcceptedCandidates(Long projectId) {
        Project project = projectRepository.getReferenceById(projectId);
        List<Candidate> candidates = projectRepository.findAcceptedCandidates(project);
        return candidates;
    }

    public Integer fetchTecnicalInterviewCount(Long projectId) {
        Project project = projectRepository.getReferenceById(projectId);
        Integer techInterviewCount = projectRepository.findTechnicalInterviewCount(project);
        return techInterviewCount;
    }

    public Integer fetchManagementIntervewCount(Long projectId) {
        Project project = projectRepository.getReferenceById(projectId);
        Integer techInterviewCount = projectRepository.findManagementInterviewCount(project);
        return techInterviewCount;
    }

    public List<Interview> fetchUpcomingTecnicalInterviews(Long projectId) {
        Project project = projectRepository.getReferenceById(projectId);
        List<Interview> upcomingTechInterviews = projectRepository.findUpcomingTechnicalInterviews(project);
        return upcomingTechInterviews;
    }

    public List<Interview> fetchUpcomingManagementInterviews(Long projectId) {
        Project project = projectRepository.getReferenceById(projectId);
        List<Interview> upcomingManagementInterviews = projectRepository.findUpcomingManagementInterviews(project);
        return upcomingManagementInterviews;
    }

    public Optional<Project> getByUuid(UUID projectUuid) {
        return this.projectRepository.getByUuid(projectUuid);
    }

}
