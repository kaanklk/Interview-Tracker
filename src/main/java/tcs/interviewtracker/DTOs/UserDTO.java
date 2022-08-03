package tcs.interviewtracker.DTOs;



import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class UserDTO {

    private UUID uuid;
    private String firstName;
    private String lastName;
    private String middleName;
    private String employeeId;
    private String profilePicture;
    @JsonFormat(pattern = "EEE MMM dd yyyy HH:mm:ss")
    private Date dateOfBirth;
    private String email;
    private String phone;
    private Boolean admin;
}
