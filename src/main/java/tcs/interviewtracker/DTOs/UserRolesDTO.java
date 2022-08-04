package tcs.interviewtracker.DTOs;

import java.util.List;

import lombok.Data;


@Data
public class UserRolesDTO {

    private UserDTO user;
    private List<RoleDTO> roles;
    private ProjectDTO project;
}
