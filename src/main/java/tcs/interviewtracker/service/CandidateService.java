package tcs.interviewtracker.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import tcs.interviewtracker.DTOs.WorkExperienceDTO;
import tcs.interviewtracker.exceptions.ResourceNotFoundException;
import tcs.interviewtracker.persistence.Candidate;
import tcs.interviewtracker.persistence.Education;
import tcs.interviewtracker.persistence.Language;
import tcs.interviewtracker.persistence.ManagementDocumentation;
import tcs.interviewtracker.persistence.Position;
import tcs.interviewtracker.persistence.StatusChange;
import tcs.interviewtracker.persistence.TechnicalDocumentation;
import tcs.interviewtracker.persistence.User;
import tcs.interviewtracker.persistence.WorkExperience;
import tcs.interviewtracker.repository.CandidateRepository;
import tcs.interviewtracker.repository.EducationRepository;
import tcs.interviewtracker.repository.LanguageRepository;
import tcs.interviewtracker.repository.ManagementDocumentationRepository;
import tcs.interviewtracker.repository.PositionRepository;
import tcs.interviewtracker.repository.StatusChangeRepository;
import tcs.interviewtracker.repository.TechnicalDocumentationRepository;
import tcs.interviewtracker.repository.UserRepository;
import tcs.interviewtracker.repository.WorkExperienceRepository;

@Service
@AllArgsConstructor
public class CandidateService {
    private CandidateRepository candidateRepository;
    private StatusChangeRepository statusChangeRepository;
    private WorkExperienceRepository workExperienceRepository;
    private EducationRepository educationRepository;
    private UserRepository userRepository;
    private TechnicalDocumentationRepository technicalDocumentationRepository;
    private ManagementDocumentationRepository managementDocumentationRepository;
    private PositionRepository positionRepository;
    private LanguageRepository languageRepository;

    public List<Candidate> findAll() {
        return candidateRepository.findAll();
    }

    public Page<Candidate> findPaginated(PageRequest request) {
        return candidateRepository.findAll(request);
    }

    public Candidate getByUuid(UUID uuid) throws ResourceNotFoundException {
        var candidate = candidateRepository.getByUuid(uuid);
        if (null == candidate) {
            throw new ResourceNotFoundException();
        }
        return candidate;
    }

    public Candidate save(Candidate candidate) {
        candidate.setUuid(UUID.randomUUID());
        return candidateRepository.save(candidate);
    }

    public Candidate update(UUID uuid, Candidate candidate) throws ResourceNotFoundException {
        Candidate oldCandidate = candidateRepository.getByUuid(uuid);
        if (null == oldCandidate) {
            throw new ResourceNotFoundException();
        }

        //Delete old attached records:
        deleteWorkExperience(oldCandidate);
        deleteLanguage(oldCandidate);
        deleteEducations(oldCandidate);

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

    public StatusChange getCandidateHistoryByUuid(UUID candidateUuid, UUID statusChangeUuid)
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

    public Optional<User> getInterviewer(UUID interviewerUuid) {
        return userRepository.findByUuid(interviewerUuid);
    }

    public TechnicalDocumentation getTechnicalDocumentation(UUID documentationUuid) {
        return technicalDocumentationRepository.findByUuid(documentationUuid).get();
    }

    public Optional<ManagementDocumentation> getManagementDocumentation(UUID documentationUuid) {
        return managementDocumentationRepository.getReferenceByUuid(documentationUuid);
    }

    public Optional<Position> getPosition(UUID positionUuid) {
        return positionRepository.findByUuid(positionUuid);
    }

    public TechnicalDocumentation getTechnicalDocumentationOfCandidate(UUID candidateUuid) {
        var candidate = candidateRepository.getByUuid(candidateUuid);
        if (null == candidate) {
            return null;
        }
        return technicalDocumentationRepository.getReferenceByCandidate(candidate).get();
    }

    public ManagementDocumentation getManagementDocumentationByCandidate(UUID candidateUuid) {
        var candidate = candidateRepository.getByUuid(candidateUuid);
        if (null == candidate) {
            return null;
        }
        return managementDocumentationRepository.getReferenceByCandidate(candidate);
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

    public WorkExperience saveWorkExperience(WorkExperience workExperience) {
        return workExperienceRepository.save(workExperience);
    }

    public void deleteWorkExperience(Candidate candidate) throws ResourceNotFoundException {
        List<WorkExperience> toDelete = workExperienceRepository.getByCandidate(candidate);
        for (var d : toDelete) {
            workExperienceRepository.delete(d);    
        }
    }

    // Education:----------------------------------------------------------------

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

    public Education saveEducation(Education education) {
        return educationRepository.save(education);
    }

    public void deleteEducations(Candidate candidate) throws ResourceNotFoundException {
        List<Education> toDelete = educationRepository.getByCandidate(candidate);
        for (var d : toDelete) {
            educationRepository.delete(d);    
        }
    }

    // Languages:----------------------------------------------------------------

    public List<Language> findLanguages(UUID candidateUuid) throws ResourceNotFoundException {
        Candidate candidate = candidateRepository.getByUuid(candidateUuid);
        if (null == candidate) {
            throw new ResourceNotFoundException();
        }
        return languageRepository.getByCandidate(candidate);
    }

    public Page<Language> findLanguagePaginated(UUID candidateUuid, PageRequest request)
            throws ResourceNotFoundException {
        Candidate candidate = candidateRepository.getByUuid(candidateUuid);
        if (null == candidate) {
            throw new ResourceNotFoundException();
        }
        return languageRepository.getByCandidate(candidate, request);
    }

    public Language saveLanguage(Language language) {
        return languageRepository.save(language);
    }

    public void deleteLanguage(Candidate candidate) throws ResourceNotFoundException {
        List<Language> toDelete = languageRepository.getByCandidate(candidate);
        for (var d : toDelete) {
            languageRepository.delete(d);    
        }
    }
}
