package tcs.interviewtracker.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tcs.interviewtracker.DTOs.RoleDTO;
import tcs.interviewtracker.exceptions.ResourceAlreadyExistsException;
import tcs.interviewtracker.exceptions.ResourceNotFoundException;
import tcs.interviewtracker.persistence.Role;
import tcs.interviewtracker.repository.RoleRepository;

@Service
public class RoleService {

    @Autowired
    private ModelMapper modelMapper;
    private RoleRepository roleRepo;

    RoleService(RoleRepository repo){
       this.roleRepo = repo;
    }

    public List<RoleDTO> getAllRoles(){

        List<RoleDTO> roleDTO = Arrays.asList(modelMapper.map(roleRepo.findAll(), RoleDTO[].class));
        return roleDTO;
    }

    public RoleDTO getRoleById(UUID uuid) throws ResourceNotFoundException{
        Optional<Role> role = roleRepo.findByUuid(uuid);
        if(!role.isPresent()){
            throw new ResourceNotFoundException("Role name not found");
        }
        return entityToDto(role.get());
    }

    public RoleDTO saveRole(RoleDTO roleDTO) throws ResourceAlreadyExistsException{

        Optional<Role> existingRole = roleRepo.findByRoleName(roleDTO.getRoleName());

        if(!existingRole.isEmpty()){
            throw new ResourceAlreadyExistsException("Role name already exists");
        }

        UUID uuid = UUID.randomUUID();
        roleDTO.setUuid(uuid);

        Role role = dtoToEntity(roleDTO);
        return entityToDto(roleRepo.save(role));
    }

    public RoleDTO updateRole(UUID uuid, RoleDTO roleDTO) throws ResourceNotFoundException{
        Optional<Role> roleToUpdate = roleRepo.findByUuid(uuid);
        if(!roleToUpdate.isPresent()){
           throw new ResourceNotFoundException("Role name not found");
        }

        Role updateRole = roleToUpdate.get();
        Role role = dtoToEntity(roleDTO);
        updateRole.setRoleName(role.getRoleName());

        return entityToDto(roleRepo.save(updateRole));
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

    public Role dtoToEntity(RoleDTO roleDTO){
        return modelMapper.map(roleDTO, Role.class);
   }

   public RoleDTO entityToDto(Role role){
       return modelMapper.map(role, RoleDTO.class);
   }
}
