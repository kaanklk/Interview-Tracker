package tcs.interviewtracker.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tcs.interviewtracker.DTOs.TechnicalDocumentationDTO;
import tcs.interviewtracker.persistence.TechnicalDocumentation;
import tcs.interviewtracker.repository.TechnicalDocumentationRepository;

@Service
public class TechnicalDocumentationService {

    @Autowired
    private TechnicalDocumentationRepository techDocRepository;


    public TechnicalDocumentationService(TechnicalDocumentationRepository techDocRepository) {
        this.techDocRepository = techDocRepository;
    }
    
    public TechnicalDocumentation getById(Long id){
        return techDocRepository.getReferenceById(id);
    }

    public List<TechnicalDocumentation> getAllTechDocs(){
        return techDocRepository.findAll();
    }

    public TechnicalDocumentation (){

    }
}
