package tcs.interviewtracker.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import tcs.interviewtracker.persistence.User;
import tcs.interviewtracker.repository.UserRepository;

@Service
public class UserService {


    private UserRepository userRepo;

    UserService(UserRepository repo){
       this.userRepo = repo;
    }

    public List<User> getAllUsers(){
       return userRepo.findAll();
    }

    public User getUserById(Long id){
        Optional<User> user = userRepo.findById(id);
        return user.get();
    }

    public void saveUser(User user){
        userRepo.save(user);
    }

    public void updateUser(Long id, User user){

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

    public void deleteUser(Long id){
       User user = userRepo.findById(id).get();
       userRepo.delete(user);
    }

}
