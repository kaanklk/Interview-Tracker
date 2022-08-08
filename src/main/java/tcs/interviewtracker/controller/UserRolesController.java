package tcs.interviewtracker.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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

import tcs.interviewtracker.DTOs.ProjectDTO;
import tcs.interviewtracker.DTOs.RoleDTO;
import tcs.interviewtracker.DTOs.UserDTO;
import tcs.interviewtracker.DTOs.UserRolesDTO;
import tcs.interviewtracker.exceptions.ResourceAlreadyExistsException;
import tcs.interviewtracker.exceptions.ResourceNotFoundException;
import tcs.interviewtracker.persistence.Project;
import tcs.interviewtracker.persistence.Role;
import tcs.interviewtracker.persistence.User;
import tcs.interviewtracker.persistence.UserRoles;
import tcs.interviewtracker.service.ProjectService;
import tcs.interviewtracker.service.RoleService;
import tcs.interviewtracker.service.UserRolesService;
import tcs.interviewtracker.service.UserService;


@RestController
@RequestMapping("/users/{userUuid}/roles")
public class UserRolesController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProjectService projectService;
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
        List<UserRolesDTO> userRolesDTO = entityListToDto(service.getAllRoles(userUuid));
        return userRolesDTO;
    }

    @GetMapping("/{projectUuid}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public UserRolesDTO getRoleForSpecificProject(@PathVariable UUID userUuid, @PathVariable UUID projectUuid) throws ResourceNotFoundException{
        return entityToDto(service.getRoleForSpecificProject(userUuid, projectUuid));

    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public UserRolesDTO setRole(@RequestBody UserRolesDTO userRoleDTO) throws ResourceNotFoundException, ResourceAlreadyExistsException{

        UserRoles userRoles = new UserRoles();

        List<Role> roles = new ArrayList<>();
        User user = userService.getUserById(userRoleDTO.getUser().getUuid());
        Project project = projectService.getByUuid(userRoleDTO.getProject().getUuid());

        for(RoleDTO r : userRoleDTO.getRoles()){
            roles.add(roleService.getRoleById(r.getUuid()));
        }

        userRoles.setUser(user);
        userRoles.setProject(project);
        userRoles.setRoles(roles);

        UserRoles created = service.saveUserRole(userRoles);

     //   List<RoleDTO> roleCreated = Arrays.asList(modelMapper.map(created.getRoles(), RoleDTO[].class));

        UserRolesDTO createdDTO = entityToDto(created);
        // createdDTO.setUser(entityToDto(created.getUser()));
        // createdDTO.setProject(entityToDto(created.getProject()));
        // createdDTO.setRoles(roleCreated);

        return createdDTO;
    }

    @PutMapping("/{projectUuid}")
    @ResponseStatus(HttpStatus.OK)
    public UserRolesDTO setNewRole(@PathVariable UUID userUuid, @PathVariable UUID projectUuid, @RequestBody UserRolesDTO userRoleDTO) throws ResourceNotFoundException{

        UserRoles userRole = service.getRoleForSpecificProject(userUuid, projectUuid);

        List<Role> roles = new ArrayList<>();

        for(RoleDTO r : userRoleDTO.getRoles()){
           roles.add(roleService.getRoleById(r.getUuid()));
        }

        userRole.setRoles(roles);
        service.setNewRoleforExistingUser(userRole);

        userRoleDTO.setUser(entityToDto(userRole.getUser()));
        userRoleDTO.setProject(entityToDto(userRole.getProject()));

        return userRoleDTO;
    }

    @DeleteMapping("/{projectUuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRolesForSpecificProject(@PathVariable UUID userUuid, @PathVariable UUID projectUuid) throws ResourceNotFoundException{
        service.deleteUserRole(userUuid, projectUuid);
    }



    ////////////////////Mapping dto to entity methods/////////////////////////

    public UserRolesDTO entityToDto(UserRoles userRoles){

        User user = userRoles.getUser();
        Project project = userRoles.getProject();
        List<Role> roles = userRoles.getRoles();
        List<RoleDTO> rolesDTO = Arrays.asList(modelMapper.map(roles, RoleDTO[].class));

        UserRolesDTO userRolesDTO = new UserRolesDTO();

        userRolesDTO.setUser(entityToDto(user));
        userRolesDTO.setProject(entityToDto(project));
        userRolesDTO.setRoles(rolesDTO);

        return userRolesDTO;
    }


    public UserRoles dtoToEntity(UserRolesDTO userRolesDTO){
        return modelMapper.map(userRolesDTO, UserRoles.class);
    }

    public User dtoToEntity(UserDTO userDTO){
        return modelMapper.map(userDTO, User.class);
    }

    public UserDTO entityToDto(User user){
       return modelMapper.map(user, UserDTO.class);
    }

    public Project dtoToEntity(ProjectDTO projectDTO){
       return modelMapper.map(projectDTO, Project.class);
    }

    public ProjectDTO entityToDto(Project project){

        ProjectDTO projectDTO = ProjectDTO.builder()
                .uuid(project.getUuid())
                .name(project.getName())
                .description(project.getDescription()).build();


        return projectDTO;
    }

    public List<UserRolesDTO> entityListToDto(List<UserRoles> userRoles){

        List<UserRolesDTO> userRolesDTO = new ArrayList<>();

        for(UserRoles ur : userRoles){
            userRolesDTO.add(entityToDto(ur));
        }

        return userRolesDTO;
    }

}
