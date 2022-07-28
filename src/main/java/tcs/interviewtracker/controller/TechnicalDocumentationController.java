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
   //TODO pagination 
   public ResponseEntity<List<TechnicalDocumentationDTO>> getAllTechDocs(int page, int size, Sort sort){
    PageRequest pRequest = PageRequest.of(page, size, sort);
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
    public ResponseEntity<TechnicalDocumentationDTO> getProjectById(@PathVariable(value = "technicalId") Long techDocId) throws Exception{
            TechnicalDocumentation technicalDocumentation = techDocService.getById(techDocId).get();
            TechnicalDocumentationDTO technicalDocumentationDTO = convertToDto(technicalDocumentation);
        
            return new ResponseEntity<TechnicalDocumentationDTO>(technicalDocumentationDTO, HttpStatus.OK);      
      
    }

    @PutMapping("/{technicalId}")
    public ResponseEntity<TechnicalDocumentationDTO> updateTechnicalDocumentation(@PathVariable(value = "technicalId") Long techId,
     @Validated @RequestBody TechnicalDocumentation techDoc) throws Exception{
    
            TechnicalDocumentation technicalDocumentation = techDocService.getById(techId).get();
            TechnicalDocumentationDTO technicalDocumentationDTO = convertToDto(technicalDocumentation);

            return new ResponseEntity<TechnicalDocumentationDTO>(technicalDocumentationDTO, HttpStatus.OK);
    
    }

    @DeleteMapping("/{technicalId}")
    public void deleteTechnicalDocumentation(@PathVariable(value = "technicalId") Long techId ) throws Exception {
            techDocService.delete(techId);
    }

    private TechnicalDocumentationDTO convertToDto(TechnicalDocumentation technicalDocumentation) {
        TechnicalDocumentationDTO techDocDTO = modelMapper.map(technicalDocumentation, TechnicalDocumentationDTO.class);
        return techDocDTO;
    }

    private TechnicalDocumentation convertToEntity(TechnicalDocumentationDTO technicalDocumentationDTO) {
        TechnicalDocumentation techDocEntity = modelMapper.map(technicalDocumentationDTO, TechnicalDocumentation.class)
        return techDocEntity;
    }
}
