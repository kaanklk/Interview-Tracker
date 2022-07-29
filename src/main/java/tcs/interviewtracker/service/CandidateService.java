package tcs.interviewtracker.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    public Candidate getByUuid(UUID uuid) {
        return candidateRepository.getByUuid(uuid);
    }

    public Candidate save(Candidate candidate) {
        return candidateRepository.save(candidate);
    }

    public Candidate update(UUID uuid, Candidate candidate) throws ResourceNotFoundException {
        Candidate oldCandidate = candidateRepository.getByUuid(uuid);
        if (null == oldCandidate) {
            throw new ResourceNotFoundException();
        }
        candidate.setId(oldCandidate.getId());
        candidate.setUuid(uuid);
        return candidateRepository.save(candidate);
    }

    public Candidate delete(UUID uuid) throws ResourceNotFoundException {
        Candidate toDelete = candidateRepository.getByUuid(uuid);
        if (null == toDelete) {
            throw new ResourceNotFoundException();
        }
        candidateRepository.delete(toDelete);
        return toDelete;
    }

    public List<StatusChange> getCandidateHistory(UUID candidateUuid) throws ResourceNotFoundException {
        Candidate candidate = candidateRepository.getByUuid(candidateUuid);
        if (null == candidate) {
            throw new ResourceNotFoundException();
        }
        return statusChangeRepository.getByCandidate(candidate);
    }

    public StatusChange getCandidateHistoryByUuid(UUID candidateUuid, Long statusChangeUuid)
            throws ResourceNotFoundException {
        Candidate candidate = candidateRepository.getByUuid(candidateUuid);
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

    public List<WorkExperience> findWorkExperiences(UUID candidateUuid) throws ResourceNotFoundException {
        Candidate candidate = candidateRepository.getByUuid(candidateUuid);
        if (null == candidate) {
            throw new ResourceNotFoundException();
        }
        return workExperienceRepository.getByCandidate(candidate);
    }

    public Page<WorkExperience> findWorkExperiencesPaginated(UUID candidateUuid, PageRequest request) 
                                    throws ResourceNotFoundException {
        Candidate candidate = candidateRepository.getByUuid(candidateUuid);
        if (null == candidate) {
            throw new ResourceNotFoundException();
        }
        return workExperienceRepository.getByCandidate(candidate, request);
    }

    public WorkExperience getWorkExperienceByUuid(UUID candidateId, UUID experienceUuid) 
                                throws ResourceNotFoundException {
        Candidate candidate = candidateRepository.getByUuid(candidateId);
        if (null == candidate) {
            throw new ResourceNotFoundException();
        }
        WorkExperience workExperience = workExperienceRepository.getByUuid(experienceUuid);
        if (null == workExperience) {
            throw new ResourceNotFoundException();
        }
        return workExperience;
    }

    public WorkExperience saveWorkExperience(WorkExperience workExperience) {
        workExperience.setUuid(UUID.randomUUID());
        return workExperienceRepository.save(workExperience);
    }

    public WorkExperience updateWorkExperience(UUID workExperienceUuid, WorkExperience workExperience) throws ResourceNotFoundException {
        WorkExperience oldWorkExperience = workExperienceRepository.getByUuid(workExperienceUuid);
        if (null == oldWorkExperience) {
            throw new ResourceNotFoundException();
        }
        workExperience.setId(oldWorkExperience.getId());
        workExperience.setUuid(workExperienceUuid);
        return workExperienceRepository.save(workExperience);
    }

    public WorkExperience deleteWorkExperience(UUID workExperienceUuid) throws ResourceNotFoundException {
        WorkExperience toDelete = workExperienceRepository.getByUuid(workExperienceUuid);
        if (null == toDelete) {
            throw new ResourceNotFoundException();
        }
        workExperienceRepository.delete(toDelete);
        return toDelete;
    }


    //Education:----------------------------------------------------------------

    public List<Education> findEducation(UUID candidateUuid) throws ResourceNotFoundException {
        Candidate candidate = candidateRepository.getByUuid(candidateUuid);
        if (null == candidate) {
            throw new ResourceNotFoundException();
        }
        return educationRepository.getByCandidate(candidate);
    }

    public Page<Education> findEducationPaginated(UUID candidateUuid, PageRequest request) 
                                    throws ResourceNotFoundException {
        Candidate candidate = candidateRepository.getByUuid(candidateUuid);
        if (null == candidate) {
            throw new ResourceNotFoundException();
        }
        return educationRepository.getByCandidate(candidate, request);
    }

    public Education getEducationByUuid(UUID candidateUuid, UUID educationUuid) 
                                throws ResourceNotFoundException {
        Candidate candidate = candidateRepository.getByUuid(candidateUuid);
        if (null == candidate) {
            throw new ResourceNotFoundException();
        }
        Education education = educationRepository.getByUuid(educationUuid);
        if (null == education) {
            throw new ResourceNotFoundException();
        }
        return education;
    }

    public Education saveEducation(Education education) {
        education.setUuid(UUID.randomUUID());
        return educationRepository.save(education);
    }

    public Education updateEducation(UUID educationUuid, Education education) throws ResourceNotFoundException {
        Education oldEducation = educationRepository.getByUuid(educationUuid);
        if (null == oldEducation) {
            throw new ResourceNotFoundException();
        }
        education.setId(oldEducation.getId());
        education.setUuid(educationUuid);
        return educationRepository.save(education);
    }

    public Education deleteEducation(UUID educationUuid) throws ResourceNotFoundException {
        Education toDelete = educationRepository.getByUuid(educationUuid);
        if (null == toDelete) {
            throw new ResourceNotFoundException();
        }
        educationRepository.delete(toDelete);
        return toDelete;
    }

}
