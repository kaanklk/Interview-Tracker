package tcs.interviewtracker.persistence;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NonNull;

@Entity
@Data
public class PersonHasTimeslot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long id;

    private UUID uuid;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Timeslot timeslot;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Person person;

    @Column(columnDefinition = "varchar(128)")
    private String function;
}