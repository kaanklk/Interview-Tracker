package tcs.interviewtracker.controller;

import java.util.List;
import java.util.UUID;

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

import tcs.interviewtracker.DTOs.UserRolesDTO;
import tcs.interviewtracker.exceptions.ResourceNotFoundException;
import tcs.interviewtracker.service.UserRolesService;


@RestController
@RequestMapping("/users/{userUuid}/roles")
public class UserRolesController {

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
        return service.getRoleForSpecificProject(userUuid, userRolesUuid);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public UserRolesDTO setRole(@RequestBody UserRolesDTO userRoleDTO){
        return service.saveUserRole(userRoleDTO);
    }

    @PutMapping("/{userRolesUuid}")
    @ResponseStatus(HttpStatus.OK)
    public UserRolesDTO setNewRole(@PathVariable UUID userRolesUuid, @RequestBody UserRolesDTO userRoleDTO){
        return service.setNewRoleforExistingUser(userRolesUuid, userRoleDTO);
    }

    @DeleteMapping("/{userRolesUuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRolesForSpecificProject(@PathVariable UUID userRolesUuid) throws ResourceNotFoundException{
        service.deleteUserRole(userRolesUuid);
    }

}
