package tcs.interviewtracker.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
import tcs.interviewtracker.properties.TechnicalSkills;
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

    @GetMapping("/")
    public ResponseEntity<List<TechnicalDocumentationDTO>> getAllTechDocs(
            @RequestParam(required = false, defaultValue = "10") Integer page,
            @RequestParam(required = false, defaultValue = "0") Integer offset,
            @RequestParam(required = false, defaultValue = "uuid") String orderby,
            @RequestParam(required = false, defaultValue = "ascending") String orderDirection)
            throws BadRequestException {

        Pageable pageable;
        var fieldsOfTechDocDTO = TechnicalDocumentationDTO.class.getDeclaredFields();
        boolean existingOrderBy = false;
        for (Field f : fieldsOfTechDocDTO) {
            if (f.getName().equals(orderby)) {
                existingOrderBy = true;
                break;
            }
        }
        if (existingOrderBy == false) {
            throw new BadRequestException(
                    "Wrong query parameter value (orderby). No such field in Technical documentations " + orderby);
        }

        if (orderDirection.equals("descending")) {
            pageable = PageRequest.of(offset, page, Sort.by(orderby).descending());
        } else {
            pageable = PageRequest.of(offset, page, Sort.by(orderby).ascending());

        }
        var techDocs = techDocService.getPaginatedTechDocs(pageable).stream().map(this::convertToDTO)
                .collect(Collectors.toList());

        return new ResponseEntity<List<TechnicalDocumentationDTO>>(techDocs, HttpStatus.OK);

    }

    @PostMapping("/")
    public ResponseEntity<TechnicalDocumentationDTO> createNewTechnicalDocumentation(
          @RequestBody TechnicalDocumentationDTO techDocDTO)
            throws ResourceAlreadyExistsException, ResourceNotFoundException {

        TechnicalDocumentation techDoc = convertToEntity(techDocDTO);
        TechnicalDocumentationDTO savedTechDocDTO = convertToDTO(techDocService.save(techDoc));

        return new ResponseEntity<TechnicalDocumentationDTO>(savedTechDocDTO, HttpStatus.CREATED);

    }

    @GetMapping("/{technicalId}")
    public ResponseEntity<TechnicalDocumentationDTO> getTechnicalDocumentationById(@PathVariable(value = "technicalId") UUID techDocId)
            throws ResourceNotFoundException {
        TechnicalDocumentation technicalDocumentation = techDocService.getById(techDocId).get();
        TechnicalDocumentationDTO technicalDocumentationDTO = convertToDTO(technicalDocumentation);

        return new ResponseEntity<TechnicalDocumentationDTO>(technicalDocumentationDTO, HttpStatus.OK);

    }

    @PutMapping("/{technicalId}")
    public ResponseEntity<TechnicalDocumentationDTO> updateTechnicalDocumentation(
            @PathVariable(value = "technicalId") UUID techId,
            @Validated @RequestBody TechnicalDocumentationDTO techDocDTO) throws ResourceNotFoundException {

        TechnicalDocumentation techDoc = convertToEntity(techDocDTO);
        TechnicalDocumentationDTO technicalDocumentationDTO = convertToDTO(techDocService.update(techDoc));
        return new ResponseEntity<TechnicalDocumentationDTO>(technicalDocumentationDTO, HttpStatus.OK);

    }

    @DeleteMapping("/{technicalId}")
    public void deleteTechnicalDocumentation(@PathVariable(value = "technicalId") UUID techId) throws Exception {

        techDocService.delete(techId);
    }


    private TechnicalDocumentation convertToEntity(TechnicalDocumentationDTO dto) throws ResourceNotFoundException {
        var techDoc = new TechnicalDocumentation();
        if (dto.getUuid() == null) {
            techDoc.setUuid(UUID.randomUUID());
        } else {
            techDoc.setUuid(dto.getUuid());
            if (techDocService.getById(dto.getUuid()).get().getId() != null) {
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

        if(dto.getSkills().size()==1){
            techDoc.setTechSkillComment1(dto.getSkills().get(0).getSkillName());
            techDoc.setTechnicalSkills1(dto.getSkills().get(0).getSkillLevel());
        
        }else if(dto.getSkills().size()==2){

            techDoc.setTechSkillComment1(dto.getSkills().get(0).getSkillName());
            techDoc.setTechnicalSkills1(dto.getSkills().get(0).getSkillLevel());
            techDoc.setTechSkillComment2(dto.getSkills().get(1).getSkillName());
            techDoc.setTechnicalSkills2(dto.getSkills().get(1).getSkillLevel());
        
        }else if(dto.getSkills().size()==3){
            techDoc.setTechSkillComment1(dto.getSkills().get(0).getSkillName());
            techDoc.setTechnicalSkills1(dto.getSkills().get(0).getSkillLevel());
            techDoc.setTechSkillComment2(dto.getSkills().get(1).getSkillName());
            techDoc.setTechnicalSkills2(dto.getSkills().get(1).getSkillLevel());
            techDoc.setTechSkillComment3(dto.getSkills().get(2).getSkillName());
            techDoc.setTechnicalSkills3(dto.getSkills().get(2).getSkillLevel());
            
        }else if(dto.getSkills().size() >= 4){

            techDoc.setTechSkillComment1(dto.getSkills().get(0).getSkillName());
            techDoc.setTechnicalSkills1(dto.getSkills().get(0).getSkillLevel());
            techDoc.setTechSkillComment2(dto.getSkills().get(1).getSkillName());
            techDoc.setTechnicalSkills2(dto.getSkills().get(1).getSkillLevel());
            techDoc.setTechSkillComment3(dto.getSkills().get(2).getSkillName());
            techDoc.setTechnicalSkills3(dto.getSkills().get(2).getSkillLevel());
            techDoc.setTechSkillComment4(dto.getSkills().get(3).getSkillName());
            techDoc.setTechnicalSkills4(dto.getSkills().get(3).getSkillLevel());
            
        }

        techDoc.setRoleExperience(dto.getRoleExperience());
        techDoc.setTotalExperience(dto.getTotalExperience());
        techDoc.setUnderstandingComment(dto.getUnderstandingComment());
        techDoc.setUnderstandingOfRole(dto.getUnderstandingOfRole());

        return techDoc;
    }
    
     private TechnicalDocumentationDTO convertToDTO(TechnicalDocumentation
     techDoc){
     var dto = new TechnicalDocumentationDTO();
     dto.setUuid(techDoc.getUuid());
     dto.setDate(techDoc.getDate());
     dto.setDesignationOne(techDoc.getDesignationOne());
     dto.setDesignationTwo(techDoc.getDesignationTwo());
     dto.setCandidateUuid(techDoc.getCandidate().getUuid());
     dto.setDuration(techDoc.getDuration());
     
     dto.setInterviewerOneUUID(techDoc.getInterviewerOne().getUuid());
     dto.setInterviewerTwoUUID(techDoc.getInterviewerTwo().getUuid());
     
     if(techDoc.getTechSkillComment1() != null && techDoc.getTechnicalSkills1() != null){
        TechnicalSkills techSkill = new TechnicalSkills(techDoc.getTechSkillComment1(),techDoc.getTechnicalSkills1());
        dto.getSkills().add(techSkill);
     }
     if(techDoc.getTechSkillComment2() != null && techDoc.getTechnicalSkills2() != null){
        TechnicalSkills techSkill = new TechnicalSkills(techDoc.getTechSkillComment2(),techDoc.getTechnicalSkills2());
        dto.getSkills().add(techSkill);
     }
     if(techDoc.getTechSkillComment3() != null && techDoc.getTechnicalSkills3() != null){
        TechnicalSkills techSkill = new TechnicalSkills(techDoc.getTechSkillComment3(),techDoc.getTechnicalSkills3());
        dto.getSkills().add(techSkill);
     }
     if(techDoc.getTechSkillComment4() != null && techDoc.getTechnicalSkills4() != null){
        TechnicalSkills techSkill = new TechnicalSkills(techDoc.getTechSkillComment4(),techDoc.getTechnicalSkills4());
        dto.getSkills().add(techSkill);
     }
     
     dto.setIsReccomended(techDoc.getIsReccomended());
     dto.setLastComments(techDoc.getLastComments());
     
     
     dto.setRoleExperience(techDoc.getRoleExperience());
     dto.setTotalExperience(techDoc.getTotalExperience());
     dto.setUnderstandingComment(techDoc.getUnderstandingComment());
     dto.setUnderstandingOfRole(techDoc.getUnderstandingOfRole());
     
     
     return dto;
     
     }
     
     
}
