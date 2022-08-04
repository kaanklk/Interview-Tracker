package tcs.interviewtracker.controller;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.persistence.OrderBy;
import javax.persistence.criteria.Order;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
import tcs.interviewtracker.exceptions.BadRequestException;
import tcs.interviewtracker.exceptions.ResourceAlreadyExistsException;
import tcs.interviewtracker.exceptions.ResourceNotFoundException;
import tcs.interviewtracker.persistence.Candidate;
import tcs.interviewtracker.persistence.TechnicalDocumentation;
import tcs.interviewtracker.persistence.User;
import tcs.interviewtracker.service.TechnicalDocumentationService;

//PageRequest -> Page tipust ad vissza
import tcs.interviewtracker.service.CandidateService;
import tcs.interviewtracker.service.TechnicalDocumentationService;
import tcs.interviewtracker.service.UserService;

@RestController
@RequestMapping("/technical-documentations")
public class TechnicalDocumentationController {
    @Autowired    
    private TechnicalDocumentationService techDocService;

    @Autowired 
    private CandidateService candidateService;

    @Autowired
    @Qualifier("technicalDocumentationMapper")
    private ModelMapper technicalDocumentationMapper;

    @Autowired
    private UserService userService;
    
    Converter<UUID, User> userConverter = new AbstractConverter<UUID,User>() {
        protected User convert(UUID uuid) {
             try {

                return userService.getUserById(uuid);
            
            } catch (ResourceNotFoundException e) {
            
                return null;
            
            }
               
            
        }
    };

    Converter<UUID, Candidate> candidateConverter = new AbstractConverter<UUID,Candidate>() {
        protected Candidate convert(UUID uuid) {
                return candidateService.getByUuid(uuid);
            
        }
    };
    

    public TechnicalDocumentationController(ModelMapper modelMapper){
        this.technicalDocumentationMapper = modelMapper;
        this.technicalDocumentationMapper.addConverter(userConverter);
        this.technicalDocumentationMapper.addConverter(candidateConverter);
        
    }




   @GetMapping("/")
   public ResponseEntity<List<TechnicalDocumentationDTO>> getAllTechDocs(@RequestParam(required = false, defaultValue = "10") Integer page,
    @RequestParam(required = false, defaultValue = "0") Integer offset, @RequestParam(required = false, defaultValue = "uuid") String orderby, 
    @RequestParam(required = false, defaultValue = "ascending") String orderDirection) throws BadRequestException{
     
    Pageable pageable;
    var fieldsOfTechDocDTO = TechnicalDocumentationDTO.class.getDeclaredFields();
    boolean existingOrderBy = false;
    for(Field f: fieldsOfTechDocDTO){
        if(f.getName().equals(orderby)){
            existingOrderBy = true;
            break;
        }
    }
    if(existingOrderBy == false){
        throw new BadRequestException("Wrong query parameter value (orderby). No such field in Technical documentations "+orderby);
    }

    if(orderDirection.equals("descending")){
    pageable = PageRequest.of(offset,page, Sort.by(orderby).descending());
    }
    else{
    pageable = PageRequest.of(offset,page, Sort.by(orderby).ascending());
        
    }   
        var techDocs = techDocService.getPaginatedTechDocs(pageable).stream().map(this::convertToDTO).collect(Collectors.toList());

        return new ResponseEntity<List<TechnicalDocumentationDTO>>(techDocs, HttpStatus.OK);
      
}

    @PostMapping("/")
    public ResponseEntity<TechnicalDocumentationDTO> createNewProject(@Validated @RequestBody TechnicalDocumentationDTO techDocDTO) throws ResourceAlreadyExistsException, ResourceNotFoundException{
        
        TechnicalDocumentation techDoc = convertToEntity(techDocDTO);
        TechnicalDocumentationDTO savedTechDocDTO = convertToDTO( techDocService.save(techDoc));
        
        return new ResponseEntity<TechnicalDocumentationDTO>(savedTechDocDTO, HttpStatus.CREATED);

    }

    @GetMapping("/{technicalId}")
    public ResponseEntity<TechnicalDocumentationDTO> getProjectById(@PathVariable(value = "technicalId") UUID techDocId) throws ResourceNotFoundException {
            TechnicalDocumentation technicalDocumentation = techDocService.getById(techDocId).get();
            TechnicalDocumentationDTO technicalDocumentationDTO = convertToDTO(technicalDocumentation);
        
            return new ResponseEntity<TechnicalDocumentationDTO>(technicalDocumentationDTO, HttpStatus.OK);      
      
    }

    @PutMapping("/{technicalId}")
    public ResponseEntity<TechnicalDocumentationDTO> updateTechnicalDocumentation(@PathVariable(value = "technicalId") UUID techId,
     @Validated @RequestBody TechnicalDocumentationDTO techDocDTO) throws ResourceNotFoundException{

            TechnicalDocumentation techDoc = convertToEntity(techDocDTO);
            TechnicalDocumentationDTO technicalDocumentationDTO = convertToDTO(techDocService.update(techDoc));
            return new ResponseEntity<TechnicalDocumentationDTO>(technicalDocumentationDTO, HttpStatus.OK);

    }

    @DeleteMapping("/{technicalId}")
    public void deleteTechnicalDocumentation(@PathVariable(value = "technicalId") UUID techId ) throws Exception {
        
        techDocService.delete(techId);
    }

    private TechnicalDocumentationDTO convertToDTO(TechnicalDocumentation techDoc){
        TechnicalDocumentationDTO techDocDTO = technicalDocumentationMapper.map(techDoc, TechnicalDocumentationDTO.class);
        return techDocDTO;
    }

    private TechnicalDocumentation convertToEntity(TechnicalDocumentationDTO dto) throws ResourceNotFoundException{
        var techDoc = new TechnicalDocumentation();
        if(dto.getUuid() == null){
        techDoc.setUuid(UUID.randomUUID());    
        }
        else{
        techDoc.setUuid(dto.getUuid());
         if(techDocService.getById(dto.getUuid()).get().getId()!= null){
            techDoc.setId(techDocService.getById(dto.getUuid()).get().getId());
        }
        }
        techDoc.setCandidate(candidateService.getByUuid(dto.getCandidateUuid()));
        techDoc.setDate(dto.getDate());
        techDoc.setDesignationOne(dto.getDesignationOne());
        techDoc.setDesignationTwo(dto.getDesignationTwo());
        techDoc.setDuration(dto.getDuration());
       
        techDoc.setInterviewerOne(userService.getUserById(dto.getInterviewerOneUUID()));
        techDoc.setInterviewerTwo(userService.getUserById(dto.getInterviewerTwoUUID()));

        techDoc.setIsReccomended(dto.getIsReccomended());
        techDoc.setLastComments(dto.getLastComments());
        techDoc.setTechSkillComment1(dto.getTechSkillComment1());
        techDoc.setTechSkillComment2(dto.getTechSkillComment2());
        techDoc.setTechSkillComment3(dto.getTechSkillComment3());
        techDoc.setTechSkillComment4(dto.getTechSkillComment4());
        techDoc.setTechnicalSkills1(dto.getTechnicalSkills1());
        techDoc.setTechnicalSkills2(dto.getTechnicalSkills2());
        techDoc.setTechnicalSkills3(dto.getTechnicalSkills3());
        techDoc.setTechnicalSkills4(dto.getTechnicalSkills4());
        techDoc.setRoleExperience(dto.getRoleExperience());
        techDoc.setTotalExperience(dto.getTotalExperience());
        techDoc.setUnderstandingComment(dto.getUnderstandingComment());
        techDoc.setUnderstandingOfRole(dto.getUnderstandingOfRole());

        return techDoc;
    }
/*
    private TechnicalDocumentationDTO convertToDto(TechnicalDocumentation techDoc){
        var dto = new TechnicalDocumentationDTO();
        dto.setUuid(techDoc.getUuid());
        dto.setDate(techDoc.getDate());
        dto.setDesignationOne(techDoc.getDesignationOne());
        dto.setDesignationTwo(techDoc.getDesignationTwo());
        dto.setCandidateUuid(techDoc.getCandidate().getUuid());
        dto.setDuration(techDoc.getDuration());

        dto.setInterviewerOneUUID(techDoc.getInterviewerOne().getUuid());
        dto.setInterviewerTwoUUID(techDoc.getInterviewerTwo().getUuid());
        
        dto.setIsReccomended(techDoc.getIsReccomended());
        dto.setLastComments(techDoc.getLastComments());
        dto.setTechSkillComment1(techDoc.getTechSkillComment1());
        dto.setTechSkillComment2(techDoc.getTechSkillComment2());
        dto.setTechSkillComment3(techDoc.getTechSkillComment3());
        dto.setTechSkillComment4(techDoc.getTechSkillComment4());
        dto.setTechnicalSkills1(techDoc.getTechnicalSkills1());
        dto.setTechnicalSkills2(techDoc.getTechnicalSkills2());
        dto.setTechnicalSkills3(techDoc.getTechnicalSkills3());
        dto.setTechnicalSkills4(techDoc.getTechnicalSkills4());
        dto.setRoleExperience(techDoc.getRoleExperience());
        dto.setTotalExperience(techDoc.getTotalExperience());
        dto.setUnderstandingComment(techDoc.getUnderstandingComment());
        dto.setUnderstandingOfRole(techDoc.getUnderstandingOfRole());
        

        return dto;
            
    }

 */
}
