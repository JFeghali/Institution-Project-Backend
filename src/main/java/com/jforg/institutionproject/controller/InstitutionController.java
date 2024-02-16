package com.jforg.institutionproject.controller;

import com.jforg.institutionproject.dto.InstitutionDTO;
import com.jforg.institutionproject.entity.Institution;
import com.jforg.institutionproject.response.ResponseMessage;
import com.jforg.institutionproject.service.InstitutionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class InstitutionController {

    @Autowired
    InstitutionService institutionService;

    @PostMapping("/institution/create")
    public ResponseEntity<ResponseMessage<Institution>> createInstitution(@Valid @RequestBody InstitutionDTO institutionDTO) {
        Institution institution = mapDtoToEntity(institutionDTO);
        Institution savedInstitution = institutionService.saveInstitution(institution);
        ResponseMessage<Institution> responseMessage = new ResponseMessage<>(HttpStatus.CREATED, "Institution created successfully", savedInstitution);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
    }

    @GetMapping("/institution/{id}")
    public ResponseEntity<ResponseMessage<InstitutionDTO>> getInstitution(@PathVariable Long id) {
        Institution institution = institutionService.getInstitution(id);
        if (institution == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage<>(HttpStatus.NOT_FOUND, "Institution not found", null));
        }
        InstitutionDTO institutionDTO = mapEntityToDto(institution);
        ResponseMessage<InstitutionDTO> responseMessage = new ResponseMessage<>(HttpStatus.OK, "Institution retrieved successfully", institutionDTO);
        return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
    }

    @GetMapping("/institutions")
    public ResponseEntity<ResponseMessage<List<InstitutionDTO>>> getInstitutions() {
        List<Institution> institutions = institutionService.getInstitutions();
        if (institutions.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ResponseMessage<>(HttpStatus.NO_CONTENT, "No institutions found", null));
        }
        List<InstitutionDTO> institutionDTOs = institutions.stream()
                .map(this::mapEntityToDto)
                .collect(Collectors.toList());
        ResponseMessage<List<InstitutionDTO>> responseMessage = new ResponseMessage<>(HttpStatus.OK, "Institutions retrieved successfully", institutionDTOs);
        return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
    }

    @DeleteMapping("/institution/delete/{id}")
    public ResponseEntity<ResponseMessage<Void>> deleteInstitution(@PathVariable Long id) {
        Institution institution = institutionService.getInstitution(id);
        if (institution == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage<>(HttpStatus.NOT_FOUND, "Institution not found", null));
        }
        institutionService.deleteInstitution(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage<>(HttpStatus.OK, "Institution deleted successfully", null));
    }

    @GetMapping("/institutions/active")
    public ResponseEntity<ResponseMessage<List<InstitutionDTO>>> getActiveInstitutions() {
        List<Institution> institutions = institutionService.getActiveInstitutions();
        if (institutions.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ResponseMessage<>(HttpStatus.NO_CONTENT, "No active institutions found", null));
        }
        List<InstitutionDTO> institutionDTOs = institutions.stream()
                .map(this::mapEntityToDto)
                .collect(Collectors.toList());
        ResponseMessage<List<InstitutionDTO>> responseMessage = new ResponseMessage<>(HttpStatus.OK, "Active institutions retrieved successfully", institutionDTOs);
        return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
    }

    //  method to map InstitutionDTO to Institution entity
    private Institution mapDtoToEntity(InstitutionDTO institutionDTO) {
        Institution institution = new Institution();
        institution.setId(institutionDTO.getId());
        institution.setCode(institutionDTO.getCode());
        institution.setName(institutionDTO.getName());
        institution.setStatus(institutionDTO.getStatus());
        return institution;
    }

    //  method to map Institution entity to InstitutionDTO
    private InstitutionDTO mapEntityToDto(Institution institution) {
        InstitutionDTO institutionDTO = new InstitutionDTO();
        institutionDTO.setId(institution.getId());
        institutionDTO.setCode(institution.getCode());
        institutionDTO.setName(institution.getName());
        institutionDTO.setStatus(institution.getStatus());
        return institutionDTO;
    }
}
