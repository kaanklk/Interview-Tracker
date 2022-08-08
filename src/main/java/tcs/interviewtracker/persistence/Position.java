package tcs.interviewtracker.persistence;

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

    @Column(name = "total_count")
    private Integer totalCount;

    @Column
    private Integer hiredCount;

    @Column
    private Boolean open;

    @ManyToMany
    @JoinTable(name = "position_has_interviewers", joinColumns = @JoinColumn(name = "position_id"), inverseJoinColumns = @JoinColumn(name = "course_id"))
    Set<User> interviewers;

}
