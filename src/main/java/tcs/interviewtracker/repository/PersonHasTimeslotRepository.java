package tcs.interviewtracker.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tcs.interviewtracker.persistence.PersonHasTimeslot;

@Repository
public interface PersonHasTimeslotRepository  extends CrudRepository<PersonHasTimeslot, Long> {

}
