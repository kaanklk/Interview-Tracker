package tcs.interviewtracker.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tcs.interviewtracker.service.TechnicalDocumentationService;

//PageRequest -> Page tipust ad vissza
@RestController
@RequestMapping("api/technical-documentations")
public class TechnicalDocumentationController {

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
}
