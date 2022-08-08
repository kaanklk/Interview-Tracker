package tcs.interviewtracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tcs.interviewtracker.persistence.Person;
import tcs.interviewtracker.persistence.PersonHasTimeslot;

@Repository
public interface PersonHasTimeslotRepository  extends JpaRepository<PersonHasTimeslot, Long> {
    List<PersonHasTimeslot> findByPerson(Person person);
}
