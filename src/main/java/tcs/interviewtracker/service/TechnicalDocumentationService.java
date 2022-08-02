package tcs.interviewtracker.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import tcs.interviewtracker.persistence.Candidate;
import tcs.interviewtracker.persistence.Person;
import tcs.interviewtracker.persistence.Position;
import tcs.interviewtracker.persistence.TechnicalDocumentation;
import tcs.interviewtracker.persistence.User;
import tcs.interviewtracker.repository.TechnicalDocumentationRepository;

@Service
public class TechnicalDocumentationService {

    @Autowired
    private TechnicalDocumentationRepository techDocRepository;


    public TechnicalDocumentationService(TechnicalDocumentationRepository techDocRepository) {
        this.techDocRepository = techDocRepository;
    }
    
    public Optional<TechnicalDocumentation> getById(UUID id){
        return techDocRepository.findByUuid(id);
    }

    public List<TechnicalDocumentation> getAllTechDocs(){
        
        return techDocRepository.findAll();
    }
    public Page<TechnicalDocumentation> getPaginatedTechDocs(Pageable pageRequest){
        
    var res = techDocRepository.findAll(pageRequest);    
    return  res;

    }

    public TechnicalDocumentation save (TechnicalDocumentation tD){
        tD.setUuid(UUID.randomUUID());
        return techDocRepository.save(tD);
    }
    public TechnicalDocumentation update(TechnicalDocumentation tD){
        return techDocRepository.save(tD);
    }

    
    public TechnicalDocumentation update(Long techId, TechnicalDocumentation techDoc){

        TechnicalDocumentation finalTechDoc = techDocRepository.save(techDoc);
        return finalTechDoc;
    }

    public void delete(TechnicalDocumentation techD){
        techDocRepository.delete(techD);
        
    }
}
