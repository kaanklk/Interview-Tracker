package tcs.interviewtracker.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import tcs.interviewtracker.exceptions.ResourceNotFoundException;
import tcs.interviewtracker.persistence.Project;
import tcs.interviewtracker.persistence.Role;
import tcs.interviewtracker.persistence.User;
import tcs.interviewtracker.persistence.UserRoles;
import tcs.interviewtracker.repository.ProjectRepository;
import tcs.interviewtracker.repository.RoleRepository;
import tcs.interviewtracker.repository.UserRepository;

@Service
public class UserService {

    private UserRepository userRepo;

    UserService(UserRepository repo) {
        this.userRepo = repo;
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public User getUserById(Long id) throws ResourceNotFoundException {
        Optional<User> user = userRepo.findById(id);
        if(!user.isPresent()){
           throw new ResourceNotFoundException();
        }
        return user.get();
    }

    public User saveUser(User user) {
        return userRepo.save(user);
    }

    public User updateUser(Long id, User user) throws ResourceNotFoundException {

        Optional<User> userToUpdate = userRepo.findById(id);

        if(!userToUpdate.isPresent()){
           throw new ResourceNotFoundException();
        }

        User updateUser = userToUpdate.get();

        updateUser.setFirstName(user.getFirstName());
        updateUser.setLastName(user.getLastName());
        updateUser.setMiddleName(user.getMiddleName());
        updateUser.setEmployeeId(user.getEmployeeId());
        updateUser.setProfilePicture(user.getProfilePicture());
        updateUser.setEmail(user.getEmail());
        updateUser.setDateOfBirth(user.getDateOfBirth());
        updateUser.setPhone(user.getPhone());
        updateUser.setAdmin(user.getAdmin());

        return userRepo.save(updateUser);
    }

    public void deleteUser(Long id) throws ResourceNotFoundException {
        Optional<User> user = userRepo.findById(id);
        if(!user.isPresent()){
            throw new ResourceNotFoundException();
        }
        userRepo.deleteById(id);
    }


    // public User getUserWithSpesificRole(Long projectId, String roleName) {
    //     User foundedUser = null;
    //     List<User> users = userRepo.findAll();
    //     Project project = projectRepo.findById(projectId).get();
    //     Role role = roleRepo.findByRoleName(roleName);
    //     for (User user : users) {
    //         Set<Project> projects = user.getProjects();
    //         Set<Role> roles = user.getRoles();
    //         if (projects.contains(project) && roles.contains(role)) {
    //             foundedUser = user;
    //         }
    //     }
    //     if (foundedUser != null) {
    //         return foundedUser;
    //     } else {
    //         return null;
    //     }
    // }

}
