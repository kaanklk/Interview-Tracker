package tcs.interviewtracker.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tcs.interviewtracker.DTOs.RoleDTO;
import tcs.interviewtracker.DTOs.UserRolesDTO;
import tcs.interviewtracker.exceptions.ResourceNotFoundException;
import tcs.interviewtracker.persistence.Role;
import tcs.interviewtracker.persistence.User;
import tcs.interviewtracker.persistence.UserRoles;
import tcs.interviewtracker.repository.RoleRepository;
import tcs.interviewtracker.repository.UserRepository;
import tcs.interviewtracker.repository.UserRolesRepository;

@Service
public class UserRolesService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private RoleRepository roleRepo;
    @Autowired
    private UserRepository userRepo;


    private UserRolesRepository userRolesRepo;
    UserRolesService(UserRolesRepository userRolesRepo){
        this.userRolesRepo = userRolesRepo;
    }

    public List<UserRolesDTO> getAllRoles(UUID userUuid) throws ResourceNotFoundException{
        List<UserRolesDTO> userRolesDTO = Arrays.asList(modelMapper.map(userRolesRepo.findByUserUuid(userUuid), UserRolesDTO[].class));
        return userRolesDTO;
    }

    public UserRolesDTO getRoleForSpecificProject(UUID userUuid, UUID userRolesUuid) throws ResourceNotFoundException{
        Optional<UserRoles> userRole = userRolesRepo.findByUuid(userRolesUuid);

        return entityToDto(userRole.get());
    }

    public UserRolesDTO saveUserRole(UserRolesDTO userRoleDTO){
        UUID uuid = UUID.randomUUID();
        userRoleDTO.setUuid(uuid);

        List<RoleDTO> roles = userRoleDTO.getRoles();

        List<Role> role = new ArrayList<>();

        for(RoleDTO r : roles){
           role.add(roleRepo.findByUuid(r.getUuid()).get());
        }

        UserRoles userRole = new UserRoles();
        userRole.setUuid(userRoleDTO.getUuid());
        userRole.setUserUuid(userRoleDTO.getUserUuid());
        userRole.setRoles(role);
        userRole.setProjectUuid(userRoleDTO.getProjectUuid());


        UserRoles created = userRolesRepo.save(userRole);
        UserRolesDTO createdDTO = new UserRolesDTO();

        List<RoleDTO> roleCreated = Arrays.asList(modelMapper.map(created.getRoles(), RoleDTO[].class));

        createdDTO.setUuid(created.getUuid());
        createdDTO.setUserUuid(created.getUserUuid());
        createdDTO.setRoles(roleCreated);
        createdDTO.setProjectUuid(created.getProjectUuid());

        return createdDTO;
    }

    public UserRolesDTO setNewRoleforExistingUser(UUID uuid, UserRolesDTO userRoleDTO){
        Optional<UserRoles> userRoleToUpdate = userRolesRepo.findByUuid(uuid);

        UserRoles updated = userRoleToUpdate.get();
        UserRoles userRole = dtoToEntity(userRoleDTO);

        updated.setUserUuid(userRole.getUserUuid());
        updated.setRoles(userRole.getRoles());
        updated.setProjectUuid(userRole.getProjectUuid());

        return entityToDto(userRolesRepo.save(updated));
    }

    public void deleteUserRole(UUID uuid) throws ResourceNotFoundException{
        Optional<UserRoles> userRole = userRolesRepo.findByUuid(uuid);

        if(!userRole.isPresent()){
            throw new ResourceNotFoundException();
        }

        userRolesRepo.delete(userRole.get());
    }


    public void updateProject(UserRolesDTO userRolesDTO){

        UUID user_id = userRolesDTO.getUserUuid();
        UUID project_id = userRolesDTO.getProjectUuid();
        User user = userRepo.findByUuid(user_id).get();

        List<RoleDTO> roles = userRolesDTO.getRoles();

        UUID project_manager_id = roleRepo.findByRoleName("Project Manager").get().getUuid();
        UUID recruiter_id = roleRepo.findByRoleName("Recruiter").get().getUuid();
        UUID sourcer_id = roleRepo.findByRoleName("Sourcer").get().getUuid();


        for(RoleDTO role : roles){
            if(role.getUuid().equals(project_manager_id)){

            }else if(role.getUuid().equals(recruiter_id)){

            }else if(role.getUuid().equals(sourcer_id)){

            }
        }


    }





    public UserRolesDTO entityToDto(UserRoles userRoles){
        return modelMapper.map(userRoles, UserRolesDTO.class);
    }

    public UserRoles dtoToEntity(UserRolesDTO userRolesDTO){
        return modelMapper.map(userRolesDTO, UserRoles.class);
    }
}
