package tcs.interviewtracker.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import tcs.interviewtracker.DTOs.PositionDTO;
import tcs.interviewtracker.persistence.Candidate;
import tcs.interviewtracker.persistence.Interview;
import tcs.interviewtracker.persistence.Position;
import tcs.interviewtracker.persistence.Project;
import tcs.interviewtracker.persistence.Role;
import tcs.interviewtracker.persistence.Timeslot;
import tcs.interviewtracker.persistence.User;
import tcs.interviewtracker.persistence.UserRoles;
import tcs.interviewtracker.repository.ProjectRepository;
import tcs.interviewtracker.repository.RoleRepository;
import tcs.interviewtracker.repository.UserRepository;
import tcs.interviewtracker.repository.UserRolesRepository;

@Service
@AllArgsConstructor
public class ProjectService {

    private ProjectRepository projectRepository;
    private UserRolesRepository userRolesRepository;
    private UserRepository userRepository;
    private RoleRepository roleRepository;

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
        project.setRecruiters(projectDetails.getRecruiters());
        project.setSourcers(projectDetails.getSourcers());
        project.setDeadline(projectDetails.getDeadline());

        return project;
    }

    public List<Project> getAllProjects(Integer pageNo, Integer pageSize, String sortBy) {
        PageRequest paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Project> pagedResult = projectRepository.findAll(paging);
        if (pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Project>();
        }
    }

    public Project saveProject(Project project) {
        UUID newUuid = UUID.randomUUID();
        Project savedProject = Project.builder().uuid(newUuid).recruiters(project.getRecruiters())
                .sourcers(project.getSourcers()).deadline(project.getDeadline()).name(project.getName()).build();
        return projectRepository.save(savedProject);
    }

    public void deleteProject(Project project) {
        projectRepository.delete(project);
    }

    public List<PositionDTO> fetchProjectPositions(UUID uuid) {
        Project project = projectRepository.findByUuid(uuid);
        List<Position> projectPositions = project.getProjectPositions();
        return projectPositions;
    }

    public int fetchProjectPositionsCount(UUID uuid) {
        List<PositionDTO> positions = fetchProjectPositions(uuid);
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

    public List<User> fetchRecruiters(UUID projectUuid) {
        List<UserRoles> userRole = userRolesRepository.findByProjectUuid(projectUuid);
        Optional<Role> roleToSearch = roleRepository.findByRoleName("recruiter");
        for (UserRoles roles : userRole) {
            UUID userUuid = roles.getUserUuid();
            if(roles.getRoles().equals(roleToSearch)){
                Optional<User> newUser = userRepository.findByUuid(userUuid);
            }
        }
    }

}
