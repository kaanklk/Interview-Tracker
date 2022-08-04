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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import tcs.interviewtracker.DTOs.UserDTO;
import tcs.interviewtracker.exceptions.ResourceAlreadyExistsException;
import tcs.interviewtracker.exceptions.ResourceNotFoundException;
import tcs.interviewtracker.persistence.User;
import tcs.interviewtracker.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private ModelMapper modelMapper;
    private UserService service;

    UserController(UserService service){
        this.service = service;
    }

    @GetMapping("")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<UserDTO> getAllUsers(
        @RequestParam(required = false, defaultValue = "100") Integer pagesize,
        @RequestParam(required = false, defaultValue = "0") Integer offset,
        @RequestParam(required = false, defaultValue = "id") String orderBy,
        @RequestParam(required = false, defaultValue = "ascending") String orderDirection){

        Pageable page;

        if (orderDirection.equals("ascending"))
            page = PageRequest.of(offset, pagesize, Sort.by(orderBy).ascending());
        else
            page = PageRequest.of(offset, pagesize, Sort.by(orderBy).descending());


        var usersDTO = service.getAllUsers(page).stream()
        .map(this::entityToDto)
        .collect(Collectors.toList());

        return usersDTO;
    }

    @GetMapping("/{uuid}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public UserDTO getUserById(@PathVariable UUID uuid) throws ResourceNotFoundException{
        return entityToDto(service.getUserById(uuid));
    }
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO save(@RequestBody UserDTO userDTO) throws ResourceAlreadyExistsException{
        User user = dtoToEntity(userDTO);
        return entityToDto(service.saveUser(user));
    }

    @PutMapping("/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO updateUserById(@PathVariable UUID uuid, @RequestBody UserDTO userDTO) throws ResourceNotFoundException{
        User user = dtoToEntity(userDTO);
        return entityToDto(service.updateUser(uuid,user));
    }

    @DeleteMapping("/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserById(@PathVariable UUID uuid) throws ResourceNotFoundException{
        service.deleteUser(uuid);
    }

    public User dtoToEntity(UserDTO userDTO){
        return modelMapper.map(userDTO, User.class);
   }

   public UserDTO entityToDto(User user){
       return modelMapper.map(user, UserDTO.class);
   }

}
