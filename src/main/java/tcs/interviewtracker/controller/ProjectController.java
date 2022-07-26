package tcs.interviewtracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import tcs.interviewtracker.service.ProjectService;

@RestController
@RequestMapping("api/projects")
public class ProjectController {

    private ProjectService projectService;

    public ProjectController(@Autowired ProjectService projectService) {
        this.projectService = projectService;
    }

}
