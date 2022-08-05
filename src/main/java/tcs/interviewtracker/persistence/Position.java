package tcs.interviewtracker.persistence;

import java.sql.Date;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.lang.Nullable;

import lombok.Data;

@Entity
@Data
@Table(name = "position")
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "uuid")
    private UUID uuid;

    @Column(columnDefinition = "varchar(128)")
    private String positionName;

    @OneToOne
    @JoinColumn(name = "project_id", referencedColumnName = "id")
    private Project project;

    @OneToMany(mappedBy = "position")
    private Set<Candidate> candidates;

    @Column(name = "total_count")
    @Nullable
    private Integer totalCount;

    @Column
    @Nullable
    private Integer hiredCount;

    @Column
    @Nullable
    private Boolean open;

    @Column(name = "deadline")
    @Nullable
    private Date dateOfBirth;

    @ManyToMany
    @JoinTable(name = "position_has_interviewers", joinColumns = @JoinColumn(name = "position_id"), inverseJoinColumns = @JoinColumn(name = "course_id"))
    Set<User> interviewers;

}
