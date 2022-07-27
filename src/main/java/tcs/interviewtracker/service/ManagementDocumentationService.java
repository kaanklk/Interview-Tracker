package tcs.interviewtracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tcs.interviewtracker.persistence.ManagementDocumentation;
import tcs.interviewtracker.repository.ManagementDocumentationRepository;

@Service
public class ManagementDocumentationService {

    private ManagementDocumentationRepository manageRepo;

    public ManagementDocumentationService(@Autowired ManagementDocumentationRepository manageRepo) {
        this.manageRepo = manageRepo;
    }

    public ManagementDocumentation getManageDocById(Long manageDocId) {
        return manageRepo.getReferenceById(manageDocId);
    }

    public List<ManagementDocumentation>  getAllManageDocs() {
        return manageRepo.findAll();
    }

    public void saveManageDoc(ManagementDocumentation manageDoc) {
        manageRepo.save(manageDoc);
    }

    public ManagementDocumentation updateManageDoc(Long id,  ManagementDocumentation manageDoc) {

        ManagementDocumentation updateDoc = manageRepo.findById(id).get();

        updateDoc.setDateOfInterview(manageDoc.getDateOfInterview());
        updateDoc.setMobileToWork(manageDoc.getMobileToWork());
        updateDoc.setFloorVisit(manageDoc.getFloorVisit());
        updateDoc.setMotivation(manageDoc.getMotivation());
        updateDoc.setCareerAspiration(manageDoc.getCareerAspiration());
        updateDoc.setFitment(manageDoc.getFitment());
        updateDoc.setOpennessToDiversity(manageDoc.getOpennessToDiversity());
        updateDoc.setProactiveness(manageDoc.getProactiveness());
        updateDoc.setPotentionalConflict(manageDoc.getPotentionalConflict());
        updateDoc.setTeamFit(manageDoc.getTeamFit());
        updateDoc.setObservations(manageDoc.getObservations());
        updateDoc.setOtherComments(manageDoc.getOtherComments());
        updateDoc.setOtherStrengths(manageDoc.getOtherStrengths());
        updateDoc.setOtherWeaknesses(manageDoc.getOtherWeaknesses());
        updateDoc.setRelevantExperience(manageDoc.getRelevantExperience());
        updateDoc.setTotalExperience(manageDoc.getTotalExperience());
        updateDoc.setRecommended(manageDoc.getRecommended());
        updateDoc.setRGSID(manageDoc.getRGSID());
        updateDoc.setDirectSupervisorName(manageDoc.getDirectSupervisorName());
        updateDoc.setCandidate(manageDoc.getCandidate());
        updateDoc.setProject(manageDoc.getProject());
        updateDoc.setInterviewer1(manageDoc.getInterviewer1());
        updateDoc.setInterviewer2(manageDoc.getInterviewer2());

        manageRepo.save(updateDoc);

        return updateDoc;

    }

    public void deleteManageDoc(Long id) {
        ManagementDocumentation manageDoc = manageRepo.findById(id).get();
        manageRepo.delete(manageDoc);
    }

}
