package tcs.interviewtracker.DTOs;

import java.util.List;
import java.util.UUID;

import lombok.Data;


@Data
public class UserRolesDTO {

    private UUID uuid;
    private UUID userUuid;
    private List<RoleDTO> roles;
    private UUID projectUuid;
}
