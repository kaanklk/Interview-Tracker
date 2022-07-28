package tcs.interviewtracker.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import tcs.interviewtracker.persistence.Project;
import tcs.interviewtracker.persistence.Role;
import tcs.interviewtracker.persistence.User;
import tcs.interviewtracker.repository.ProjectRepository;
import tcs.interviewtracker.repository.RoleRepository;
import tcs.interviewtracker.repository.UserRepository;

@Service
public class UserService {

    private UserRepository userRepo;
    private ProjectRepository projectRepo;
    private RoleRepository roleRepo;

    UserService(UserRepository repo) {
        this.userRepo = repo;
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public User getUserById(Long id) {
        Optional<User> user = userRepo.findById(id);
        return user.get();
    }

    public void saveUser(User user) {
        userRepo.save(user);
    }

    public void updateUser(Long id, User user) {

        User userToUpdate = userRepo.findById(id).get();
        userToUpdate.setFirstName(user.getFirstName());
        userToUpdate.setLastName(user.getLastName());
        userToUpdate.setMiddleName(user.getMiddleName());
        userToUpdate.setEmployeeId(user.getEmployeeId());
        userToUpdate.setProfilePicture(user.getProfilePicture());
        userToUpdate.setEmail(user.getEmail());
        userToUpdate.setDateOfBirth(user.getDateOfBirth());
        userToUpdate.setPhone(user.getPhone());
        userToUpdate.setRoles(user.getRoles());

        userRepo.save(userToUpdate);
    }

    public void deleteUser(Long id) {
        User user = userRepo.findById(id).get();
        userRepo.delete(user);
    }

    public User getUserWithSpesificRole(Long projectId, String roleName) {
        User foundedUser = null;
        List<User> users = userRepo.findAll();
        Project project = projectRepo.findById(projectId).get();
        Role role = roleRepo.findByRoleName(roleName);
        for (User user : users) {
            Set<Project> projects = user.getProjects();
            Set<Role> roles = user.getRoles();
            if (projects.contains(project) && roles.contains(role)) {
                foundedUser = user;
            }
        }
        if (foundedUser != null) {
            return foundedUser;
        } else {
            return null;
        }
    }

}
