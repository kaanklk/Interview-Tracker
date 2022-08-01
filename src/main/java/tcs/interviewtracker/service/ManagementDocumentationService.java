package tcs.interviewtracker.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import tcs.interviewtracker.persistence.Candidate;
import tcs.interviewtracker.persistence.ManagementDocumentation;
import tcs.interviewtracker.persistence.Project;
import tcs.interviewtracker.persistence.User;
import tcs.interviewtracker.repository.ManagementDocumentationRepository;

@Service
public class ManagementDocumentationService {

    private ManagementDocumentationRepository manageRepo;

    public ManagementDocumentationService(@Autowired ManagementDocumentationRepository manageRepo) {
        this.manageRepo = manageRepo;
    }

    public <Optional> ManagementDocumentation getManageDocByUuid(UUID manageDocId) {
        return manageRepo.getReferenceByUuid(manageDocId);
    }

    public Page<ManagementDocumentation> findPaginated(PageRequest request) {
        // return manageRepo.findAll(request);
        Candidate candidate1 = new Candidate();
        Project project1 = new Project();
        User user1 = new User();
        User user2 = new User();

        candidate1.setId(1L);
        candidate1.setUuid(UUID.randomUUID());
        project1.setId(1L);
        project1.setUuid(UUID.randomUUID());
        user1.setId(1L);
        user1.setUuid(UUID.randomUUID());
        user2.setId(1L);
        user2.setUuid(UUID.randomUUID());

        ManagementDocumentation manageDoc1 = new ManagementDocumentation();
        manageDoc1.setCandidate(candidate1);
        manageDoc1.setProject(project1);
        manageDoc1.setInterviewer1(user1);
        manageDoc1.setInterviewer1(user2);
        manageDoc1.setFitment("he/she fits");
        manageDoc1.setOtherComments("this is a comment!");

        ArrayList list = new ArrayList<>();
        list.add(manageDoc1);

        Page<ManagementDocumentation> page = new PageImpl<>(list);

        return page;
    }

    /*
    public List<ManagementDocumentation> findPaginated2(PageRequest request) {
        // return manageRepo.findAll(request);
        Candidate candidate1 = new Candidate();
        Project project1 = new Project();
        User user1 = new User();
        User user2 = new User();

        candidate1.setId(1L);
        candidate1.setUuid(UUID.randomUUID());
        project1.setId(1L);
        project1.setUuid(UUID.randomUUID());
        user1.setId(1L);
        user1.setUuid(UUID.randomUUID());
        user2.setId(1L);
        user2.setUuid(UUID.randomUUID());

        ManagementDocumentation manageDoc1 = new ManagementDocumentation();
        manageDoc1.setCandidate(candidate1);
        manageDoc1.setProject(project1);
        manageDoc1.setInterviewer1(user1);
        manageDoc1.setInterviewer1(user2);
        manageDoc1.setFitment("he/she fits");
        manageDoc1.setOtherComments("this is a comment!");

        ArrayList<ManagementDocumentation> list = new ArrayList<ManagementDocumentation>();
        list.add(manageDoc1);

        return list;
    }

    */

    public List<ManagementDocumentation> getAllManageDocs() {
        return manageRepo.findAll();
    }

    public ManagementDocumentation saveManageDoc(ManagementDocumentation manageDoc) {
        return manageRepo.save(manageDoc);
    }

    public ManagementDocumentation updateManageDoc(UUID id, ManagementDocumentation manageDoc) {

        ManagementDocumentation updateDoc = manageRepo.getReferenceByUuid(id);

        manageRepo.save(updateDoc);

        return updateDoc;

    }

    public void deleteManageDoc(UUID id) {
        ManagementDocumentation manageDoc = manageRepo.getReferenceByUuid(id);
        manageRepo.delete(manageDoc);
    }

}
