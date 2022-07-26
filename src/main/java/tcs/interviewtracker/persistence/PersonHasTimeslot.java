package tcs.interviewtracker.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Data
@Table(name="person_has_timeslot")
public class PersonHasTimeslot {

    @Id
    @Column(name="id")
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int personHasTimeslotId;

    @ManyToOne
    @Column(name="timeslot_id")
    private Timeslot timeslot;

    @OneToMany(mappedBy="person_id")
    @Column(name="person_id")
    private Person person;

    @Column(name="function")
    private String function;

    public PersonHasTimeslot() {
    }

    public PersonHasTimeslot(int personHasTimeslotId, Timeslot  timeslot, Person person, String function) {
        this.personHasTimeslotId = personHasTimeslotId;
        this.timeslot = timeslot;
        this.person = person;
        this.function = function;
    }
    
    @Override
    public String toString() {
        return "PersonHasTimeslot [function=" + function + ", personHasTimeslotId=" + personHasTimeslotId
                + ", person_id=" + person + ", timeslot_id=" + timeslot + "]";
    }

}
