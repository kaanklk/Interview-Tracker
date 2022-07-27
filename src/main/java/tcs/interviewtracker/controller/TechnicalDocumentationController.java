package tcs.interviewtracker.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tcs.interviewtracker.persistence.TechnicalDocumentation;
import tcs.interviewtracker.repository.TechnicalDocumentationRepository;
import tcs.interviewtracker.service.TechnicalDocumentationService;

@RestController
@RequestMapping("api/technical-documentations")
public class TechnicalDocumentationController {
    
    private TechnicalDocumentationService techDocService;
    private TechnicalDocumentationRepository techDocRepository;

    @GetMapping("/")
    public List<TechnicalDocumentation> getAllTechDocs(){
        return techDocService.getAllTechDocs();
    }

    @PostMapping("/")
    public TechnicalDocumentation createNewProject(@Validated @RequestBody TechnicalDocumentation techDoc){
        return techDocRepository.save(techDoc);
    }

    @GetMapping("/{technicalId}")
    public ResponseEntity<TechnicalDocumentation> getProjectById(@PathVariable(value = "technicalId") Long techDocId) throws Exception{
        TechnicalDocumentation techDoc = techDocRepository.findById(techDocId)
        .orElseThrow(() -> new Exception("Technical Documentation is not found this id! " + techDocId));
        return ResponseEntity.ok().body(techDoc);
    }

    
}
