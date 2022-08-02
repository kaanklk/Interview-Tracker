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

import tcs.interviewtracker.DTOs.RoleDTO;
import tcs.interviewtracker.exceptions.ResourceAlreadyExistsException;
import tcs.interviewtracker.exceptions.ResourceNotFoundException;
import tcs.interviewtracker.service.RoleService;

@RestController
@RequestMapping("/roles")
public class RoleController {

    private RoleService service;

    RoleController(RoleService service){
        this.service = service;
    }

    @GetMapping("")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<RoleDTO> getAllRoles(){
        return service.getAllRoles();
    }

    @GetMapping("/{uuid}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public RoleDTO getRoleById(@PathVariable UUID uuid) throws ResourceNotFoundException{
        return service.getRoleById(uuid);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public RoleDTO save(@RequestBody RoleDTO roleDTO) throws ResourceAlreadyExistsException{
        return service.saveRole(roleDTO);
    }

    @PutMapping("/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public RoleDTO updateRoleById(@PathVariable UUID uuid, @RequestBody RoleDTO role) throws ResourceNotFoundException{
        return service.updateRole(uuid,role);
    }

    @DeleteMapping("/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRoleById(@PathVariable UUID uuid) throws ResourceNotFoundException{
        service.deleteRole(uuid);
    }
}
