package tcs.interviewtracker.controller;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
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

import tcs.interviewtracker.DTOs.RoleDTO;
import tcs.interviewtracker.exceptions.ResourceAlreadyExistsException;
import tcs.interviewtracker.exceptions.ResourceNotFoundException;
import tcs.interviewtracker.persistence.Role;
import tcs.interviewtracker.service.RoleService;

@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private ModelMapper modelMapper;
    private RoleService service;

    RoleController(RoleService service){
        this.service = service;
    }

    @GetMapping("")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<RoleDTO> getAllRoles(
        @RequestParam(required = false, defaultValue = "10") Integer pagesize,
        @RequestParam(required = false, defaultValue = "0") Integer offset,
        @RequestParam(required = false, defaultValue = "id") String orderBy,
        @RequestParam(required = false, defaultValue = "ascending") String orderDirection){

        Pageable page;

        if (orderDirection.equals("ascending"))
            page = PageRequest.of(offset, pagesize, Sort.by(orderBy).ascending());
        else
            page = PageRequest.of(offset, pagesize, Sort.by(orderBy).descending());


        var rolesDTO = service.getAllRoles(page).stream()
        .map(this::entityToDto)
        .collect(Collectors.toList());

        return rolesDTO;
    }

    @GetMapping("/{uuid}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public RoleDTO getRoleById(@PathVariable UUID uuid) throws ResourceNotFoundException{
        Role role = service.getRoleById(uuid);
        return entityToDto(role);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public RoleDTO save(@RequestBody RoleDTO roleDTO) throws ResourceAlreadyExistsException{
        Role role = dtoToEntity(roleDTO);
        return entityToDto(service.saveRole(role));
    }

    @PutMapping("/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public RoleDTO updateRoleById(@PathVariable UUID uuid, @RequestBody RoleDTO roleDTO) throws ResourceNotFoundException{
        Role role = dtoToEntity(roleDTO);
        return entityToDto(service.updateRole(uuid,role));
    }

    @DeleteMapping("/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRoleById(@PathVariable UUID uuid) throws ResourceNotFoundException{
        service.deleteRole(uuid);
    }

    public Role dtoToEntity(RoleDTO roleDTO){
        return modelMapper.map(roleDTO, Role.class);
    }

    public RoleDTO entityToDto(Role role){
        return modelMapper.map(role, RoleDTO.class);
    }
}
