package tcs.interviewtracker.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = Project.TBL_NAME)
@Data
public class Project {
    protected static final String TBL_NAME="project";

    protected static final String FLD_NAME="name";
    protected static final String FLD_PROJECT_MANAGER_ID = "project_manager_id";
    protected static final String FLD_DESCRIPTION = "description";
    protected static final String FLD_RECRUITER_ID = "recruiter_id";
    protected static final String FLD_SOURCER_ID = "sourcer_id";
    protected static final String FLD_DEADLINE = "deadline";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = FLD_NAME, nullable = false)
    private String name;

    // TODO: One to one
    @Column(name = FLD_PROJECT_MANAGER_ID,nullable = false)
    private Long projectManagerId;

    @Column(name = FLD_DESCRIPTION,nullable = false)
    private Long description;

    //TODO: One to many?
    @Column(name = FLD_RECRUITER_ID,nullable = true)
    private Long recruiterId;

    // TODO: One to many?
    @Column(name = FLD_SOURCER_ID,nullable = true)
    private Long sourcerId;

    @Column(name = FLD_DEADLINE,nullable = true)
    private String deadline;

}
