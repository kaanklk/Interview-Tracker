package tcs.interviewtracker.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tcs.interviewtracker.persistence.Timeslot;

@Repository
public interface TimeslotRepository extends CrudRepository<Timeslot, Long> {

}
