package tcs.interviewtracker.service;

import java.util.List;
import java.util.Optional;

import tcs.interviewtracker.persistence.User;
import tcs.interviewtracker.repository.UserRepository;

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

    }

    public void deleteUser(Long id){
        userRepo.deleteById(id);
    }

}
