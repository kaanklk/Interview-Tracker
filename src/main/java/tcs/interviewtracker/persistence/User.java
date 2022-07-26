package tcs.interviewtracker.persistence;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="users")
public class User {

    @Id
    @Column(name="user_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="first_name",nullable = false)
    private String firstName;

    @Column(name="last_name",nullable = false)
    private String lastName;

    @Column(name="middle_name",nullable = true)
    private String middleName;

    @Column(name="employee_id",nullable = false)
    private String employeeId;

    @Column(name="photo",nullable = true)
    private String profilePicture;

    @Column(name="date_of_birth",nullable = false)
    private Date dateOfBirth;

    @Column(nullable = true)
    @ManyToMany
    private List<Role> roles;

    @Column(name="email",nullable = false)
    private String email;

    @Column(name="phone_number",nullable = false)
    private String phone;

    @Column(name="created_at")
    @CreationTimestamp
    private Timestamp created;

}
