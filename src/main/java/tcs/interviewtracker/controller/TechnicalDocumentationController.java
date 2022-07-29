package tcs.interviewtracker.controller;

import java.util.List;

import org.springframework.data.domain.PageRequest;
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
    
    private TechnicalDocumentationService techDocService;
/* 
   @GetMapping("/")
   public ResponseEntity<List<TechnicalDocumentationDTO>> getAllTechDocs(PageRequest pageRequest){
        try{
            //TODO átalakitani
            return ResponseEntity.ok().body(techDocService.getAllTechDocs(pageRequest));
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/")
    public TechnicalDocumentation createNewProject(@Validated @RequestBody TechnicalDocumentation techDoc){
        //TODO átalakitani
        return techDocService.save(techDoc);
    }

    @GetMapping("/{technicalId}")
    public ResponseEntity<TechnicalDocumentationDTO> getProjectById(@PathVariable(value = "technicalId") Long techDocId) throws Exception{
        try{
            //TODO átalakitani
            return ResponseEntity.ok().body(techDocService.getById(techDocId).get());
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
      
    }

    @PutMapping("/{technicalId}")
    public ResponseEntity<TechnicalDocumentation> updateTechnicalDocumentation(@PathVariable(value = "technicalId") Long techId, @Validated @RequestBody TechnicalDocumentation techDoc) throws Exception{
        try{
            TechnicalDocumentation technicalDocumentation = techDocService.getById(techId);
        
            return ResponseEntity.ok().body(techDocService.update(techId, techDoc));
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{technicalId}")
    public ResponseEntity<Object> deleteTechnicalDocumentation(@PathVariable(value = "technicalId") Long techId ) throws Exception {
        try{
            if(!techDocService.getById(techId).isPresent()){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            techDocService.delete(techId);

        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        //TODO utánanézni 
        return ResponseEntity.ok(); 
    }
*/
}
