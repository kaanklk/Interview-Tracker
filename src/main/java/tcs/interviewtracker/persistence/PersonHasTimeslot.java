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
@Table(name="person_has_timeslot")
public class PersonHasTimeslot {

    @Id
    @Column(name="id")
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

    public int getPersonHasTimeslotId() {
        return personHasTimeslotId;
    }

    public void setPersonHasTimeslotId(int personHasTimeslotId) {
        this.personHasTimeslotId = personHasTimeslotId;
    }

    public Timeslot getTimeslot_id() {
        return timeslot;
    }

    public void setTimeslot_id(Timeslot timeslot) {
        this.timeslot = timeslot;
    }

    public Person getPerson_id() {
        return person;
    }

    public void setPerson_id(Person person) {
        this.person = person;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    @Override
    public String toString() {
        return "PersonHasTimeslot [function=" + function + ", personHasTimeslotId=" + personHasTimeslotId
                + ", person_id=" + person + ", timeslot_id=" + timeslot + "]";
    }

}
