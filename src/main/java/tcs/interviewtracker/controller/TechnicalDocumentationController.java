package tcs.interviewtracker.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.OrderBy;
import javax.persistence.criteria.Order;

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

import tcs.interviewtracker.DTOs.TechnicalDocumentationDTO;
import tcs.interviewtracker.persistence.TechnicalDocumentation;
import tcs.interviewtracker.repository.TechnicalDocumentationRepository;
import tcs.interviewtracker.service.TechnicalDocumentationService;
//PageRequest -> Page tipust ad vissza
@RestController
@RequestMapping("api/technical-documentations")
public class TechnicalDocumentationController {
    @Autowired    
    private TechnicalDocumentationService techDocService;

    @Autowired
    private ModelMapper modelMapper;

   @GetMapping("/")
   public ResponseEntity<List<TechnicalDocumentationDTO>> getAllTechDocs(@RequestParam(required = false, defaultValue = "10") Integer page,
    @RequestParam(required = false, defaultValue = "10") Integer offset, @RequestParam(required = false, defaultValue = "id") String orderby, 
    @RequestParam(required = false, defaultValue = "ascending") String orderDirection){
    PageRequest pRequest;
    if(orderDirection.equals("descending")){
    pRequest = PageRequest.of(page, offset, Sort.by(orderby).descending());
    }
    else{
    pRequest = PageRequest.of(page, offset, Sort.by(orderby).ascending());
        
    }
        var techDocs = techDocService.getAllTechDocs();
        var techDocsDTOs = new ArrayList<TechnicalDocumentationDTO>();
        for(var techDoc : techDocs){
            var techDocDTO = convertToDto(techDoc);
            techDocsDTOs.add(techDocDTO);
        }
        return new ResponseEntity<List<TechnicalDocumentationDTO>>(techDocsDTOs, HttpStatus.OK);

}

    @PostMapping("/")
    public ResponseEntity<TechnicalDocumentationDTO> createNewProject(@Validated @RequestBody TechnicalDocumentationDTO techDocDTO){
        
        TechnicalDocumentation techDoc = convertToEntity(techDocDTO);
        TechnicalDocumentationDTO savedTechDocDTO = convertToDto( techDocService.save(techDoc));
        
        return new ResponseEntity<TechnicalDocumentationDTO>(savedTechDocDTO, HttpStatus.CREATED);
        
    }

    @GetMapping("/{technicalId}")
    public ResponseEntity<TechnicalDocumentationDTO> getProjectById(@PathVariable(value = "technicalId") UUID techDocId) throws Exception{
            TechnicalDocumentation technicalDocumentation = techDocService.getById(techDocId).get();
            TechnicalDocumentationDTO technicalDocumentationDTO = convertToDto(technicalDocumentation);
        
            return new ResponseEntity<TechnicalDocumentationDTO>(technicalDocumentationDTO, HttpStatus.OK);      
      
    }

    @PutMapping("/{technicalId}")
    public ResponseEntity<TechnicalDocumentationDTO> updateTechnicalDocumentation(@PathVariable(value = "technicalId") UUID techId,
     @Validated @RequestBody TechnicalDocumentation techDoc) throws Exception{
    
            TechnicalDocumentation technicalDocumentation = techDocService.getById(techId).get();
            TechnicalDocumentationDTO technicalDocumentationDTO = convertToDto(technicalDocumentation);

            return new ResponseEntity<TechnicalDocumentationDTO>(technicalDocumentationDTO, HttpStatus.OK);
    
    }

    @DeleteMapping("/{technicalId}")
    public void deleteTechnicalDocumentation(@PathVariable(value = "technicalId") UUID techId ) throws Exception {
        TechnicalDocumentation techDoc = techDocService.findByUuid(techId);    
        techDocService.delete(techId);
    }

    private TechnicalDocumentationDTO convertToDto(TechnicalDocumentation technicalDocumentation) {
        TechnicalDocumentationDTO techDocDTO = modelMapper.map(technicalDocumentation, TechnicalDocumentationDTO.class);
        return techDocDTO;
    }

    private TechnicalDocumentation convertToEntity(TechnicalDocumentationDTO technicalDocumentationDTO) {
        TechnicalDocumentation techDocEntity = modelMapper.map(technicalDocumentationDTO, TechnicalDocumentation.class);
        return techDocEntity;
    }
}
