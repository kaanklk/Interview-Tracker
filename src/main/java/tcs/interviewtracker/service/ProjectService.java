package tcs.interviewtracker.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import tcs.interviewtracker.persistence.Candidate;
import tcs.interviewtracker.persistence.CandidateStatus;
import tcs.interviewtracker.persistence.Interview;
import tcs.interviewtracker.persistence.Position;
import tcs.interviewtracker.persistence.Project;
import tcs.interviewtracker.persistence.Role;
import tcs.interviewtracker.persistence.User;
import tcs.interviewtracker.persistence.UserRoles;
import tcs.interviewtracker.repository.CandidateRepository;
import tcs.interviewtracker.repository.InterviewRepository;
import tcs.interviewtracker.repository.PositionRepository;
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
    private PositionRepository positionRepository;
    private InterviewRepository interviewRepository;
    private CandidateRepository candidateRepository;

    public Project getByUuid(UUID uuid) {
        return projectRepository.findByUuid(uuid).get();
    }

    public Project getById(Long id) {
        return projectRepository.getReferenceById(id);
    }

    public Project updateProject(Project project, Project projectDetails) {

        project.setName(projectDetails.getName());
        project.setDescription(projectDetails.getDescription());

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
        Project savedProject = Project.builder().uuid(newUuid).name(project.getName())
                .description(project.getDescription())
                .build();
        return projectRepository.save(savedProject);
    }

    public void deleteProject(Project project) {
        projectRepository.delete(project);
    }

    public List<Position> fetchProjectPositions(UUID uuid) {
        Project project = projectRepository.findByUuid(uuid).get();
        List<Position> positions = positionRepository.findAll();
        List<Position> projectPostions = new ArrayList<Position>();
        for (Position pos : positions) {
            if (pos.getProject().equals(project)) {
                projectPostions.add(pos);
            }
        }
        return projectPostions;
    }

    public int fetchProjectPositionsCount(UUID uuid) {
        Project project = projectRepository.findByUuid(uuid).get();
        List<Position> positions = positionRepository.findAll();
        List<Position> projectPostions = new ArrayList<Position>();
        for (Position pos : positions) {
            if (pos.getProject().equals(project)) {
                projectPostions.add(pos);
            }
        }
        return projectPostions.size();
    }

    public int fetchProjectAssocicateCount(UUID uuid) {
        Project project = projectRepository.findByUuid(uuid).get();
        List<User> users = userRepository.findAll();
        int associateCount = 0;
        for (User usr : users) {
            if (usr.getProjects().contains(project)) {
                associateCount++;
            }
        }
        return associateCount;
    }

    public List<Interview> fetchCompletedInterviews(UUID uuid) {
        Project project = projectRepository.findByUuid(uuid).get();
        List<Interview> interviews = interviewRepository.findAll();
        List<Interview> completedInterviews = new ArrayList<Interview>();
        for (Interview inter : interviews) {
            if (inter.getProjectId() == project.getId()) {
                if (inter.getIsCompleted() == true) {
                    completedInterviews.add(inter);
                }
            }
        }
        return completedInterviews;
    }

    public List<Interview> fetchIncompletedInterviews(UUID uuid) {
        Project project = projectRepository.findByUuid(uuid).get();
        List<Interview> interviews = interviewRepository.findAll();
        List<Interview> completedInterviews = new ArrayList<Interview>();
        for (Interview inter : interviews) {
            if (inter.getProjectId() == project.getId()) {
                if (inter.getIsCompleted() == false) {
                    completedInterviews.add(inter);
                }
            }
        }
        return completedInterviews;
    }

    public List<Candidate> fetchPendingCandidates(UUID uuid) {
        Project project = projectRepository.findByUuid(uuid).get();
        List<Candidate> candidates = candidateRepository.findAll();
        List<Candidate> pendingCandidates = new ArrayList<Candidate>();
        for (Candidate c : candidates) {
            if (c.getProject().getId() == project.getId()) {
                if (c.getStatus() != CandidateStatus.REJECTED.toString() || c.getStatus() != CandidateStatus.OFFER_ACCEPTED.toString()) {
                    pendingCandidates.add(c);
                }
            }

        }
        return pendingCandidates;
    }

    public List<Candidate> fetchAcceptedCandidates(UUID uuid) {
        Project project = projectRepository.findByUuid(uuid).get();
        List<Candidate> candidates = candidateRepository.findAll();
        List<Candidate> pendingCandidates = new ArrayList<Candidate>();
        for (Candidate c : candidates) {
            if (c.getProject().getId() == project.getId()) {
                if (c.getStatus() == CandidateStatus.OFFER_ACCEPTED.toString()) {
                    pendingCandidates.add(c);
                }
            }

        }
        return pendingCandidates;
    }

    public List<Candidate> fetchRejectedCandidates(UUID uuid) {
        Project project = projectRepository.findByUuid(uuid).get();
        List<Candidate> candidates = candidateRepository.findAll();
        List<Candidate> pendingCandidates = new ArrayList<Candidate>();
        for (Candidate c : candidates) {
            if (c.getProject().getId() == project.getId()) {
                if (c.getStatus() == CandidateStatus.REJECTED.toString()) {
                    pendingCandidates.add(c);
                }
            }

        }
        return pendingCandidates;
    }

    public Integer fetchTecnicalInterviewCount(UUID uuid) {
        Project project = projectRepository.findByUuid(uuid).get();
        List<Interview> interviews = interviewRepository.findAll();
        List<Interview> technicalInterviews = new ArrayList<Interview>();
        for (Interview inter : interviews) {
            if (inter.getProjectId() == project.getId()) {
                if (inter.getType() == "technical") {
                    technicalInterviews.add(inter);
                }
            }
        }
        return technicalInterviews.size();
    }

    public Integer fetchManagementInterviewCount(UUID uuid) {
        Project project = projectRepository.findByUuid(uuid).get();
        List<Interview> interviews = interviewRepository.findAll();
        List<Interview> technicalInterviews = new ArrayList<Interview>();
        for (Interview inter : interviews) {
            if (inter.getProjectId() == project.getId()) {
                if (inter.getType() == "management") {
                    technicalInterviews.add(inter);
                }
            }
        }
        return technicalInterviews.size();
    }

    public List<Interview> fetchUpcomingTecnicalInterviews(UUID uuid) {
        Project project = projectRepository.findByUuid(uuid).get();
        List<Interview> interviews = interviewRepository.findAll();
        List<Interview> upcomingTechnicalInterviews = new ArrayList<Interview>();
        for (Interview inter : interviews) {
            if (inter.getProjectId() == project.getId() && inter.getIsCompleted() == false) {
                if (inter.getType() == "teehnical") {
                    upcomingTechnicalInterviews.add(inter);
                }
            }
        }
        return upcomingTechnicalInterviews;
    }

    public List<Interview> fetchUpcomingManagementInterviews(UUID uuid) {
        Project project = projectRepository.findByUuid(uuid).get();
        List<Interview> interviews = interviewRepository.findAll();
        List<Interview> upcomingManagementInterviews = new ArrayList<Interview>();
        for (Interview inter : interviews) {
            if (inter.getProjectId() == project.getId() && inter.getIsCompleted() == false) {
                if (inter.getType() == "management") {
                    upcomingManagementInterviews.add(inter);
                }
            }
        }
        return upcomingManagementInterviews;
    }

    public List<User> fetchRecruiters(UUID projectUuid) {
        Project project = projectRepository.findByUuid(projectUuid).get();
        Optional<Role> recruiterRole = roleRepository.findByRoleName("recruiter");
        List<UserRoles> userRoles = userRolesRepository.findByProject(project);
        List<User> recruiters = new ArrayList<User>();
        for (UserRoles uRoles : userRoles) {
            if (uRoles.getRoles().contains(recruiterRole.get())) {
                recruiters.add(uRoles.getUser());
            }
        }
        return recruiters;
    }

    public List<User> fetchSourcers(UUID projectUuid) {
        Project project = projectRepository.findByUuid(projectUuid).get();
        Optional<Role> sourcerRole = roleRepository.findByRoleName("sourcer");
        List<UserRoles> userRoles = userRolesRepository.findByProject(project);
        List<User> sourcers = new ArrayList<User>();
        for (UserRoles uRoles : userRoles) {
            if (uRoles.getRoles().contains(sourcerRole.get())) {
                sourcers.add(uRoles.getUser());
            }
        }
        return sourcers;
    }

    public User fetchProjectManager(UUID projectUuid) {
        Project project = projectRepository.findByUuid(projectUuid).get();
        Optional<Role> projectManagerRole = roleRepository.findByRoleName("project_manager");
        List<UserRoles> userRoles = userRolesRepository.findByProject(project);
        User projectManager = null;
        for (UserRoles uRoles : userRoles) {
            if (uRoles.getRoles().contains(projectManagerRole.get())) {
                projectManager = uRoles.getUser();
            }
        }
        return projectManager;
    }

    public Set<User> fetchInterviewers(UUID projectUuid) {
        Project project = projectRepository.findByUuid(projectUuid).get();
        List<Interview> interviewToCheckInterviewers = interviewRepository.findByProjectId(project.getId());
        Set<User> projectInterviewers = new HashSet<User>();
        for (Interview inter : interviewToCheckInterviewers) {
            User interviewerOne = userRepository.getReferenceById(inter.getInterviewerOneId());
            User interviewerTwo = userRepository.getReferenceById(inter.getInterviewerTwoId());
            projectInterviewers.add(interviewerOne);
            projectInterviewers.add(interviewerTwo);
        }
        return projectInterviewers;
    }
}
