package tcs.interviewtracker.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
import tcs.interviewtracker.service.ManagementDocumentationService;

@RestController
@RequestMapping("management-documentations")
public class ManagementDocumentationController {

    @Autowired
    private ManagementDocumentationService manageService;

    @GetMapping("/")
    public ResponseEntity<List<ManagementDocumentationDTO>> getManageDocs(
        @RequestParam(required = false, defaultValue = "10") int pageSize,
        @RequestParam(required = false, defaultValue = "1")int offset,
        @RequestParam(required = false, defaultValue = "id")String sortBy,
        @RequestParam(required = false, defaultValue = "ascending") String orderDirection) throws ResourceNotFoundException {

        Sort SortByOrdered = orderDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
		: Sort.by(sortBy).descending();

        PageRequest manageDocRequest = PageRequest.of(offset, pageSize, SortByOrdered);
        var manageDocs = manageService.findPaginated2(manageDocRequest);
        var DTOs = new ArrayList<ManagementDocumentationDTO>();
        for(var manageDoc : manageDocs) {
            var manageDTO = ManagementDocumentationMapper.INSTANCE.MyDtoConverter(manageDoc);
            DTOs.add(manageDTO);
        }

        return new ResponseEntity<List<ManagementDocumentationDTO>>(DTOs, HttpStatus.OK);
    }

    @GetMapping("/{manageDocId}")
    public ResponseEntity<ManagementDocumentationDTO> getManageDocById(@PathVariable(value = "manageDocId") UUID manageDocId)
    throws ResourceNotFoundException {
        ManagementDocumentation manageEntity= manageService.getManageDocByUuid(manageDocId);
        ManagementDocumentationDTO manageDTO = ManagementDocumentationMapper.INSTANCE.MyDtoConverter(manageEntity);

        return new ResponseEntity<ManagementDocumentationDTO>(manageDTO, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<ManagementDocumentationDTO>  createNewDoc(@Validated @RequestBody ManagementDocumentationDTO manageDTO)
    throws ResourceAlreadyExistsException {
        var manageEntity = ManagementDocumentationMapper.INSTANCE.MyEntityConverter(manageDTO);
        ManagementDocumentationDTO savedDTO = ManagementDocumentationMapper.INSTANCE.MyDtoConverter(manageEntity);

        return new ResponseEntity<ManagementDocumentationDTO>(savedDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{manageDocId}")
    public ResponseEntity<ManagementDocumentationDTO>  updateDoc(@PathVariable(value = "mangeDocId") UUID manageId,
    @Validated @ RequestBody ManagementDocumentationDTO manageDTO) throws ResourceNotFoundException {

        ManagementDocumentation manageDoc = manageService.getManageDocByUuid(manageId);
        ManagementDocumentationDTO updatedDocDTO = ManagementDocumentationMapper.INSTANCE.MyDtoConverter(manageDoc);

        return new ResponseEntity<ManagementDocumentationDTO>(updatedDocDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{manageDocId}")
    public void deleteManageDoc(@PathVariable(value = "manageDocId") UUID manageId )  throws ResourceNotFoundException {
        manageService.deleteManageDoc(manageId);
    }
}
