package tcs.interviewtracker.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import tcs.interviewtracker.exceptions.ResourceAlreadyExistsException;
import tcs.interviewtracker.exceptions.ResourceNotFoundException;
import tcs.interviewtracker.persistence.User;
import tcs.interviewtracker.repository.UserRepository;

@Service
public class UserService {

    private UserRepository userRepo;

    UserService(UserRepository repo) {
        this.userRepo = repo;
    }

    public Page<User> getAllUsers(Pageable page) {

        Page<User> users = userRepo.findAll(page);

        return users;
    }

    public User getUserById(UUID uuid) throws ResourceNotFoundException {
        Optional<User> user = userRepo.findByUuid(uuid);
        if (!user.isPresent()) {
            throw new ResourceNotFoundException("User not found for provided Id");
        }
        return user.get();
    }

    public User saveUser(User user) throws ResourceAlreadyExistsException {

        UUID uuid = UUID.randomUUID();
        user.setUuid(uuid);

        Optional<User> existingUser = userRepo.findByEmail(user.getEmail());

        if (!existingUser.isEmpty()) {
            throw new ResourceAlreadyExistsException("User registered for provided email");
        }

        return userRepo.save(user);
    }

    public User updateUser(UUID uuid, User user) throws ResourceNotFoundException {

        Optional<User> userToUpdate = userRepo.findByUuid(uuid);

        if (!userToUpdate.isPresent()) {
            throw new ResourceNotFoundException("User not found for provided Id");
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

    public void deleteUser(UUID id) throws ResourceNotFoundException {
        Optional<User> user = userRepo.findByUuid(id);
        if (!user.isPresent()) {
            throw new ResourceNotFoundException("User not found for provided Id");
        }
        userRepo.delete(user.get());
    }


    // public User getUserWithSpesificRole(Long projectId, String roleName) {
    // User foundedUser = null;
    // List<User> users = userRepo.findAll();
    // Project project = projectRepo.findById(projectId).get();
    // Role role = roleRepo.findByRoleName(roleName);
    // for (User user : users) {
    // Set<Project> projects = user.getProjects();
    // Set<Role> roles = user.getRoles();
    // if (projects.contains(project) && roles.contains(role)) {
    // foundedUser = user;
    // }
    // }
    // if (foundedUser != null) {
    // return foundedUser;
    // } else {
    // return null;
    // }
    // }

}
