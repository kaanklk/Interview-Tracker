package tcs.interviewtracker.controller;


import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import tcs.interviewtracker.DTOs.UserDTO;
import tcs.interviewtracker.exceptions.ResourceAlreadyExistsException;
import tcs.interviewtracker.exceptions.ResourceNotFoundException;
import tcs.interviewtracker.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService service;

    UserController(UserService service){
        this.service = service;
    }

    @GetMapping("")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<UserDTO> getAllUsers(){
        return service.getAllUsers();
    }
/* 
    @GetMapping("/{uuid}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public UserDTO getUserById(@PathVariable UUID uuid) throws ResourceNotFoundException{
        return service.getUserById(uuid);
    }
*/
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO save(@RequestBody UserDTO userDTO) throws ResourceAlreadyExistsException{
        return service.saveUser(userDTO);
    }

    @PutMapping("/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO updateUserById(@PathVariable UUID uuid, @RequestBody UserDTO userDTO) throws ResourceNotFoundException{
        return service.updateUser(uuid,userDTO);
    }

    @DeleteMapping("/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserById(@PathVariable UUID uuid) throws ResourceNotFoundException{
        service.deleteUser(uuid);
    }

}
