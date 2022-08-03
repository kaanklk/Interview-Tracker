package tcs.interviewtracker.persistence;

import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.mapstruct.control.MappingControl.Use;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import tcs.interviewtracker.DTOs.PositionDTO;

@Entity
@Table(name = Project.TBL_NAME)
@Data
@NoArgsConstructor
@SuperBuilder
public class Project {
    protected static final String TBL_NAME = "project";

    protected static final String FLD_NAME = "name";
    protected static final String FLD_PROJECT_MANAGER = "project_manager_id";
    protected static final String FLD_DESCRIPTION = "description";
    protected static final String FLD_RECRUITER = "recruiter_id";
    protected static final String FLD_SOURCER = "sourcer_id";
    protected static final String FLD_DEADLINE = "deadline";
    protected static final String FLD_PROJECT_POSITIONS = "project_positions";
    protected static final String FLD_PROJECT_ASSOCIATES = "project_associates";
    protected static final String FLD_INTERVIEWERS = "interviewer_id";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "uuid")
    private UUID uuid;

    @Column(name = FLD_NAME, nullable = false)
    private String name;

    @Column(name = FLD_PROJECT_MANAGER)
    private User projectManager;

    @JoinColumn(name = FLD_DESCRIPTION, referencedColumnName = "id", nullable = false)
    private String description;

    // @Column(name = FLD_RECRUITER)
    // @OneToOne()
    // private List<UUID> recruiters;

    // @Column(name = FLD_SOURCER)
    // @ManyToMany
    // private List<UUID> sourcers;

    // @Column(name = FLD_INTERVIEWERS)
    // @ManyToMany
    // private List<User> interviewers;

    @Column(name = FLD_DEADLINE, nullable = true)
    private String deadline;

    @Column(name = FLD_PROJECT_POSITIONS)
    @OneToMany(targetEntity = UserRoles.class, mappedBy = "roles", fetch = FetchType.EAGER)
    private List<Position> projectPositions;

    @Column(name = FLD_PROJECT_ASSOCIATES)
    @OneToMany(targetEntity = UserRoles.class, mappedBy = "uuid", fetch = FetchType.EAGER)
    private List<User> projectAssocicates;

}
