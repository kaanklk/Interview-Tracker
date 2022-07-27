package tcs.interviewtracker.controller;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.Converters.Converter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tcs.interviewtracker.DTOs.ManagementDocumentationDTO;
import tcs.interviewtracker.persistence.ManagementDocumentation;
import tcs.interviewtracker.service.ManagementDocumentationService;

@RestController
@RequestMapping("api/management-documentations")
public class ManagementDocumentationController {

    private ManagementDocumentation manageDoc;
    private ManagementDocumentationService manageDocService;
    private ManagementDocumentationDTO manageDocDTO;



    //Converter<ManagementDocumentation, UUID> uuidConverter = new AbstractConverter<ManagementDocumentation, UUID>() {
      //  protected UUID convert(ManagementDocumentation source) {
        //    return UUID.fromString(source.getId());
      //  }
    //};

   // private PropertyMap<ManagementDocumentation, ManagementDocumentationDTO> manageDocMap = new PropertyMap<ManagementDocumentation, ManagementDocumentationDTO>() {
     //   protected void configure() {
       //     try {
         //       using(uuidConverter).map(source).setId(null);
           //} catch (Exception ex) {
             //   System.out.println("Error.");
           //}
       //}
    //};


    private ModelMapper manageDocToManageDocDTOMapper = new ModelMapper();



     private ManagementDocumentationDTO convertToDto(ManagementDocumentation manageDoc) {

        ManagementDocumentationDTO manageDocDTO = manageDocToManageDocDTOMapper.map(manageDoc, ManagementDocumentationDTO.class);

        return manageDocDTO;
    }

}
