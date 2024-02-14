package com.jforg.institutionproject.controller;


import com.jforg.institutionproject.entiy.Institution;
import com.jforg.institutionproject.entiy.User;
import com.jforg.institutionproject.response.ResponseMessage;
import com.jforg.institutionproject.service.InstitutionService;
import com.jforg.institutionproject.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ResponseMessage> createInstitution(@Valid @RequestBody User user){
        Optional<User> userRecord = userService.getUserByUsername(user.getUsername());
        if(userRecord.isPresent()){
            return new ResponseEntity<>(new ResponseMessage<>("Username already exists",null), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new ResponseMessage<>("User has been successfully registered ",userService.saveUser(user)), HttpStatus.CREATED);
    }
}
