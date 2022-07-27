package tcs.interviewtracker.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tcs.interviewtracker.persistence.Position;
import tcs.interviewtracker.repository.PositionRepository;
import tcs.interviewtracker.service.PositionService;

@RestController
@RequestMapping("/positions")
public class PositionController {
    private final PositionService positionService;

    public PositionController(PositionService positionService) {
        this.positionService = positionService;
    }

    @GetMapping
    ResponseEntity<List<Position>> all(){

        var positions = positionService.findAll();
        try {
            return new ResponseEntity<>(positions, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/positions/{id}")
    ResponseEntity<PositionDTO> findById(@PathVariable String id){
        positionService.findById(id);
    }


}
