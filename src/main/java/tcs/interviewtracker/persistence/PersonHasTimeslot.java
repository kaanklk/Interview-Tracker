package tcs.interviewtracker.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NonNull;

@Entity
@Data
@Table(name="person_has_timeslot")
public class PersonHasTimeslot {

    @Id
    @Column(name="id")
    @NonNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long personHasTimeslotId;

    @ManyToOne
    @Column(name="timeslot_id")
    private Timeslot timeslot;

    @ManyToOne
    @Column(name="person_id")
    private Person person;

    @Column(name="function")
    private String function;

    public PersonHasTimeslot() {
    }

    public PersonHasTimeslot(Timeslot  timeslot, Person person, String function) {
        this.timeslot = timeslot;
        this.person = person;
        this.function = function;
    }

}