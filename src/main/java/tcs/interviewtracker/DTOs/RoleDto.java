package tcs.interviewtracker.DTOs;

import java.util.UUID;

import lombok.Data;

@Data
public class RoleDto {

    private UUID uuid;
    private String roleName;
}
