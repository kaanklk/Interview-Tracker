package tcs.interviewtracker.persistence;

import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;



@Entity
@Data
@Table(name = "position")
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="uuid")
    private UUID uuid;

    @Column(columnDefinition = "varchar(128)")
    private String positionName;

    @OneToOne
    @JoinColumn(name = "project_id", referencedColumnName = "id")
    private Project project;

    @OneToMany(mappedBy = "position")
    private Set<Candidate> candidates;

    private Integer totalCount;
    private Integer hiredCount;
    private Boolean open;

}
