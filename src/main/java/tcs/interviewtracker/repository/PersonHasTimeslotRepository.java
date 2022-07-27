package tcs.interviewtracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tcs.interviewtracker.persistence.PersonHasTimeslot;

@Repository
public interface PersonHasTimeslotRepository  extends JpaRepository<PersonHasTimeslot, Long> {

}
