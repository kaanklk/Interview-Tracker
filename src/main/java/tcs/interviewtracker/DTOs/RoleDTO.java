package tcs.interviewtracker.DTOs;

import java.util.UUID;

import lombok.Data;

@Data
public class RoleDTO {

    private UUID uuid;
    private String roleName;
}
