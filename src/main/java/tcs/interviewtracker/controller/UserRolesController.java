package tcs.interviewtracker.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import tcs.interviewtracker.exceptions.ResourceNotFoundException;
import tcs.interviewtracker.persistence.Role;
import tcs.interviewtracker.persistence.UserRoles;
import tcs.interviewtracker.service.UserRolesService;


@RestController
@RequestMapping("/users/{userId}/roles")
public class UserRolesController {

    private UserRolesService service;

    UserRolesController(UserRolesService service){
         this.service = service;
    }

    @GetMapping("/")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<Role> getUserRoles(@PathVariable Long userId) throws ResourceNotFoundException{
        return service.getAllRoles(userId);
    }

    @GetMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Role getRoleForSpecificProject(@PathVariable Long userId, @RequestParam Long projectId) throws ResourceNotFoundException{
        return service.getRoleForSpecificProject(userId, projectId);
    }


    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public UserRoles setRole(@RequestBody UserRoles userRole){
        return service.saveUserRole(userRole);
    }

    @PutMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public UserRoles setNewRole(@RequestBody UserRoles userRole){
        return service.setNewRoleforExistingUser(userRole);
    }

}
