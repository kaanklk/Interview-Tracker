package tcs.interviewtracker.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import tcs.interviewtracker.DTOs.UserRolesDTO;
import tcs.interviewtracker.exceptions.ResourceAlreadyExistsException;
import tcs.interviewtracker.exceptions.ResourceNotFoundException;
import tcs.interviewtracker.persistence.Project;
import tcs.interviewtracker.persistence.User;
import tcs.interviewtracker.persistence.UserRoles;
import tcs.interviewtracker.repository.UserRolesRepository;

@Service
public class UserRolesService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private ProjectService projectService;


    private UserRolesRepository userRolesRepo;
    UserRolesService(UserRolesRepository userRolesRepo){
        this.userRolesRepo = userRolesRepo;
    }

    public List<UserRoles> getAllRoles(UUID userUuid) throws ResourceNotFoundException{

        User user = userService.getUserById(userUuid);
        List<UserRoles> userRoles = userRolesRepo.findByUser(user);
        if(userRoles.isEmpty()){
            throw new ResourceNotFoundException("Not assigned or wrong id");
        }
        return userRoles;
    }

    public UserRoles getRoleForSpecificProject(UUID userUuid, UUID projectUuid) throws ResourceNotFoundException{
        User user = userService.getUserById(userUuid);
        Project project = projectService.getByUuid(projectUuid);

        Optional<UserRoles> userRole = userRolesRepo.findByUserAndProject(user, project);

        if(!userRole.isPresent()){
            throw new ResourceNotFoundException("Not assigned or wrong id");
        }

        return userRole.get();
    }

    public UserRoles saveUserRole(UserRoles userRoles) throws ResourceAlreadyExistsException{
        Optional<UserRoles> checkExisting = userRolesRepo.findByUserAndProject(userRoles.getUser(), userRoles.getProject());

        if(checkExisting.isPresent()){
            throw new ResourceAlreadyExistsException("User already assigned for this project, try to modify it");
        }

        return userRolesRepo.save(userRoles);
    }

    public UserRoles setNewRoleforExistingUser(UserRoles userRole) throws ResourceNotFoundException{
        return userRolesRepo.save(userRole);
    }

    public void deleteUserRole(UUID userUuid, UUID projectUuid) throws ResourceNotFoundException{
        UserRoles userRole = getRoleForSpecificProject(userUuid, projectUuid);
        userRolesRepo.deleteById(userRole.getId());
    }

 /*
    public void updateProject(UserRolesDTO userRolesDTO){

        UUID user_id = userRolesDTO.getUser().getUuid();
        UUID project_id = userRolesDTO.getProjectUuid();
        User user = userRepo.findByUuid(user_id).get();

        Project project = projectRepo.findByUuid(project_id);

        List<User> users = project.getProjectAssocicates();
        users.add(user);
        project.setProjectAssocicates(users);

        projectRepo.save(project);

        ///////////////////////////////////////////////////////////////////

        List<RoleDTO> roles = userRolesDTO.getRoles();

     //   UUID project_manager_id = roleRepo.findByRoleName("Project Manager").get().getUuid();
     //   UUID recruiter_id = roleRepo.findByRoleName("Recruiter").get().getUuid();
     //   UUID sourcer_id = roleRepo.findByRoleName("Sourcer").get().getUuid();


        for(RoleDTO role : roles){
            if(role.getUuid().equals(project_manager_id)){

            }else if(role.getUuid().equals(recruiter_id)){

            }else if(role.getUuid().equals(sourcer_id)){

            }
        }


    }

*/



    public UserRolesDTO entityToDto(UserRoles userRoles){
        return modelMapper.map(userRoles, UserRolesDTO.class);
    }

    public UserRoles dtoToEntity(UserRolesDTO userRolesDTO){
        return modelMapper.map(userRolesDTO, UserRoles.class);
    }
}
