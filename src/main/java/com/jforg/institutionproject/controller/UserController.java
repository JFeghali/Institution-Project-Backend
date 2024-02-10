package com.jforg.institutionproject.controller;


import com.jforg.institutionproject.entiy.Institution;
import com.jforg.institutionproject.entiy.User;
import com.jforg.institutionproject.service.InstitutionService;
import com.jforg.institutionproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> createInstitution(@RequestBody User user){
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }
}
