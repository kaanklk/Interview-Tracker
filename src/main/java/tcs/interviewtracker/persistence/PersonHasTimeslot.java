package tcs.interviewtracker.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
    @JoinColumn(name="timeslot_id")
    private int timeslot_id;

    @OneToMany(mappedBy="person_id")
    @Column(name="person_id")
    private Person person;

    @Column(name="function")
    private String function;

    public PersonHasTimeslot() {
    }

    public PersonHasTimeslot(int personHasTimeslotId, int timeslot_id, Person person, String function) {
        this.personHasTimeslotId = personHasTimeslotId;
        this.timeslot_id = timeslot_id;
        this.person = person;
        this.function = function;
    }

    public int getPersonHasTimeslotId() {
        return personHasTimeslotId;
    }

    public void setPersonHasTimeslotId(int personHasTimeslotId) {
        this.personHasTimeslotId = personHasTimeslotId;
    }

    public int getTimeslot_id() {
        return timeslot_id;
    }

    public void setTimeslot_id(int timeslot_id) {
        this.timeslot_id = timeslot_id;
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
                + ", person_id=" + person + ", timeslot_id=" + timeslot_id + "]";
    }

}
