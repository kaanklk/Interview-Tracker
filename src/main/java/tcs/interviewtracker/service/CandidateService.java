package tcs.interviewtracker.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import tcs.interviewtracker.exceptions.ResourceNotFoundException;
import tcs.interviewtracker.persistence.Candidate;
import tcs.interviewtracker.persistence.Education;
import tcs.interviewtracker.persistence.StatusChange;
import tcs.interviewtracker.persistence.WorkExperience;
import tcs.interviewtracker.repository.CandidateRepository;
import tcs.interviewtracker.repository.EducationRepository;
import tcs.interviewtracker.repository.StatusChangeRepository;
import tcs.interviewtracker.repository.WorkExperienceRepository;

@Service
@AllArgsConstructor
public class CandidateService {
    private CandidateRepository candidateRepository;
    private StatusChangeRepository statusChangeRepository;
    private WorkExperienceRepository workExperienceRepository;
    private EducationRepository educationRepository;


    public List<Candidate> findAll() {
        return candidateRepository.findAll();
    }

    public Page<Candidate> findPaginated(PageRequest request) {
        return candidateRepository.findAll(request);
    }

    public Candidate getById(Long id) {
        return candidateRepository.getReferenceById(id);
    }

    public Candidate save(Candidate candidate) {
        return candidateRepository.save(candidate);
    }

    public Candidate update(Long id, Candidate candidate) throws ResourceNotFoundException {
        Optional<Candidate> oldCandidate = candidateRepository.findById(id);
        if (oldCandidate.isEmpty()) {
            throw new ResourceNotFoundException();
        }
        candidate.setId(id);
        return candidateRepository.save(candidate);
    }

    public Candidate delete(Long id) throws ResourceNotFoundException {
        Candidate toDelete = candidateRepository.getReferenceById(id);
        if (null == toDelete) {
            throw new ResourceNotFoundException();
        }
        candidateRepository.delete(toDelete);
        return toDelete;
    }

    public List<StatusChange> getCandidateHistory(Long candidateId) throws ResourceNotFoundException {
        Candidate candidate = candidateRepository.getReferenceById(candidateId);
        if (null == candidate) {
            throw new ResourceNotFoundException();
        }
        return statusChangeRepository.getByCandidate(candidate);
    }

    public StatusChange getCandidateHistoryById(Long candidateId, Long statusChangeId)
            throws ResourceNotFoundException {
        Candidate candidate = candidateRepository.getReferenceById(candidateId);
        if (null == candidate) {
            throw new ResourceNotFoundException();
        }
        List<StatusChange> history = statusChangeRepository.getByCandidate(candidate);
        for (StatusChange statusChange : history) {
            if (statusChange.getCandidate().equals(candidate)) {
                return statusChange;
            }
        }
        throw new ResourceNotFoundException();
    }

    //WorkExperience:----------------------------------------------------

    public List<WorkExperience> findWorkExperiences(Long candidateId) throws ResourceNotFoundException {
        Candidate candidate = candidateRepository.getReferenceById(candidateId);
        if (null == candidate) {
            throw new ResourceNotFoundException();
        }
        return workExperienceRepository.getByCandidate(candidate);
    }

    public Page<WorkExperience> findWorkExperiencesPaginated(Long candidateId, PageRequest request) 
                                    throws ResourceNotFoundException {
        Candidate candidate = candidateRepository.getReferenceById(candidateId);
        if (null == candidate) {
            throw new ResourceNotFoundException();
        }
        return workExperienceRepository.getByCandidate(candidate, request);
    }

    public WorkExperience getWorkExperienceById(Long candidateId, Long experienceId) 
                                throws ResourceNotFoundException {
        Candidate candidate = candidateRepository.getReferenceById(candidateId);
        if (null == candidate) {
            throw new ResourceNotFoundException();
        }
        WorkExperience workExperience = workExperienceRepository.getReferenceById(experienceId);
        if (null == workExperience) {
            throw new ResourceNotFoundException();
        }
        return workExperience;
    }

    public WorkExperience saveWorkExperience(WorkExperience workExperience) {
        return workExperienceRepository.save(workExperience);
    }

    public WorkExperience updateWorkExperience(Long workExperienceId, WorkExperience workExperience) throws ResourceNotFoundException {
        Optional<WorkExperience> oldWorkExperience = workExperienceRepository.findById(workExperienceId);
        if (oldWorkExperience.isEmpty()) {
            throw new ResourceNotFoundException();
        }
        workExperience.setId(workExperienceId);
        return workExperienceRepository.save(workExperience);
    }

    public WorkExperience deleteWorkExperience(Long workExperienceId) throws ResourceNotFoundException {
        WorkExperience toDelete = workExperienceRepository.getReferenceById(workExperienceId);
        if (null == toDelete) {
            throw new ResourceNotFoundException();
        }
        workExperienceRepository.delete(toDelete);
        return toDelete;
    }


    //Education:----------------------------------------------------------------

    public List<Education> findEducation(Long candidateId) throws ResourceNotFoundException {
        Candidate candidate = candidateRepository.getReferenceById(candidateId);
        if (null == candidate) {
            throw new ResourceNotFoundException();
        }
        return educationRepository.getByCandidate(candidate);
    }

    public Page<Education> findEducationPaginated(Long candidateId, PageRequest request) 
                                    throws ResourceNotFoundException {
        Candidate candidate = candidateRepository.getReferenceById(candidateId);
        if (null == candidate) {
            throw new ResourceNotFoundException();
        }
        return educationRepository.getByCandidate(candidate, request);
    }

    public Education getEducationById(Long candidateId, Long educationId) 
                                throws ResourceNotFoundException {
        Candidate candidate = candidateRepository.getReferenceById(candidateId);
        if (null == candidate) {
            throw new ResourceNotFoundException();
        }
        Education education = educationRepository.getReferenceById(educationId);
        if (null == education) {
            throw new ResourceNotFoundException();
        }
        return education;
    }

    public Education saveEducation(Education education) {
        return educationRepository.save(education);
    }

    public Education updateEducation(Long educationId, Education education) throws ResourceNotFoundException {
        Optional<Education> oldEducation = educationRepository.findById(educationId);
        if (oldEducation.isEmpty()) {
            throw new ResourceNotFoundException();
        }
        education.setId(educationId);
        return educationRepository.save(education);
    }

    public Education deleteEducation(Long educationId) throws ResourceNotFoundException {
        Education toDelete = educationRepository.getReferenceById(educationId);
        if (null == toDelete) {
            throw new ResourceNotFoundException();
        }
        educationRepository.delete(toDelete);
        return toDelete;
    }

}
