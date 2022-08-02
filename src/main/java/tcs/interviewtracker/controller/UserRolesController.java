package tcs.interviewtracker.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import tcs.interviewtracker.DTOs.RoleDTO;
import tcs.interviewtracker.DTOs.UserRolesDTO;
import tcs.interviewtracker.exceptions.ResourceNotFoundException;
import tcs.interviewtracker.persistence.Role;
import tcs.interviewtracker.persistence.UserRoles;
import tcs.interviewtracker.repository.RoleRepository;
import tcs.interviewtracker.service.UserRolesService;


@RestController
@RequestMapping("/users/{userUuid}/roles")
public class UserRolesController {

    @Autowired
    private RoleRepository roleRepo;
    @Autowired
    private ModelMapper modelMapper;
    private UserRolesService service;

    UserRolesController(UserRolesService service){
         this.service = service;
    }

    @GetMapping("")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<UserRolesDTO> getUserRoles(@PathVariable UUID userUuid) throws ResourceNotFoundException{
        return service.getAllRoles(userUuid);
    }

    @GetMapping("/{userRolesUuid}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public UserRolesDTO getRoleForSpecificProject(@PathVariable UUID userUuid, @PathVariable UUID userRolesUuid) throws ResourceNotFoundException{
        return entityToDto(service.getRoleForSpecificProject(userUuid, userRolesUuid));
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public UserRolesDTO setRole(@RequestBody UserRolesDTO userRoleDTO){

        UserRoles userRoles = new UserRoles();

        userRoles.setUserUuid(userRoleDTO.getUserUuid());
        userRoles.setProjectUuid(userRoleDTO.getProjectUuid());

        List<Role> roles = new ArrayList<>();

        for(RoleDTO r : userRoleDTO.getRoles()){
            roles.add(roleRepo.findByUuid(r.getUuid()).get());
        }
        userRoles.setRoles(roles);

        UserRoles created = service.saveUserRole(userRoles);

        List<RoleDTO> roleCreated = Arrays.asList(modelMapper.map(created.getRoles(), RoleDTO[].class));

        UserRolesDTO createdDTO = new UserRolesDTO();
        createdDTO.setUuid(created.getUuid());
        createdDTO.setUserUuid(created.getUserUuid());
        createdDTO.setProjectUuid(created.getProjectUuid());
        createdDTO.setRoles(roleCreated);

        return createdDTO;
    }

    @PutMapping("/{userRolesUuid}")
    @ResponseStatus(HttpStatus.OK)
    public UserRolesDTO setNewRole(@PathVariable UUID userRolesUuid, @RequestBody UserRolesDTO userRoleDTO) throws ResourceNotFoundException{

        UserRoles userRole = service.getRoleForSpecificProject(userRoleDTO.getUserUuid(), userRolesUuid);

        List<Role> roles = new ArrayList<>();

        for(RoleDTO r : userRoleDTO.getRoles()){
           roles.add(roleRepo.findByUuid(r.getUuid()).get());
        }

        userRole.setRoles(roles);
        UserRoles updated = service.setNewRoleforExistingUser(userRolesUuid, userRole);

        userRoleDTO.setUuid(updated.getUuid());
        return userRoleDTO;
    }

    @DeleteMapping("/{userRolesUuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRolesForSpecificProject(@PathVariable UUID userRolesUuid) throws ResourceNotFoundException{
        service.deleteUserRole(userRolesUuid);
    }

    public UserRolesDTO entityToDto(UserRoles userRoles){
        return modelMapper.map(userRoles, UserRolesDTO.class);
    }

    public UserRoles dtoToEntity(UserRolesDTO userRolesDTO){
        return modelMapper.map(userRolesDTO, UserRoles.class);
    }

}
