package tcs.interviewtracker.DTOs;

import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public class UserDTO {

    private UUID uuid;
    private String firstName;
    private String lastName;
    private String middleName;
    private String employeeId;
    private String profilePicture;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date dateOfBirth;
    private String email;
    private String phone;
    private Boolean admin;
}
