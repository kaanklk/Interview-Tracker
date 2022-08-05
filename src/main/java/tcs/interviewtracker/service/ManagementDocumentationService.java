package tcs.interviewtracker.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import tcs.interviewtracker.persistence.ManagementDocumentation;
import tcs.interviewtracker.repository.ManagementDocumentationRepository;

@Service
public class ManagementDocumentationService {

    private ManagementDocumentationRepository manageRepo;

    public ManagementDocumentationService(@Autowired ManagementDocumentationRepository manageRepo) {
        this.manageRepo = manageRepo;
    }

    public Optional<ManagementDocumentation> getManageDocByUuid(UUID manageDocId) {
        return manageRepo.getReferenceByUuid(manageDocId);
    }

    public Page<ManagementDocumentation> getAllManageDocs(Pageable pageData) {
        return manageRepo.findAll(pageData);
    }

    public Page<ManagementDocumentation> findPaginated(Pageable request) {
        return manageRepo.findAll(request);
    }

    public ManagementDocumentation saveManageDoc(ManagementDocumentation manageDoc) {
        manageDoc.setUuid(UUID.randomUUID());
        return manageRepo.save(manageDoc);
    }

    public ManagementDocumentation updateManageDoc(ManagementDocumentation manageDoc) {
        return manageRepo.save(manageDoc);
    }

    public ManagementDocumentation updateManageDoc(Long id, ManagementDocumentation manageDoc){

        ManagementDocumentation updatedManageDoc = manageRepo.save(manageDoc);
        return updatedManageDoc;
    }

    public void deleteManageDoc(ManagementDocumentation manageDoc) {
        manageRepo.delete(manageDoc);
    }

}
