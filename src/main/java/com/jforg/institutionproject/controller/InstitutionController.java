package com.jforg.institutionproject.controller;


import com.jforg.institutionproject.entiy.Institution;
import com.jforg.institutionproject.service.InstitutionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.List;

@RestController()
@RequestMapping("/api/v1")
public class InstitutionController {

    @Autowired
    InstitutionService institutionService;

    @PostMapping("/institution/create")
    public ResponseEntity<Institution> createInstitution(@Valid @RequestBody Institution institution){
        return new ResponseEntity<>(institutionService.saveInstitution(institution), HttpStatus.CREATED);
    }

    @GetMapping("/institution/{id}")
    public ResponseEntity<Institution> getInstitution(@PathVariable Long id){
        return new ResponseEntity<>(institutionService.getInstitution(id), HttpStatus.OK);
    }
    @GetMapping("/institutions")
    public ResponseEntity<List<Institution>> getInstitutions(){
        List<Institution> institutions = institutionService.getInstitutions();
        if (institutions.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(institutions, HttpStatus.OK);
    }

    @DeleteMapping("/institution/delete/{id}")
    public ResponseEntity<Institution> deleteInstitution(@PathVariable Long id){
        institutionService.deleteInstitution(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/institutions/active")
    public ResponseEntity<List<Institution>> getActiveInstitutions(){
        List<Institution> institutions = institutionService.getActiveInstitutions();
        if (institutions.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(institutions, HttpStatus.OK);
    }
}
