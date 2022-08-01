package tcs.interviewtracker.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
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

    public Project getByUuid(UUID uuid) {
        return projectRepository.findByUuid(uuid);
    }

    public Project getById(Long id) {
        return projectRepository.getReferenceById(id);
    }

    public Project updateProject(Project project, Project projectDetails) {

        project.setName(projectDetails.getName());
        project.setProjectManager(projectDetails.getProjectManager());
        project.setDescription(projectDetails.getDescription());
        project.setRecruiter(projectDetails.getProjectManager());
        project.setSourcer(projectDetails.getProjectManager());
        project.setDeadline(projectDetails.getDeadline());

        return project;
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Project saveProject(Project project) {
        UUID newUuid = UUID.randomUUID();
        Project savedProject = Project.builder().uuid(newUuid).recruiter(project.getRecruiter())
                .sourcer(project.getSourcer()).deadline(project.getDeadline()).name(project.getName()).build();
        return projectRepository.save(savedProject);
    }

    public void deleteProject(Project project) {
        projectRepository.delete(project);
    }

    public List<Position> fetchProjectPositions(UUID uuid) {
        Project project = projectRepository.findByUuid(uuid);
        List<Position> projectPositions = project.getProjectPositions();
        return projectPositions;
    }

    public int fetchProjectPositionsCount(UUID uuid) {
        List<Position> positions = fetchProjectPositions(uuid);
        return positions.size();
    }

    public int fetchProjectAssocicateCount(UUID uuid) {
        Project project = projectRepository.findByUuid(uuid);
        List<Position> projePositions = project.getProjectPositions();
        return projePositions.size();
    }

    public List<Timeslot> fetchCompletedInterviews(UUID uuid) {
        Project project = projectRepository.findByUuid(uuid);
        List<Timeslot> timeslots = projectRepository.findByIsCompletedTrue(project);
        return timeslots;
    }

    public List<Timeslot> fetchIncompletedInterviews(UUID uuid) {
        Project project = projectRepository.findByUuid(uuid);
        List<Timeslot> timeslots = projectRepository.findByIsCompletedFalse(project);
        return timeslots;
    }

    public List<Candidate> fetchPendingCandidates(UUID uuid) {
        Project project = projectRepository.findByUuid(uuid);
        List<Candidate> candidates = projectRepository.findPendingCandidates(project);
        return candidates;
    }

    public List<Candidate> fetchRejectedCandidates(UUID uuid) {
        Project project = projectRepository.findByUuid(uuid);
        List<Candidate> candidates = projectRepository.findRejectedCandidates(project);
        return candidates;
    }

    public List<Candidate> fetchAcceptedCandidates(UUID uuid) {
        Project project = projectRepository.findByUuid(uuid);
        List<Candidate> candidates = projectRepository.findAcceptedCandidates(project);
        return candidates;
    }

    public Integer fetchTecnicalInterviewCount(UUID uuid) {
        Project project = projectRepository.findByUuid(uuid);
        Integer techInterviewCount = projectRepository.findTechnicalInterviewCount(project);
        return techInterviewCount;
    }

    public Integer fetchManagementIntervewCount(UUID uuid) {
        Project project = projectRepository.findByUuid(uuid);
        Integer techInterviewCount = projectRepository.findManagementInterviewCount(project);
        return techInterviewCount;
    }

    public List<Interview> fetchUpcomingTecnicalInterviews(UUID uuid) {
        Project project = projectRepository.findByUuid(uuid);
        List<Interview> upcomingTechInterviews = projectRepository.findUpcomingTechnicalInterviews(project);
        return upcomingTechInterviews;
    }

    public List<Interview> fetchUpcomingManagementInterviews(UUID uuid) {
        Project project = projectRepository.findByUuid(uuid);
        List<Interview> upcomingManagementInterviews = projectRepository.findUpcomingManagementInterviews(project);
        return upcomingManagementInterviews;
    }

}
