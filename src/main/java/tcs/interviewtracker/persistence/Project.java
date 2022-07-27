package tcs.interviewtracker.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = Project.TBL_NAME)
@Data
@NoArgsConstructor
public class Project {
    protected static final String TBL_NAME = "project";

    protected static final String FLD_NAME = "name";
    protected static final String FLD_PROJECT_MANAGER = "project_manager";
    protected static final String FLD_DESCRIPTION = "description";
    protected static final String FLD_RECRUITER = "recruiter";
    protected static final String FLD_SOURCER = "sourcer";
    protected static final String FLD_DEADLINE = "deadline";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = FLD_NAME, nullable = false)
    private String name;

    @OneToOne
    @JoinColumn(name = FLD_PROJECT_MANAGER, referencedColumnName = "id", nullable = false)
    private User projectManager;

    @JoinColumn(name = FLD_DESCRIPTION, referencedColumnName = "id", nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = FLD_RECRUITER, referencedColumnName = "id", nullable = false)
    private User recruiter;

    @ManyToOne
    @JoinColumn(name = FLD_SOURCER, referencedColumnName = "id", nullable = false)
    private User sourcer;

    @Column(name = FLD_DEADLINE, nullable = true)
    private String deadline;

}
