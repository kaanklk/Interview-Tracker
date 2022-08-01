package tcs.interviewtracker.controller;

<<<<<<< HEAD
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.OrderBy;
import javax.persistence.criteria.Order;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
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
=======
>>>>>>> origin/dev
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

<<<<<<< HEAD
import tcs.interviewtracker.DTOs.TechnicalDocumentationDTO;
import tcs.interviewtracker.exceptions.ResourceAlreadyExistsException;
import tcs.interviewtracker.exceptions.ResourceNotFoundException;
import tcs.interviewtracker.mappers.TechnicalDocumentationMapper;
import tcs.interviewtracker.persistence.TechnicalDocumentation;
=======
>>>>>>> origin/dev
import tcs.interviewtracker.service.TechnicalDocumentationService;

//PageRequest -> Page tipust ad vissza
@RestController
@RequestMapping("/technical-documentations")
public class TechnicalDocumentationController {
<<<<<<< HEAD
    @Autowired    
    private TechnicalDocumentationService techDocService;

   @GetMapping
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
        var techDocs = techDocService.getPaginatedTechDocs(pRequest);
        var techDocsDTOs = new ArrayList<TechnicalDocumentationDTO>();
        for(var techDoc : techDocs){
            var techDocDTO = TechnicalDocumentationMapper.INSTANCE.convertToDTO(techDoc);
            techDocsDTOs.add(techDocDTO);
        }
        return new ResponseEntity<List<TechnicalDocumentationDTO>>(techDocsDTOs, HttpStatus.OK);

}

    @PostMapping("/")
    public ResponseEntity<TechnicalDocumentationDTO> createNewProject(@Validated @RequestBody TechnicalDocumentationDTO techDocDTO) throws ResourceAlreadyExistsException{
        
        TechnicalDocumentation techDoc = TechnicalDocumentationMapper.INSTANCE.convertToEntity(techDocDTO);
        TechnicalDocumentationDTO savedTechDocDTO = TechnicalDocumentationMapper.INSTANCE.convertToDTO( techDocService.save(techDoc));
        
        return new ResponseEntity<TechnicalDocumentationDTO>(savedTechDocDTO, HttpStatus.CREATED);
        
    }

    @GetMapping("/{technicalId}")
    public ResponseEntity<TechnicalDocumentationDTO> getProjectById(@PathVariable(value = "technicalId") UUID techDocId) throws ResourceNotFoundException {
            TechnicalDocumentation technicalDocumentation = techDocService.getById(techDocId).get();
            TechnicalDocumentationDTO technicalDocumentationDTO =TechnicalDocumentationMapper.INSTANCE.convertToDTO(technicalDocumentation);
        
            return new ResponseEntity<TechnicalDocumentationDTO>(technicalDocumentationDTO, HttpStatus.OK);      
      
    }

    @PutMapping("/{technicalId}")
    public ResponseEntity<TechnicalDocumentationDTO> updateTechnicalDocumentation(@PathVariable(value = "technicalId") UUID techId,
     @Validated @RequestBody TechnicalDocumentation techDoc) throws ResourceNotFoundException{
    
            TechnicalDocumentation technicalDocumentation = techDocService.getById(techId).get();
            TechnicalDocumentationDTO technicalDocumentationDTO = TechnicalDocumentationMapper.INSTANCE.convertToDTO(technicalDocumentation);

            return new ResponseEntity<TechnicalDocumentationDTO>(technicalDocumentationDTO, HttpStatus.OK);
    
    }

    @DeleteMapping("/{technicalId}")
    public void deleteTechnicalDocumentation(@PathVariable(value = "technicalId") UUID techId ) throws Exception {
        TechnicalDocumentation techDoc = techDocService.getById(techId).get();

        techDocService.delete(techDoc);
    }

=======

    private TechnicalDocumentationService techDocService;
    /*
     * @GetMapping("/")
     * public ResponseEntity<List<TechnicalDocumentationDTO>>
     * getAllTechDocs(PageRequest pageRequest){
     * try{
     * //TODO átalakitani
     * return ResponseEntity.ok().body(techDocService.getAllTechDocs(pageRequest));
     * }catch(Exception e){
     * return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
     * }
     * }
     * 
     * @PostMapping("/")
     * public TechnicalDocumentation createNewProject(@Validated @RequestBody
     * TechnicalDocumentation techDoc){
     * //TODO átalakitani
     * return techDocService.save(techDoc);
     * }
     * 
     * @GetMapping("/{technicalId}")
     * public ResponseEntity<TechnicalDocumentationDTO>
     * getProjectById(@PathVariable(value = "technicalId") Long techDocId) throws
     * Exception{
     * try{
     * //TODO átalakitani
     * return ResponseEntity.ok().body(techDocService.getById(techDocId).get());
     * }catch(Exception e){
     * return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
     * }
     * 
     * }
     * 
     * @PutMapping("/{technicalId}")
     * public ResponseEntity<TechnicalDocumentation>
     * updateTechnicalDocumentation(@PathVariable(value = "technicalId") Long
     * techId, @Validated @RequestBody TechnicalDocumentation techDoc) throws
     * Exception{
     * try{
     * TechnicalDocumentation technicalDocumentation =
     * techDocService.getById(techId);
     * 
     * return ResponseEntity.ok().body(techDocService.update(techId, techDoc));
     * }catch(Exception e){
     * return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
     * }
     * }
     * 
     * @DeleteMapping("/{technicalId}")
     * public ResponseEntity<Object>
     * deleteTechnicalDocumentation(@PathVariable(value = "technicalId") Long techId
     * ) throws Exception {
     * try{
     * if(!techDocService.getById(techId).isPresent()){
     * return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
     * }
     * techDocService.delete(techId);
     * 
     * }catch(Exception e){
     * return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
     * }
     * //TODO utánanézni
     * return ResponseEntity.ok();
     * }
     */
>>>>>>> origin/dev
}
