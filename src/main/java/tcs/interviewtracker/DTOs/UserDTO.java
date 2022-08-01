package tcs.interviewtracker.DTOs;

import java.sql.Date;
import java.util.UUID;

import lombok.Data;

@Data
public class UserDTO {

    private UUID uuid;
    private String firstName;
    private String lastName;
    private String middleName;
    private String employeeId;
    private String profilePicture;
    private Date dateOfBirth;
    private String email;
    private String phone;
    private Boolean admin;
}
