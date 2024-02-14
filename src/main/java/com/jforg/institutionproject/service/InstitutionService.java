package com.jforg.institutionproject.service;

import com.jforg.institutionproject.entity.Institution;

import java.util.List;

public interface InstitutionService {
    Institution getInstitution(Long id);
    Institution saveInstitution(Institution student);
    void deleteInstitution(Long id);
    List<Institution> getInstitutions();
    List<Institution> getActiveInstitutions();

}
