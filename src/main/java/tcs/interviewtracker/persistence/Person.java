package tcs.interviewtracker.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long id;

    @Column(name = "uuid")
    @Nullable
    private UUID uuid;

    @Column(name = "fname", columnDefinition = "varchar(255)")
    @NonNull
    private String firstName;

    @Column(name = "mname", columnDefinition = "varchar(255)")
    @Nullable
    private String middleName;

    @Column(name = "lname", columnDefinition = "varchar(255)")
    @NonNull
    private String lastName;

    @Column(name = "email", columnDefinition = "varchar(255)")
    @NonNull
    private String email;

    @Column(name = "phone", columnDefinition = "varchar(255)")
    @Nullable
    private String phone;

    @Column(name = "date_of_birth")
    @Nullable
    private Date dateOfBirth;

    @Column(name = "profile_picture_url", columnDefinition = "varchar(255)")
    @Nullable
    private String profilePictureUrl;

}
