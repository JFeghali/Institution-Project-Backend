package com.jforg.institutionproject.repository;

import com.jforg.institutionproject.entity.Institution;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface InstitutionRepository extends CrudRepository<Institution,Long> {
    List<Institution> findAllByStatus(Boolean status);
}
