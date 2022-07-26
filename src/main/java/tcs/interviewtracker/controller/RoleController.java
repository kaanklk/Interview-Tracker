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

import tcs.interviewtracker.persistence.Role;
import tcs.interviewtracker.service.RoleService;

@RestController
@RequestMapping("/")
public class RoleController {

    private RoleService service;

    RoleController(RoleService service){
        this.service = service;
    }

    @GetMapping("roles")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<Role> getAllRoles(){
        return service.getAllRoles();
    }

    @GetMapping("roles/{id}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Role getRoleById(@PathVariable Long id){
        return service.getRoleById(id);
    }

    @PostMapping("roles")
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody Role role){
        service.saveRole(role);
    }

    @PutMapping("roles/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateRoleById(@PathVariable Long id, @RequestBody Role role){
        service.updateRole(id,role);
    }

    @DeleteMapping("roles/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRoleById(@PathVariable Long id){
        service.deleteRole(id);
    }
}
