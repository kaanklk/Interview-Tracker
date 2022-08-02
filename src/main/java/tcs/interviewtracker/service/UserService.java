package tcs.interviewtracker.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tcs.interviewtracker.DTOs.UserDTO;
import tcs.interviewtracker.exceptions.ResourceAlreadyExistsException;
import tcs.interviewtracker.exceptions.ResourceNotFoundException;
import tcs.interviewtracker.persistence.User;
import tcs.interviewtracker.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private ModelMapper modelMapper;
    private UserRepository userRepo;

    UserService(UserRepository repo) {
        this.userRepo = repo;
    }

    public List<UserDTO> getAllUsers() {

        List<UserDTO> userDTO = Arrays.asList(modelMapper.map(userRepo.findAll(), UserDTO[].class));
        return userDTO;
    }

    public User getUserById(UUID uuid) throws ResourceNotFoundException {
        Optional<User> user = userRepo.findByUuid(uuid);
        if (!user.isPresent()) {
            throw new ResourceNotFoundException("User not found for provided Id");
        }
        return user.get();
    }


    public UserDTO saveUser(UserDTO userDTO) throws ResourceAlreadyExistsException {



        UUID uuid = UUID.randomUUID();
        userDTO.setUuid(uuid);

        User user = dtoToEntity(userDTO);

        Optional<User> existingUser = userRepo.findByEmail(user.getEmail());

        if(!existingUser.isEmpty()){
            throw new ResourceAlreadyExistsException("User registered for provided email");
        }

        return entityToDto(userRepo.save(user));
    }

    public UserDTO updateUser(UUID uuid, UserDTO userDTO) throws ResourceNotFoundException {

        Optional<User> userToUpdate = userRepo.findByUuid(uuid);

        if (!userToUpdate.isPresent()) {
            throw new ResourceNotFoundException("User not found for provided Id");
        }

        User updateUser = userToUpdate.get();
        User user = dtoToEntity(userDTO);

        updateUser.setFirstName(user.getFirstName());
        updateUser.setLastName(user.getLastName());
        updateUser.setMiddleName(user.getMiddleName());
        updateUser.setEmployeeId(user.getEmployeeId());
        updateUser.setProfilePicture(user.getProfilePicture());
        updateUser.setEmail(user.getEmail());
        updateUser.setDateOfBirth(user.getDateOfBirth());
        updateUser.setPhone(user.getPhone());
        updateUser.setAdmin(user.getAdmin());

        return entityToDto(userRepo.save(updateUser));
    }

    public void deleteUser(UUID id) throws ResourceNotFoundException{
        Optional<User> user = userRepo.findByUuid(id);
        if (!user.isPresent()) {
            throw new ResourceNotFoundException("User not found for provided Id");
        }
        userRepo.delete(user.get());
    }

    public User dtoToEntity(UserDTO userDTO){
         return modelMapper.map(userDTO, User.class);
    }

    public UserDTO entityToDto(User user){
        return modelMapper.map(user, UserDTO.class);
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
