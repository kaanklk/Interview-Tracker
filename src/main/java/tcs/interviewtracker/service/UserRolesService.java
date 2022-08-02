package tcs.interviewtracker.service;


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
import tcs.interviewtracker.persistence.Project;
import tcs.interviewtracker.persistence.User;
import tcs.interviewtracker.persistence.UserRoles;
import tcs.interviewtracker.repository.ProjectRepository;
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
    @Autowired
    private ProjectRepository projectRepo;


    private UserRolesRepository userRolesRepo;
    UserRolesService(UserRolesRepository userRolesRepo){
        this.userRolesRepo = userRolesRepo;
    }

    public List<UserRolesDTO> getAllRoles(UUID userUuid) throws ResourceNotFoundException{
        List<UserRolesDTO> userRolesDTO = Arrays.asList(modelMapper.map(userRolesRepo.findByUserUuid(userUuid), UserRolesDTO[].class));
        return userRolesDTO;
    }

    public UserRoles getRoleForSpecificProject(UUID userUuid, UUID userRolesUuid) throws ResourceNotFoundException{
        Optional<UserRoles> userRole = userRolesRepo.findByUuid(userRolesUuid);

        if(!userRole.isPresent()){
            throw new ResourceNotFoundException();
        }

        return userRole.get();
    }

    public UserRoles saveUserRole(UserRoles userRoles){
        UUID uuid = UUID.randomUUID();
        userRoles.setUuid(uuid);

        return userRolesRepo.save(userRoles);
    }

    public UserRoles setNewRoleforExistingUser(UUID uuid, UserRoles userRole) throws ResourceNotFoundException{
        Optional<UserRoles> userRoleToUpdate = userRolesRepo.findByUuid(uuid);

        if(!userRoleToUpdate.isPresent()){
            throw new ResourceNotFoundException();
        }

        return userRolesRepo.save(userRole);
    }

    public void deleteUserRole(UUID uuid) throws ResourceNotFoundException{
        Optional<UserRoles> userRole = userRolesRepo.findByUuid(uuid);

        if(!userRole.isPresent()){
            throw new ResourceNotFoundException();
        }

        userRolesRepo.deleteById(userRole.get().getId());
    }


    public void updateProject(UserRolesDTO userRolesDTO){

        UUID user_id = userRolesDTO.getUserUuid();
        UUID project_id = userRolesDTO.getProjectUuid();
        User user = userRepo.findByUuid(user_id).get();

        Project project = projectRepo.findByUuid(project_id);

        List<User> users = project.getProjectAssocicates();
        users.add(user);
        project.setProjectAssocicates(users);

        projectRepo.save(project);

        ///////////////////////////////////////////////////////////////////

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
