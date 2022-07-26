package tcs.interviewtracker.service;

import java.util.List;
import java.util.Optional;

import tcs.interviewtracker.persistence.Role;
import tcs.interviewtracker.repository.RoleRepository;

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

    public void saveUser(Role role){
        roleRepo.save(role);
    }
}
