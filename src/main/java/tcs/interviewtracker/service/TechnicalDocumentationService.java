package tcs.interviewtracker.service;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import tcs.interviewtracker.persistence.TechnicalDocumentation;
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
    public Page<TechnicalDocumentation> getPaginatedTechDocs(PageRequest pageRequest){
        return techDocRepository.findAll(pageRequest);

    }
    public TechnicalDocumentation save (TechnicalDocumentation tD){
        tD.setUuid(UUID.randomUUID());
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
