package tcs.interviewtracker.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import lombok.NoArgsConstructor;
import tcs.interviewtracker.DTOs.CvDTO;
import tcs.interviewtracker.service.CvService;

@RestController
@RequestMapping("api/cv")
@NoArgsConstructor
public class CvController {

    private CvService cvService;

    @PostMapping("/")
    public CvDTO uploadCv(@RequestParam("file") MultipartFile file) {
        String fileName = cvService.storeCv(file);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/cvs/")
                .path(fileName)
                .toUriString();

        return new CvDTO(fileName, fileDownloadUri, file.getContentType(), file.getSize());
    }

}
