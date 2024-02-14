package com.jforg.institutionproject.service;

import com.jforg.institutionproject.entiy.Institution;
import com.jforg.institutionproject.exception.EntityNotFoundException;
import com.jforg.institutionproject.repository.InstitutionRepository;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InstitutionServiceImpl implements InstitutionService {

    @Autowired
    InstitutionRepository institutionRepository;

    @Override
    public Institution getInstitution(Long id) {
        Optional<Institution> institution = institutionRepository.findById(id);
        return unwrapInstitution(institution, id);
    }

    @Override
    public Institution saveInstitution(Institution student) {
        return institutionRepository.save(student);
    }

    @Override
    public void deleteInstitution(Long id) {
        institutionRepository.deleteById(id);
    }

    @Override
    public List<Institution> getInstitutions() {
        List<Institution> institutions =  (List<Institution>) institutionRepository.findAll();
        if(institutions.isEmpty()){
            throw new EntityNotFoundException(Institution.class);
        }
        return (List<Institution>) institutionRepository.findAll();
    }

    @Override
    public List<Institution> getActiveInstitutions() {
        return institutionRepository.findAllByStatus(true);
    }

    static Institution unwrapInstitution(Optional<Institution> entity, Long id) {
        if (entity.isPresent()) return entity.get();
        else throw new EntityNotFoundException(id, Institution.class);
    }
}
