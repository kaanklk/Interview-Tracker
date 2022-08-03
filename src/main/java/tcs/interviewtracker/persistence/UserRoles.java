package tcs.interviewtracker.persistence;

import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "user_roles")
@AllArgsConstructor
@NoArgsConstructor
public class UserRoles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "uuid")
    private UUID uuid;

    @Column(name = "user_id")
    private UUID userUuid;

    @ManyToMany
    @JoinTable(name = "user_roles_role", joinColumns = {
            @JoinColumn(name = "user_roles_id", referencedColumnName = "id") }, inverseJoinColumns = {
                    @JoinColumn(name = "role_id", referencedColumnName = "id") })
    private List<Role> roles;

    @Column(name = "project_id")
    private UUID projectUuid;
}
