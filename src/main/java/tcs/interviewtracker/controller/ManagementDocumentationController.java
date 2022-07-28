package tcs.interviewtracker.controller;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import tcs.interviewtracker.DTOs.ManagementDocumentationDTO;
import tcs.interviewtracker.persistence.ManagementDocumentation;
import tcs.interviewtracker.service.ManagementDocumentationService;

@RestController
@RequestMapping("api/management-documentations")
public class ManagementDocumentationController {

    @Autowired
    private ManagementDocumentationService manageService;

    @Autowired
    private ModelMapper modelMapper;

    private ManagementDocumentationDTO DtoConverter(ManagementDocumentation managementDoc) {
        ManagementDocumentationDTO dto = modelMapper.map(managementDoc, ManagementDocumentationDTO.class) ;
        return dto;
    }

    private ManagementDocumentation EntityConverter(ManagementDocumentationDTO managementDocDTO) {
        ManagementDocumentation entity = modelMapper.map(managementDocDTO, ManagementDocumentation.class) ;
        return entity;
    }


    @GetMapping("/")
    public ResponseEntity<List<ManagementDocumentationDTO>> getManageDocs(int pageSize, int offset,  String sortBy) {
        PageRequest manageDocRequest = PageRequest.of(offset, pageSize, Sort.by(sortBy).ascending());
        var manageDocs = manageService.getAllManageDocs();
        var DTOs = new ArrayList<ManagementDocumentationDTO>();
        for(var manageDoc : manageDocs) {
            var manageDTO = DtoConverter(manageDoc);
            DTOs.add(manageDTO);
        }

        return new ResponseEntity<List<ManagementDocumentationDTO>>(DTOs, HttpStatus.OK);
    }

    @GetMapping("/{manageDocId}")
    public ResponseEntity<ManagementDocumentationDTO> getManageDocById(@PathVariable(value = "manageDocId") Long manageDocId) {
        ManagementDocumentation manageEntity= manageService.getManageDocById(manageDocId);
        ManagementDocumentationDTO manageDTO = DtoConverter(manageEntity);

        return new ResponseEntity<ManagementDocumentationDTO>(manageDTO, HttpStatus.OK);

    }

    @PostMapping("/")
    public ResponseEntity<ManagementDocumentationDTO>  createNewDoc(@Validated @RequestBody ManagementDocumentationDTO manageDTO) {
        var manageEntity = EntityConverter(manageDTO);
        ManagementDocumentationDTO savedDTO = DtoConverter(manageService.saveManageDoc(manageEntity));

        return new ResponseEntity<ManagementDocumentationDTO>(savedDTO, HttpStatus.OK);
    }

    @PutMapping("/{manageDocId}")
    public ResponseEntity<ManagementDocumentationDTO>  updateDoc(@PathVariable(value = "mangeDocId") Long manageId,
    @Validated @ RequestBody ManagementDocumentationDTO manageDTO) {

        ManagementDocumentation manageDoc = manageService.getManageDocById(manageId);
        ManagementDocumentationDTO updatedDocDTO = DtoConverter(manageDoc);

        return new ResponseEntity<ManagementDocumentationDTO>(updatedDocDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{manageDocId}")
    public void deleteTechnicalDocumentation(@PathVariable(value = "manageDocId") Long manageId )  {
        manageService.deleteManageDoc(manageId);
    }
}
