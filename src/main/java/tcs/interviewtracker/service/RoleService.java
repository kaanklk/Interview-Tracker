package tcs.interviewtracker.service;


import java.util.Optional;
import java.util.UUID;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import tcs.interviewtracker.exceptions.ResourceAlreadyExistsException;
import tcs.interviewtracker.exceptions.ResourceNotFoundException;
import tcs.interviewtracker.persistence.Role;
import tcs.interviewtracker.repository.RoleRepository;

@Service
public class RoleService {


    private RoleRepository roleRepo;

    RoleService(RoleRepository repo){
       this.roleRepo = repo;
    }

    public Page<Role> getAllRoles(Pageable page){

        Page<Role> roles = roleRepo.findAll(page);
        return roles;
    }

    public Role getRoleById(UUID uuid) throws ResourceNotFoundException{
        Optional<Role> role = roleRepo.findByUuid(uuid);
        if(!role.isPresent()){
            throw new ResourceNotFoundException("Role name not found");
        }
        return role.get();
    }

    public Role saveRole(Role role) throws ResourceAlreadyExistsException{

        Optional<Role> existingRole = roleRepo.findByRoleName(role.getRoleName());

        if(!existingRole.isEmpty()){
            throw new ResourceAlreadyExistsException("Role name already exists");
        }

        UUID uuid = UUID.randomUUID();
        role.setUuid(uuid);

        return roleRepo.save(role);
    }

    public Role updateRole(UUID uuid, Role role) throws ResourceNotFoundException{
        Optional<Role> roleToUpdate = roleRepo.findByUuid(uuid);
        if(!roleToUpdate.isPresent()){
           throw new ResourceNotFoundException("Role name not found");
        }

        Role updateRole = roleToUpdate.get();

        updateRole.setRoleName(role.getRoleName());

        return roleRepo.save(updateRole);
    }

    public void deleteRole(UUID uuid) throws ResourceNotFoundException{
        Optional<Role> role = roleRepo.findByUuid(uuid);
        if(!role.isPresent()){
           throw new ResourceNotFoundException("Role name not found");
        }
       roleRepo.delete(role.get());
    }

    public Role getByName(String roleName){
        return roleRepo.findByRoleName(roleName).get();
    }
}
