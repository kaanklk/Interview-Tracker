package tcs.interviewtracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tcs.interviewtracker.persistence.Timeslot;

@Repository
public interface TimeslotRepository extends JpaRepository<Timeslot, Long> {

}
