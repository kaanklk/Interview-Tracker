package tcs.interviewtracker.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
import tcs.interviewtracker.exceptions.BadRequestException;
import tcs.interviewtracker.exceptions.ResourceNotFoundException;

import tcs.interviewtracker.persistence.Position;
import tcs.interviewtracker.persistence.Project;
import tcs.interviewtracker.service.PositionService;
import tcs.interviewtracker.service.ProjectService;

@RestController
@RequestMapping("/positions")
public class PositionController {

    @Autowired
    private PositionService positionService;

    @Autowired
    private ProjectService projectService;

    private ModelMapper modelMapper;

    public PositionController(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.modelMapper.addConverter(positionConverter);
    }

    Converter<UUID, Project> positionConverter = new AbstractConverter<UUID, Project>() {
        protected Project convert(UUID uuid) {
            return projectService.getByUuid(uuid);
        }
    };

    @GetMapping
    ResponseEntity<List<PositionDTO>> all(
            @RequestParam(required = false, defaultValue = "10") Integer pagesize,
            @RequestParam(required = false, defaultValue = "0") Integer offset,
            @RequestParam(required = false, defaultValue = "uuid") String orderBy,
            @RequestParam(required = false, defaultValue = "ascending") String orderDirection)
            throws BadRequestException {

        Pageable pagingData;

        var fieldsOfPositionDTO = PositionDTO.class.getDeclaredFields();

        var existingOrderByField = false;

        for (Field field : fieldsOfPositionDTO) {
            if (field.getName().equals(orderBy)) {
                existingOrderByField = true;
                break;
            }
        }

        if (!existingOrderByField)
            throw new BadRequestException(
                    "Wrong query parameter value (orderBy). No such field in Position: " + orderBy);

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
    ResponseEntity<PositionDTO> findById(@PathVariable UUID id) throws ResourceNotFoundException {

        var position = positionService.findByUuid(id);
        if (!position.isPresent()) {
            throw new ResourceNotFoundException();
        }

        return new ResponseEntity<>(this.convertToDto(position.get()), HttpStatus.OK);

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
    public ResponseEntity<PositionDTO> deletePosition(@PathVariable UUID uuid) throws ResourceNotFoundException {
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
        var pos = modelMapper.map(positionDTO, Position.class);
        return pos;
    }

}
