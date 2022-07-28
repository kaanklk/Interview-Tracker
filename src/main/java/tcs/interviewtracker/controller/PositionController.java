package tcs.interviewtracker.controller;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.tomcat.util.http.parser.HttpParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tcs.interviewtracker.DTOs.PositionDTO;
import tcs.interviewtracker.persistence.Position;
import tcs.interviewtracker.service.PositionService;

@RestController
@RequestMapping("/positions")
public class PositionController {

    @Autowired
    private PositionService positionService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    ResponseEntity<List<PositionDTO>> all() {

        try {
            var positions = positionService.findAll().stream()
                    .map(this::convertToDto)
                    .collect(Collectors.toList());


            return new ResponseEntity<>(positions, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    //TODO convert uuid to int
    @GetMapping("{id}")
    ResponseEntity<PositionDTO> findById(@PathVariable Long id) {

        var position =  positionService.findById(id);
        if(position.isPresent()){
            return new ResponseEntity<>(this.convertToDto(position.get()), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping
    ResponseEntity<PositionDTO> newPost(@RequestBody PositionDTO position){
       try {
        var returnPosition = this.convertToDto(positionService.save(convertToEntity(position)));
        return new ResponseEntity<PositionDTO>(returnPosition, HttpStatus.CREATED);
       } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }

    //TODO ask team, delete should not return anything
    @DeleteMapping("{id}")
    public ResponseEntity<PositionDTO> deletePosition(@RequestParam Long id){
        try {
            this.positionService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity<PositionDTO> updatePosition(PositionDTO positionDTO){
        if(!positionService.findById(convertUuidToDatabaseId(positionDTO.getId())).isPresent())
             return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else{
            try {
                positionService.update(convertToEntity(positionDTO));
                return new ResponseEntity<>(HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }



    private PositionDTO convertToDto(Position position) {
        PositionDTO positionDTO = modelMapper.map(position, PositionDTO.class);
        return positionDTO;
    }

    private Position convertToEntity(PositionDTO positionDTO) {

        return modelMapper.map(positionDTO, Position.class);
    }

    private Long convertUuidToDatabaseId(UUID uuid){
        return 1L;
    }

}
