package tcs.interviewtracker.controller;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.net.ssl.HttpsURLConnection;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
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
import tcs.interviewtracker.exceptions.ResourceNotFoundException;
import tcs.interviewtracker.persistence.Position;
import tcs.interviewtracker.service.PositionService;
import tcs.interviewtracker.service.ProjectService;

@RestController
@RequestMapping("/positions")
public class PositionController {

    @Autowired
    private PositionService positionService;

    @Autowired
    private ProjectService projectService;

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
            pagingData = PageRequest.of(offset, pagesize, Sort.by(orderBy).descending());

        var positions = positionService.findAll(pagingData).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(positions, HttpStatus.OK);

    }

    @GetMapping("{id}")
    ResponseEntity<PositionDTO> findById(@PathVariable UUID id) {

        var position = positionService.findByUuid(id);
        if (position.isPresent()) {
            return new ResponseEntity<>(this.convertToDto(position.get()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/{positionId}/candidate-count")
    ResponseEntity<Integer> candidateCount(@PathVariable(value = "positionId") UUID uuid)
            throws ResourceNotFoundException {
        var position = positionService.findByUuid(uuid);
        if (position.isPresent()) {
            var candidateCount = positionService.getTotalCandidateCount(uuid);
            return new ResponseEntity<>(candidateCount, HttpStatus.OK);
        } else {
            throw new ResourceNotFoundException("Position not found");
        }

    }

    @PostMapping
    ResponseEntity<PositionDTO> newPost(@RequestBody PositionDTO position) {
        var returnPosition = this.convertToDto(positionService.save(convertToEntity(position)));
        return new ResponseEntity<PositionDTO>(returnPosition, HttpStatus.CREATED);

    }

    @DeleteMapping("{uuid}")
    public ResponseEntity<PositionDTO> deletePosition(@RequestParam UUID uuid) {
        this.positionService.delete(uuid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("{uuid}")
    public ResponseEntity<PositionDTO> updatePosition(@RequestBody PositionDTO positionDTO, @PathVariable UUID uuid)
            throws ResourceNotFoundException {
        if (!positionService.findByUuid(uuid).isPresent())
            throw new ResourceNotFoundException("Position not found");
        else {
            positionDTO.setUuid(uuid);
            var updated = positionService.update(convertToEntity(positionDTO));
            return new ResponseEntity<>(convertToDto(updated), HttpStatus.OK);
        }
    }

    private PositionDTO convertToDto(Position position) {
        PositionDTO positionDTO = modelMapper.map(position, PositionDTO.class);
        return positionDTO;
    }

    private Position convertToEntity(PositionDTO positionDTO) {

        // modelMapper.addMappings(mapper -> mapper.skip(Position::setProject));
        // var position = PositionMapper.INSTANCE.toEntity(positionDTO);

        /*
         * var project = projectService.getByUuid(positionDTO.getProjectId());
         * position.setProject(project.get());
         */

        // return modelMapper.map(positionDTO, Position.class);
        var pos = new Position();
        pos.setUuid(positionDTO.getUuid());
        pos.setHiredCount(positionDTO.getHiredCount());
        pos.setOpen(positionDTO.getOpen());
        pos.setPositionName(positionDTO.getPositionName());
        pos.setTotalCount(positionDTO.getTotalCount());
        pos.setProject(projectService.getByUuid(positionDTO.getProjectUuid()));
        return pos;
    }
}
