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
    protected static final String FLD_DESCRIPTION = "description";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "uuid")
    private UUID uuid;

    @Column(name = FLD_NAME, nullable = false)
    private String name;

    @JoinColumn(name = FLD_DESCRIPTION, referencedColumnName = "id", nullable = false)
    private String description;

}
