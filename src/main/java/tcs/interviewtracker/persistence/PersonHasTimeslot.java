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

    @OneToMany
    @JoinColumn(name="person_id")
    private int person_id;

    @Column(name="function")
    private String function;

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

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
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
                + ", person_id=" + person_id + ", timeslot_id=" + timeslot_id + "]";
    }

}
