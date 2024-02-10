package com.jforg.institutionproject.controller;


import com.jforg.institutionproject.entiy.Institution;
import com.jforg.institutionproject.service.InstitutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.List;

@RestController
public class InstitutionController {

    @Autowired
    InstitutionService institutionService;

    @PostMapping("/create-institution")
    public ResponseEntity<Institution> createInstitution(@RequestBody Institution institution){
        return new ResponseEntity<>(institutionService.saveInstitution(institution), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
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

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Institution> deleteInstitution(@PathVariable Long id){
        institutionService.deleteInstitution(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/active-institutions")
    public ResponseEntity<List<Institution>> getActiveInstitutions(){
        List<Institution> institutions = institutionService.getActiveInstitutions();
        if (institutions.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(institutions, HttpStatus.OK);
    }
}
