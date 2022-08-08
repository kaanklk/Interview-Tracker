package tcs.interviewtracker.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import tcs.interviewtracker.exceptions.ResourceNotFoundException;
import tcs.interviewtracker.persistence.Interview;
import tcs.interviewtracker.persistence.InterviewType;
import tcs.interviewtracker.repository.InterviewRepository;
import tcs.interviewtracker.repository.InterviewTypeRepository;
import tcs.interviewtracker.repository.TimeslotRepository;

@Service
@AllArgsConstructor
public class InterviewService {
    InterviewRepository interviewRepository;
    InterviewTypeRepository interviewTypeRepository;
    TimeslotRepository timeslotRepository;

    public List<Interview> findAllInterviews() {
        return interviewRepository.findAll();
    }

    public Page<Interview> findInterviewsPaginated(PageRequest request) {
        return interviewRepository.findAll(request);
    }

    public Interview getInterviewByUuid(UUID uuid) throws ResourceNotFoundException {
        var optInterview = interviewRepository.getByUuid(uuid);
        if (optInterview.isEmpty()) {
            throw new ResourceNotFoundException();
        }
        return optInterview.get();
    }

    public Interview saveInterview(Interview toSave) {
        toSave.setUuid(UUID.randomUUID());
        return interviewRepository.save(toSave);
    }

    public Interview updateInterview(UUID uuid, Interview toUpdate)
                throws ResourceNotFoundException 
    {
        var oldInterview = interviewRepository.getByUuid(uuid);
        if (oldInterview.isEmpty()) {
            throw new ResourceNotFoundException();
        }

        toUpdate.setId(oldInterview.get().getId());
        toUpdate.setUuid(uuid);
        return interviewRepository.save(toUpdate);
    }

    public Interview deleteInterview(UUID uuid) 
                throws ResourceNotFoundException 
    {
        var optInterview = interviewRepository.getByUuid(uuid);
        if (optInterview.isEmpty()) {
            throw new ResourceNotFoundException();
        }
        interviewRepository.delete(optInterview.get());
        return optInterview.get();
    }

//------------ Interview Type --------------------------------------------------

    public List<InterviewType> findAllInterviewTypes() {
        return interviewTypeRepository.findAll();
    }

    public InterviewType saveInterviewType(InterviewType toSave) {
        return interviewTypeRepository.save(toSave);
    }

    public InterviewType deleteInterviewType(InterviewType toDelete) 
    {
        interviewTypeRepository.delete(toDelete);
        return toDelete;
    }

}
