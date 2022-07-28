package tcs.interviewtracker.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import tcs.interviewtracker.exceptions.ResourceNotFoundException;
import tcs.interviewtracker.persistence.Person;
import tcs.interviewtracker.repository.PersonRepository;

@Service
@AllArgsConstructor
public class PersonService {
    private PersonRepository personRepository;

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public Page<Person> findPaginated(PageRequest request) {
        return personRepository.findAll(request);
    }

    public Person getById(UUID uuid) {
        return personRepository.getReferenceByUuid(uuid);
    }

    public Person save(Person person) {
        person.setUuid(UUID.randomUUID());
        return personRepository.save(person);
    }

    public Person update(UUID uuid, Person person) throws ResourceNotFoundException {
        var oldPerson = personRepository.getReferenceByUuid(uuid);
        if (null == oldPerson) {
            throw new ResourceNotFoundException();
        }
        person.setId(oldPerson.getId());
        person.setUuid(uuid);
        return personRepository.save(person);
    }

    public Person delete(UUID uuid) throws ResourceNotFoundException {
        Person toDelete = personRepository.getReferenceByUuid(uuid);
        if (null == toDelete) {
            throw new ResourceNotFoundException();
        }
        personRepository.delete(toDelete);
        return toDelete;
    }

}
