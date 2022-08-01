package tcs.interviewtracker.controller;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    ResponseEntity<List<PositionDTO>> all(
            @RequestParam(required = false, defaultValue = "10") Integer pagesize,
            @RequestParam(required = false, defaultValue = "0") Integer offset,
            @RequestParam(required = false, defaultValue = "id") String orderBy,
            @RequestParam(required = false, defaultValue = "ascending") String orderDirection) {

        Pageable pagingData;

        if (orderDirection.equals("ascending"))
            pagingData = PageRequest.of(offset, pagesize, Sort.by(orderBy).ascending());
        else
            pagingData = PageRequest.of(offset, pagesize, Sort.by(orderBy).ascending());

        var positions = positionService.findAll(pagingData).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(positions, HttpStatus.OK);

    }

    @GetMapping("{id}")
    ResponseEntity<PositionDTO> findById(@PathVariable UUID id) {

        var position = positionService.findById(id);
        if (position.isPresent()) {
            return new ResponseEntity<>(this.convertToDto(position.get()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping
    ResponseEntity<PositionDTO> newPost(@RequestBody PositionDTO position) {
        try {
            var returnPosition = this.convertToDto(positionService.save(convertToEntity(position)));
            return new ResponseEntity<PositionDTO>(returnPosition, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // TODO ask team, delete should not return anything
    @DeleteMapping("{id}")
    public ResponseEntity<PositionDTO> deletePosition(@RequestParam UUID id) {
        this.positionService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<PositionDTO> updatePosition(@RequestBody PositionDTO positionDTO, @PathVariable UUID id) {
        if (!positionService.findById(id).isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else {
            positionService.update(convertToEntity(positionDTO));
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    private PositionDTO convertToDto(Position position) {
        PositionDTO positionDTO = modelMapper.map(position, PositionDTO.class);
        return positionDTO;
    }

    private Position convertToEntity(PositionDTO positionDTO) {

        return modelMapper.map(positionDTO, Position.class);
    }
}
 

