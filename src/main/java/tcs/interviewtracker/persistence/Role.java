package tcs.interviewtracker.persistence;


import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class Role {

    @Id
<<<<<<< HEAD
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
=======
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
>>>>>>> projectendpoint
    private Long id;

    @Column(name = "role_name", nullable = false)
    private String roleName;

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private Set<User> users = new HashSet<>();

}
