package tcs.interviewtracker.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tcs.interviewtracker.DTOs.ManagementDocumentationDTO;
import tcs.interviewtracker.exceptions.ResourceAlreadyExistsException;
import tcs.interviewtracker.exceptions.ResourceNotFoundException;
import tcs.interviewtracker.persistence.ManagementDocumentation;
import tcs.interviewtracker.persistence.User;
import tcs.interviewtracker.service.CandidateService;
import tcs.interviewtracker.service.ManagementDocumentationService;
import tcs.interviewtracker.service.ProjectService;
import tcs.interviewtracker.service.UserService;

@RestController
@RequestMapping("management-documentations")
public class ManagementDocumentationController {

    @Autowired
    private ManagementDocumentationService manageService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CandidateService candidateService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public ResponseEntity<List<ManagementDocumentationDTO>> getManageDocs(
        @RequestParam(required = false, defaultValue = "10") int pageSize,
        @RequestParam(required = false, defaultValue = "0")int offset,
        @RequestParam(required = false, defaultValue = "id")String orderBy,
        @RequestParam(required = false, defaultValue = "ascending") String orderDirection) throws ResourceNotFoundException {

        Pageable manageDocRequest = PageRequest.of(offset, pageSize, Sort.by(orderBy).ascending());

        if(orderDirection.equals("ascending")) {
            manageDocRequest = PageRequest.of(offset, pageSize, Sort.by(orderBy).ascending());
        }

        else if(orderDirection.equals("descending")) {
            manageDocRequest = PageRequest.of(offset, pageSize, Sort.by(orderBy).descending());
        }

        var manageDocs = manageService.findPaginated(manageDocRequest);
        var DTOs = new ArrayList<ManagementDocumentationDTO>();
        for(var manageDoc : manageDocs) {
            var manageDTO = convertingToDTO(manageDoc);
            DTOs.add(manageDTO);
        }

        return new ResponseEntity<List<ManagementDocumentationDTO>>(DTOs, HttpStatus.OK);
    }

    @GetMapping("/{manageDocId}")
    public ResponseEntity<ManagementDocumentationDTO> getManageDocById(@PathVariable(value = "manageDocId") UUID manageDocId)
    throws ResourceNotFoundException {
        var manageEntityOptional = manageService.getManageDocByUuid(manageDocId);
        ManagementDocumentation manageEntity= manageService.getManageDocByUuid(manageDocId).get();
        ManagementDocumentationDTO manageDTO = convertingToDTO(manageEntity);


          if(manageEntityOptional.isPresent()) {
            return new ResponseEntity<ManagementDocumentationDTO>(manageDTO, HttpStatus.OK);
        }

        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/")
    public ResponseEntity<ManagementDocumentationDTO>  createNewDoc(@Validated @RequestBody ManagementDocumentationDTO manageDTO)
    throws ResourceAlreadyExistsException, ResourceNotFoundException {
        var manageEntity = convertingToEntity(manageDTO);
        ManagementDocumentationDTO savedDTO = convertingToDTO(manageService.saveManageDoc(manageEntity));

        return new ResponseEntity<ManagementDocumentationDTO>(savedDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{manageDocId}")
    public ResponseEntity<ManagementDocumentationDTO>  updateDoc(@PathVariable(value = "manageDocId") UUID manageId,
    @Validated @ RequestBody ManagementDocumentationDTO manageDTO) throws ResourceNotFoundException {

        ManagementDocumentation manageDoc = convertingToEntity(manageDTO);
        ManagementDocumentationDTO updatedDocDTO = convertingToDTO(manageService.updateManageDoc(manageDoc));

        return new ResponseEntity<ManagementDocumentationDTO>(updatedDocDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{manageDocId}")
    public  ResponseEntity<ManagementDocumentationDTO> deleteManageDoc(@PathVariable(value = "manageDocId") UUID manageId )  throws ResourceNotFoundException {
        var manageDocOptional = manageService.getManageDocByUuid(manageId);
        ManagementDocumentation manageDoc = manageService.getManageDocByUuid(manageId).get();

           if(manageDocOptional.isPresent()) {
            manageService.deleteManageDoc(manageDoc);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    private ManagementDocumentationDTO convertingToDTO(ManagementDocumentation manageDoc) {
        ManagementDocumentationDTO manageDTO = modelMapper.map(manageDoc, ManagementDocumentationDTO.class);
        return manageDTO;
    }


    private ManagementDocumentation convertingToEntity(ManagementDocumentationDTO manageDTO) throws ResourceNotFoundException {
        var manageDoc = new ManagementDocumentation();

        if(manageDTO.getUuid() == null){
            manageDoc.setUuid(UUID.randomUUID());
        }

        else {
            manageDoc.setUuid(manageDTO.getUuid());
            if(manageService.getManageDocByUuid(manageDTO.getUuid()).get().getId()!= null){
                manageDoc.setId(manageService.getManageDocByUuid(manageDTO.getUuid()).get().getId());
            }
        }

        manageDoc.setUuid(manageDTO.getUuid());
        manageDoc.setDateOfInterview(manageDTO.getDateOfInterview());
        manageDoc.setMobileToWork(manageDTO.getMobileToWork());
        manageDoc.setFloorVisit(manageDTO.getFloorVisit());
        manageDoc.setMotivation(manageDTO.getMotivation());
        manageDoc.setCareerAspiration(manageDTO.getCareerAspiration());
        manageDoc.setFitment(manageDTO.getFitment());
        manageDoc.setOpennessToDiversity(manageDTO.getOpennessToDiversity());
        manageDoc.setProactiveness(manageDTO.getProactiveness());
        manageDoc.setPotentionalConflict(manageDTO.getPotentionalConflict());
        manageDoc.setTeamFit(manageDTO.getTeamFit());
        manageDoc.setObservations(manageDTO.getObservations());
        manageDoc.setOtherComments(manageDTO.getOtherComments());
        manageDoc.setOtherStrengths(manageDTO.getOtherStrengths());
        manageDoc.setOtherWeaknesses(manageDTO.getOtherWeaknesses());
        manageDoc.setRelevantExperience(manageDTO.getRelevantExperience());
        manageDoc.setTotalExperience(manageDTO.getTotalExperience());
        manageDoc.setRecommended(manageDTO.getRecommended());
        manageDoc.setRGSID(manageDTO.getRGSID());
        manageDoc.setDirectSupervisorName(manageDTO.getDirectSupervisorName());
        manageDoc.setCandidate(candidateService.getByUuid(manageDTO.getCandidateUuid()));
        manageDoc.setProject(projectService.getByUuid(manageDTO.getProjectUuid()));
        manageDoc.setInterviewer1(userService.getUserById(manageDTO.getInterviewer1Uuid()));
        manageDoc.setInterviewer2(userService.getUserById(manageDTO.getInterviewer2Uuid()));

        return manageDoc;
    }
}
