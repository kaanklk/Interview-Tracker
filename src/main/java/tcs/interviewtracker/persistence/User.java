package tcs.interviewtracker.persistence;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
<<<<<<< HEAD
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
=======
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
>>>>>>> projectendpoint
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "middle_name", nullable = true)
    private String middleName;

    @Column(name = "employee_id", nullable = false)
    private String employeeId;

    @Column(name = "photo", nullable = true)
    private String profilePicture;

    @Column(name = "date_of_birth", nullable = false)
    private Date dateOfBirth;

<<<<<<< HEAD

    @ManyToMany(fetch = FetchType.EAGER,
    cascade = {
        CascadeType.MERGE
    })
    @JoinTable(name = "users_roles",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "roles_role_id") })
    private Set<Role> roles = new HashSet<>();
=======
    @Column(nullable = true)
    @OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "users")
    private Set<Role> roles;
>>>>>>> projectendpoint

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST,
            CascadeType.MERGE })
    private Set<Project> projects;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone_number", nullable = false)
    private String phone;

    @Column(name = "created_at")
    @CreationTimestamp
<<<<<<< HEAD
    private Timestamp create;
=======
    private Timestamp created;
>>>>>>> projectendpoint

}
