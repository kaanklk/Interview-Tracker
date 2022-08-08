package tcs.interviewtracker.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import tcs.interviewtracker.exceptions.ResourceNotFoundException;
import tcs.interviewtracker.persistence.Candidate;
import tcs.interviewtracker.persistence.Interview;
import tcs.interviewtracker.persistence.Timeslot;
import tcs.interviewtracker.repository.PersonHasTimeslotRepository;
import tcs.interviewtracker.repository.PersonRepository;
import tcs.interviewtracker.repository.TimeslotRepository;

@Service
@AllArgsConstructor
public class TimeslotService {
    PersonHasTimeslotRepository personHasTimeslotRepository;
    TimeslotRepository timeslotRepository;

    public List<Timeslot> findAllTimeslots() {
        return timeslotRepository.findAll();
    }

    public Page<Timeslot> findTimeslotsPaginated(PageRequest request) {
        return timeslotRepository.findAll(request);
    }

    public Timeslot findTimeslotByUuid(UUID uuid) 
                throws ResourceNotFoundException
    {
        Optional<Timeslot> optTimeslot = timeslotRepository.getByUuid(uuid);
        if (optTimeslot.isEmpty()) {
            throw new ResourceNotFoundException();
        }
        return optTimeslot.get();
    }

    public Timeslot saveTimeslot(Timeslot toSave) {
        toSave.setUuid(UUID.randomUUID());
        return timeslotRepository.save(toSave);
    }

    public Timeslot updateTimeslot(UUID uuid, Timeslot toUpdate)
                throws ResourceNotFoundException 
    {
        var oldTimeslot = timeslotRepository.getByUuid(uuid);
        if (oldTimeslot.isEmpty()) {
            throw new ResourceNotFoundException();
        }

        toUpdate.setId(oldTimeslot.get().getId());
        toUpdate.setUuid(uuid);
        return timeslotRepository.save(toUpdate);
    }

    public Timeslot deleteTimeslot(UUID uuid) 
                throws ResourceNotFoundException 
    {
        var optTimeslot = timeslotRepository.getByUuid(uuid);
        if (optTimeslot.isEmpty()) {
            throw new ResourceNotFoundException();
        }
        timeslotRepository.delete(optTimeslot.get());
        return optTimeslot.get();
    }

// ----Candidate related -------------------------------------------------

    public List<Timeslot> findTimeslotsOfCandidate(Candidate candidate) {
        var relations = personHasTimeslotRepository.findByPerson(candidate.getPerson());
        ArrayList<Timeslot> timeslots = new ArrayList<Timeslot>();
        for (var relation : relations) {
            timeslots.add(relation.getTimeslot());
        }
        return timeslots;
    }
}
