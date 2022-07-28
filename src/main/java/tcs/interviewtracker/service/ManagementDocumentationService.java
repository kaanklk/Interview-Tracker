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

    public <Optional>ManagementDocumentation getManageDocById(Long manageDocId) {
        return manageRepo.getReferenceById(manageDocId);
    }

    public List<ManagementDocumentation>  getAllManageDocs() {
        return manageRepo.findAll();
    }

    public ManagementDocumentation saveManageDoc(ManagementDocumentation manageDoc) {
      return  manageRepo.save(manageDoc);
    }

    public ManagementDocumentation updateManageDoc(Long id,  ManagementDocumentation manageDoc) {

        ManagementDocumentation updateDoc = manageRepo.findById(id).get();

        manageRepo.save(updateDoc);

        return updateDoc;

    }

    public void deleteManageDoc(Long id) {
        ManagementDocumentation manageDoc = manageRepo.findById(id).get();
        manageRepo.delete(manageDoc);
    }

}
