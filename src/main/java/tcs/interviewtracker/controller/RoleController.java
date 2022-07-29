package tcs.interviewtracker.controller;

import java.util.List;

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

import tcs.interviewtracker.exceptions.ResourceNotFoundException;
import tcs.interviewtracker.persistence.Role;
import tcs.interviewtracker.service.RoleService;

@RestController
@RequestMapping("/roles")
public class RoleController {

    private RoleService service;

    RoleController(RoleService service){
        this.service = service;
    }

    @GetMapping("/")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<Role> getAllRoles(){
        return service.getAllRoles();
    }

    @GetMapping("/{id}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Role getRoleById(@PathVariable Long id) throws ResourceNotFoundException{
        return service.getRoleById(id);
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Role save(@RequestBody Role role){
        return service.saveRole(role);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Role updateRoleById(@PathVariable Long id, @RequestBody Role role) throws ResourceNotFoundException{
        return service.updateRole(id,role);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRoleById(@PathVariable Long id) throws ResourceNotFoundException{
        service.deleteRole(id);
    }
}
