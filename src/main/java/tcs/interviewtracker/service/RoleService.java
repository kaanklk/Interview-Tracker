package tcs.interviewtracker.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

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

    public Role getRoleById(Long id){
        Optional<Role> role = roleRepo.findById(id);
        return role.get();
    }

    public void saveRole(Role role){
        roleRepo.save(role);
    }

    public void updateRole(Long id, Role user){
        Role roleToUpdate = roleRepo.findById(id).get();
        roleToUpdate.setRoleName(user.getRoleName());
        roleRepo.save(roleToUpdate);
    }

    public void deleteRole(Long id){
       Role role = roleRepo.findById(id).get();
       roleRepo.delete(role);
    }
}
