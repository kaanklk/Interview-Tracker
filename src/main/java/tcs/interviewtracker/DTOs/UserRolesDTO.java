package tcs.interviewtracker.DTOs;

import java.util.List;
import java.util.UUID;

import lombok.Data;
import tcs.interviewtracker.persistence.Role;

@Data
public class UserRolesDTO {

    private UUID uuid;
    private UUID userUUID;
    private List<Role> roles;
    private UUID projectUUID;
}
