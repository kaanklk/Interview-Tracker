package tcs.interviewtracker.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import tcs.interviewtracker.persistence.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
    public Person getReferenceByUuid(UUID uuid);
}
