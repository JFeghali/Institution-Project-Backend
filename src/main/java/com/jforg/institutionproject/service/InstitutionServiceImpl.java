package com.jforg.institutionproject.service;

import com.jforg.institutionproject.entiy.Institution;
import com.jforg.institutionproject.repository.InstitutionRepository;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstitutionServiceImpl implements InstitutionService {

    @Autowired
    InstitutionRepository institutionRepository;

    @Override
    public Institution getInstitution(Long id) {
        return institutionRepository.findById(id).get();
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
        return (List<Institution>) institutionRepository.findAll();
    }

    @Override
    public List<Institution> getActiveInstitutions() {
        return institutionRepository.findAllByStatus(true);
    }
}
