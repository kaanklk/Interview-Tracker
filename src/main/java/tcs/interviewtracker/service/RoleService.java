package tcs.interviewtracker.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import tcs.interviewtracker.exceptions.ResourceNotFoundException;
import tcs.interviewtracker.persistence.Role;
import tcs.interviewtracker.repository.RoleRepository;

@Service
public class RoleService {

    private RoleRepository roleRepo;

    RoleService(RoleRepository repo){
       this.roleRepo = repo;
    }

    public List<Role> getAllRoles(){
       return roleRepo.findAll();
    }

    public Role getRoleById(Long id) throws ResourceNotFoundException{
        Optional<Role> role = roleRepo.findById(id);
        if(!role.isPresent()){
            throw new ResourceNotFoundException();
        }
        return role.get();
    }

    public Role saveRole(Role role){
        return roleRepo.save(role);
    }

    public Role updateRole(Long id, Role user) throws ResourceNotFoundException{
        Optional<Role> roleToUpdate = roleRepo.findById(id);
        if(!roleToUpdate.isPresent()){
           throw new ResourceNotFoundException();
        }

        Role updateRole = roleToUpdate.get();
        updateRole.setRoleName(user.getRoleName());

        return roleRepo.save(updateRole);
    }

    public void deleteRole(Long id) throws ResourceNotFoundException{
        Optional<Role> roleToUpdate = roleRepo.findById(id);
        if(!roleToUpdate.isPresent()){
           throw new ResourceNotFoundException();
        }
       roleRepo.deleteById(id);
    }

    public Role getByName(String roleName){
        return roleRepo.findByRoleName(roleName);
    }
}
