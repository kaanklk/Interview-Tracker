package tcs.interviewtracker.controller;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import tcs.interviewtracker.DTOs.CvDTO;
import tcs.interviewtracker.persistence.Candidate;
import tcs.interviewtracker.service.CandidateService;
import tcs.interviewtracker.service.CvService;

@RestController
@RequestMapping("api/cv")
public class CvController {

    private static final Logger logger = LoggerFactory.getLogger(CvController.class);

    @Autowired
    private CvService cvService;
    @Autowired
    private CandidateService candidateService;

    @PostMapping("/")
    public CvDTO uploadCv(@RequestParam("file") MultipartFile file, @RequestParam("candidateId") Long candidateId)
            throws Exception {
        String fileName = cvService.storeCv(file, candidateId);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/cvs/")
                .path(fileName)
                .toUriString();

        return new CvDTO(fileName, fileDownloadUri, file.getContentType(), file.getSize());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resource> getCvByCandidateId(@PathVariable(value = "id") UUID uuid,
            HttpServletRequest request) throws Exception {
        Candidate candidate = candidateService.getByUuid(uuid);
        String candidateId = candidate.getId().toString();
        Resource resource = cvService.loadCvAsResource(candidateId);

        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException e) {
            logger.info("Can not determine filetype");
        }

        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .body(resource);
    }

}
