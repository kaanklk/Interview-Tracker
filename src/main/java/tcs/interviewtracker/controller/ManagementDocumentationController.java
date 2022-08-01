package tcs.interviewtracker.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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
import tcs.interviewtracker.mappers.ManagementDocumentationMapper;
import tcs.interviewtracker.persistence.ManagementDocumentation;
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
    private CandidateService candidateSerivce;

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectService projectService;

    @GetMapping("/")
    public ResponseEntity<List<ManagementDocumentationDTO>> getManageDocs(
        @RequestParam(required = false, defaultValue = "10") int pageSize,
        @RequestParam(required = false, defaultValue = "1")int offset,
        @RequestParam(required = false, defaultValue = "id")String sortBy,
        @RequestParam(required = false, defaultValue = "ascending") String orderDirection) throws ResourceNotFoundException {

        Sort SortByOrdered = orderDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
		: Sort.by(sortBy).descending();

        PageRequest manageDocRequest = PageRequest.of(offset, pageSize, SortByOrdered);
        var manageDocs = manageService.findPaginated(manageDocRequest);
        var DTOs = new ArrayList<Optional<ManagementDocumentationDTO>>();
        for(var manageDoc : manageDocs) {
            var manageDTO = MyDtoConverter(manageDoc);
            DTOs.add(manageDTO);
        }

        return new ResponseEntity<List<ManagementDocumentationDTO>>(HttpStatus.OK);
    }

    @GetMapping("/{manageDocId}")
    public ResponseEntity<ManagementDocumentationDTO> getManageDocById(@PathVariable(value = "manageDocId") UUID manageDocId)
    throws ResourceNotFoundException {
        Optional<ManagementDocumentation> manageEntity= manageService.getManageDocByUuid(manageDocId);
        ManagementDocumentationDTO manageDTO = MyDtoConverter(manageEntity);

        return new ResponseEntity<ManagementDocumentationDTO>(manageDTO, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<ManagementDocumentationDTO>  createNewDoc(@Validated @RequestBody ManagementDocumentationDTO manageDTO)
    throws ResourceAlreadyExistsException {
        var manageEntity = MyEntityConverter(manageDTO);
        ManagementDocumentationDTO savedDTO = MyDtoConverter(manageEntity);

        return new ResponseEntity<ManagementDocumentationDTO>(savedDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{manageDocId}")
    public ResponseEntity<ManagementDocumentationDTO>  updateDoc(@PathVariable(value = "mangeDocId") UUID manageId,
    @Validated @ RequestBody ManagementDocumentationDTO manageDTO) throws ResourceNotFoundException {

        ManagementDocumentation manageDoc = manageService.getManageDocByUuid(manageId);
        ManagementDocumentationDTO updatedDocDTO = MyDtoConverter(manageDoc);

        return new ResponseEntity<ManagementDocumentationDTO>(updatedDocDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{manageDocId}")
    public void deleteManageDoc(@PathVariable(value = "manageDocId") UUID manageId )  throws ResourceNotFoundException {
        manageService.deleteManageDoc(manageId);
    }

    private ManagementDocumentationDTO MyDtoConverter(Optional<ManagementDocumentation> manageDoc) {
        ManagementDocumentationDTO manageDTO = modelMapper.map(manageDoc, ManagementDocumentationDTO.class);
        return manageDTO;
    }

    private ManagementDocumentation MyEntityConverter(ManagementDocumentationDTO manageDTO) {
        var manageDoc = new ManagementDocumentation();
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

        return manageDoc;
    }
}
