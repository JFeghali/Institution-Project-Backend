package com.jforg.institutionproject.controller;


import com.jforg.institutionproject.entity.User;
import com.jforg.institutionproject.response.ResponseMessage;
import com.jforg.institutionproject.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ResponseMessage<User>> createInstitution(@Valid @RequestBody User user) {
        Optional<User> userRecord = userService.getUserByUsername(user.getUsername());
        if (userRecord.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseMessage<>(HttpStatus.BAD_REQUEST, "Username already exists", null));
        }
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseMessage<>(HttpStatus.CREATED, "User has been successfully registered", userService.saveUser(user)));
    }
}
