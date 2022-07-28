package tcs.interviewtracker.service;

import java.util.List;
import java.util.Optional;

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

    public Person getById(Long id) {
        return personRepository.getReferenceById(id);
    }

    public Person save(Person person) {
        return personRepository.save(person);
    }

    public Person update(Long id, Person person) throws ResourceNotFoundException {
        Optional<Person> oldPerson = personRepository.findById(id);
        if (oldPerson.isEmpty()) {
            throw new ResourceNotFoundException();
        }
        person.setId(id);
        return personRepository.save(person);
    }

    public Person delete(Long id) throws ResourceNotFoundException {
        Person toDelete = personRepository.getReferenceById(id);
        if (null == toDelete) {
            throw new ResourceNotFoundException();
        }
        personRepository.delete(toDelete);
        return toDelete;
    }

}
