package com.jforg.institutionproject.controller;

import com.jforg.institutionproject.entity.Institution;
import com.jforg.institutionproject.response.ResponseMessage;
import com.jforg.institutionproject.service.InstitutionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class InstitutionController {

    @Autowired
    InstitutionService institutionService;

    @PostMapping("/institution/create")
    public ResponseEntity<ResponseMessage<Institution>> createInstitution(@Valid @RequestBody Institution institution) {
        Institution savedInstitution = institutionService.saveInstitution(institution);
        ResponseMessage<Institution> responseMessage = new ResponseMessage<>(HttpStatus.CREATED, "Institution created successfully", savedInstitution);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
    }

    @GetMapping("/institution/{id}")
    public ResponseEntity<ResponseMessage<Institution>> getInstitution(@PathVariable Long id) {
        Institution institution = institutionService.getInstitution(id);
        if (institution == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage<>(HttpStatus.NOT_FOUND, "Institution not found", null));
        }
        ResponseMessage<Institution> responseMessage = new ResponseMessage<>(HttpStatus.OK, "Institution retrieved successfully", institution);
        return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
    }

    @GetMapping("/institutions")
    public ResponseEntity<ResponseMessage<List<Institution>>> getInstitutions() {
        List<Institution> institutions = institutionService.getInstitutions();
        if (institutions.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ResponseMessage<>(HttpStatus.NO_CONTENT, "No institutions found", null));
        }
        ResponseMessage<List<Institution>> responseMessage = new ResponseMessage<>(HttpStatus.OK, "Institutions retrieved successfully", institutions);
        return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
    }

    @DeleteMapping("/institution/delete/{id}")
    public ResponseEntity<ResponseMessage<Void>> deleteInstitution(@PathVariable Long id) {
        institutionService.deleteInstitution(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage<>(HttpStatus.OK, "Institution deleted successfully", null));
    }

    @GetMapping("/institutions/active")
    public ResponseEntity<ResponseMessage<List<Institution>>> getActiveInstitutions() {
        List<Institution> institutions = institutionService.getActiveInstitutions();
        if (institutions.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ResponseMessage<>(HttpStatus.NO_CONTENT, "No active institutions found", null));
        }
        ResponseMessage<List<Institution>> responseMessage = new ResponseMessage<>(HttpStatus.OK, "Active institutions retrieved successfully", institutions);
        return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
    }
}
