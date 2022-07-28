package tcs.interviewtracker.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tcs.interviewtracker.service.ManagementDocumentationService;


@RestController
@RequestMapping("api/management-documentations")
public class ManagementDocumentationController {

    private ManagementDocumentationService manageDocService;

    public ManagementDocumentationController(ManagementDocumentationService manageDocService) {
        this.manageDocService = manageDocService;
    }

   // @GetMapping("/")
    //@ResponseBody
    //@ResponseStatus(HttpStatus.OK)





}
