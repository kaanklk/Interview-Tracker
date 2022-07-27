package tcs.interviewtracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tcs.interviewtracker.persistence.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
    
}
