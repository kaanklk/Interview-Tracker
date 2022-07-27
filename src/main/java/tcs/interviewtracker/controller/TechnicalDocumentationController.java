package tcs.interviewtracker.controller;

import java.util.List;

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

@RestController
@RequestMapping("api/technical-documentations")
public class TechnicalDocumentationController {
    
    private TechnicalDocumentationService techDocService;

  //  @GetMapping("/")
  //  public ResponseEntity<List<TechnicalDocumentationDTO>> getAllTechDocs(){
 //       try{
    //        return ResponseEntity.ok().body()
//        }catch(Exception e){
////            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
  //      }
 //   }

    @PostMapping("/")
    public TechnicalDocumentation createNewProject(@Validated @RequestBody TechnicalDocumentation techDoc){
        
        return techDocService.save(techDoc);
    }

    @GetMapping("/{technicalId}")
    public ResponseEntity<TechnicalDocumentation> getProjectById(@PathVariable(value = "technicalId") Long techDocId) throws Exception{
        TechnicalDocumentation techDoc = techDocRepository.findById(techDocId)
        .orElseThrow(() -> new Exception("Technical Documentation is not found this id! " + techDocId));
        return ResponseEntity.ok().body(techDoc);
    }

    @PutMapping("/{technicalId}")
    public ResponseEntity<TechnicalDocumentation> updateTechnicalDocumentation(@PathVariable(value = "technicalId") Long techId, @Validated @RequestBody TechnicalDocumentation techDoc) throws Exception{
        TechnicalDocumentation technicalDocumentation = techDocRepository.findById(techId)
        .orElseThrow(() -> new Exception("Technical documentation is not found with this id: "+ techId));

        technicalDocumentation.setCandidate(techDoc.getCandidate());
        technicalDocumentation.setDate(techDoc.getDate());
        technicalDocumentation.setDesignationOne(techDoc.getDesignationOne());
        technicalDocumentation.setDesignationTwo(techDoc.getDesignationTwo());
        technicalDocumentation.setDuration(techDoc.getDuration());
        technicalDocumentation.setInterviewerOne(techDoc.getInterviewerOne());
        technicalDocumentation.setInterviewerTwo(techDoc.getInterviewerTwo());
        technicalDocumentation.setIsReccomended(techDoc.getIsReccomended());
        technicalDocumentation.setLastComments(techDoc.getLastComments());
        technicalDocumentation.setRoleExperience(techDoc.getRoleExperience());

        technicalDocumentation.setTechSkillComment1(techDoc.getTechSkillComment1());
        technicalDocumentation.setTechSkillComment2(techDoc.getTechSkillComment2());
        technicalDocumentation.setTechSkillComment3(techDoc.getTechSkillComment3());
        technicalDocumentation.setTechSkillComment4(techDoc.getTechSkillComment4());
        
        technicalDocumentation.setTechnicalSkills1(techDoc.getTechnicalSkills1());
        technicalDocumentation.setTechnicalSkills2(techDoc.getTechnicalSkills2());
        technicalDocumentation.setTechnicalSkills3(techDoc.getTechnicalSkills3());
        technicalDocumentation.setTechnicalSkills4(techDoc.getTechnicalSkills4());

        technicalDocumentation.setTotalExperience(techDoc.getTotalExperience());
        technicalDocumentation.setUnderstandingComment(techDoc.getUnderstandingComment());
        technicalDocumentation.setUnderstandingOfRole(techDoc.getUnderstandingOfRole());


        TechnicalDocumentation finalTechDoc = techDocRepository.save(technicalDocumentation);
        return ResponseEntity.ok(finalTechDoc);
    }

    @DeleteMapping("/{technicalId}")
    public ResponseEntity<TechnicalDocumentation> deleteTechnicalDocumentation(@PathVariable(value = "technicalId") Long techId ) throws Exception {
        TechnicalDocumentation technicalDocumentation = techDocRepository.findById(techId)
        .orElseThrow(()-> new Exception("Technincal documentation is not found with this id: "+techId));
        techDocRepository.delete(technicalDocumentation);
        return ResponseEntity.ok(technicalDocumentation); 
    }

}
