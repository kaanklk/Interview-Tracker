package tcs.interviewtracker.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import tcs.interviewtracker.persistence.ManagementDocumentation;
import tcs.interviewtracker.repository.ManagementDocumentationRepository;

@Service
public class ManagementDocumentationService {

    private ManagementDocumentationRepository manageRepo;

    public ManagementDocumentationService(@Autowired ManagementDocumentationRepository manageRepo) {
        this.manageRepo = manageRepo;
    }

    public <Optional>ManagementDocumentation getManageDocByUuid(UUID manageDocId) {
        return manageRepo.getReferenceByUuid(manageDocId);
    }

    public Page<ManagementDocumentation> findPaginated(PageRequest request) {
        return manageRepo.findAll(request);
    }

    public List<ManagementDocumentation>  getAllManageDocs() {
        return manageRepo.findAll();
    }

    public ManagementDocumentation saveManageDoc(ManagementDocumentation manageDoc) {
      return  manageRepo.save(manageDoc);
    }

    public ManagementDocumentation updateManageDoc(UUID id,  ManagementDocumentation manageDoc) {

        ManagementDocumentation updateDoc = manageRepo.getReferenceByUuid(id);

        manageRepo.save(updateDoc);

        return updateDoc;

    }

    public void deleteManageDoc(UUID id) {
        ManagementDocumentation manageDoc = manageRepo.getReferenceByUuid(id);
        manageRepo.delete(manageDoc);
    }

}
