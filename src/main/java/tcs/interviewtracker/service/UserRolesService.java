package tcs.interviewtracker.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.springframework.stereotype.Service;

import tcs.interviewtracker.exceptions.ResourceNotFoundException;
import tcs.interviewtracker.persistence.Role;
import tcs.interviewtracker.persistence.UserRoles;
import tcs.interviewtracker.repository.UserRolesRepository;

@Service
public class UserRolesService {


    private UserRolesRepository userRolesRepo;
    private RoleService roleService;

    UserRolesService(UserRolesRepository userRolesRepo){
        this.userRolesRepo = userRolesRepo;
    }

    public List<Role> getAllRoles(Long id) throws ResourceNotFoundException{
        List<UserRoles> userRoles = userRolesRepo.findByUserId(id);
        List<Role> roles = new ArrayList<>();

        for(UserRoles userRole : userRoles){
            roles.add(roleService.getRoleById(userRole.getRoleId()));
        }

        return roles;
    }

    public Role getRoleForSpecificProject(Long userId, Long projectId) throws ResourceNotFoundException{
        Optional<UserRoles> userRole = userRolesRepo.findByUserIdAndProjectId(userId, projectId);
        if(userRole.isPresent()){
            throw new ResourceNotFoundException();
        }

        Role role = roleService.getRoleById(userRole.get().getRoleId());

        return role;
    }

    public UserRoles saveUserRole(UserRoles userRole){
        return userRolesRepo.save(userRole);
    }

    public UserRoles setNewRoleforExistingUser(UserRoles userRole){
        Optional<UserRoles> userRoleToUpdate = userRolesRepo.findByUserIdAndProjectId(userRole.getUserId(), userRole.getProjectId());

        UserRoles updated = userRoleToUpdate.get();
        updated.setUserId(userRole.getUserId());
        updated.setRoleId(userRole.getRoleId());
        updated.setProjectId(userRole.getProjectId());

        return updated;
    }
}
