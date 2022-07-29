package tcs.interviewtracker.service;

import java.util.List;
import java.util.Optional;


import org.springframework.stereotype.Service;

import tcs.interviewtracker.exceptions.ResourceNotFoundException;
import tcs.interviewtracker.persistence.UserRoles;
import tcs.interviewtracker.repository.UserRolesRepository;

@Service
public class UserRolesService {


    private UserRolesRepository userRolesRepo;

    UserRolesService(UserRolesRepository userRolesRepo){
        this.userRolesRepo = userRolesRepo;
    }

    public List<UserRoles> getAllRoles(Long id) throws ResourceNotFoundException{
        List<UserRoles> userRoles = userRolesRepo.findByUserId(id);
        return userRoles;
    }

    public UserRoles getRoleForSpecificProject(Long userId, Long userRolesId) throws ResourceNotFoundException{
        Optional<UserRoles> userRole = userRolesRepo.findById(userRolesId);

        return userRole.get();
    }

    public UserRoles saveUserRole(UserRoles userRole){
        return userRolesRepo.save(userRole);
    }

    public UserRoles setNewRoleforExistingUser(Long id, UserRoles userRole){
        Optional<UserRoles> userRoleToUpdate = userRolesRepo.findById(id);

        UserRoles updated = userRoleToUpdate.get();
        updated.setUserId(userRole.getUserId());
        updated.setRoles(userRole.getRoles());
        updated.setProjectId(userRole.getProjectId());

        return updated;
    }

    public void deleteUserRole(Long id){
        userRolesRepo.deleteById(id);
    }
}
