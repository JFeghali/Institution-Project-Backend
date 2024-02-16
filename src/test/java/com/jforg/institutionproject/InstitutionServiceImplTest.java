package com.jforg.institutionproject;

import com.jforg.institutionproject.entity.Institution;
import com.jforg.institutionproject.exception.EntityNotFoundException;
import com.jforg.institutionproject.repository.InstitutionRepository;
import com.jforg.institutionproject.service.InstitutionServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

public class InstitutionServiceImplTest {

    @Mock
    private InstitutionRepository institutionRepository;

    @InjectMocks
    private InstitutionServiceImpl institutionService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetInstitutionById() {
        // Mocking behavior of repository
        Long id = 1L;
        Institution institution = new Institution();
        institution.setId(id);
        Mockito.when(institutionRepository.findById(id)).thenReturn(Optional.of(institution));

        // Calling service method
        Institution result = institutionService.getInstitution(id);

        // Verifying the result
        Assertions.assertEquals(institution, result);
    }

    @Test
    public void testGetInstitutionByIdNotFound() {
        // Mocking behavior of repository
        Long id = 1L;
        Mockito.when(institutionRepository.findById(id)).thenReturn(Optional.empty());

        // Calling service method and expecting an exception
        Assertions.assertThrows(EntityNotFoundException.class, () -> institutionService.getInstitution(id));
    }

    @Test
    public void testSaveInstitution() {
        // Mocking behavior of repository
        Institution institution = new Institution();
        Mockito.when(institutionRepository.save(any(Institution.class))).thenReturn(institution);

        // Calling service method
        Institution result = institutionService.saveInstitution(new Institution());

        // Verifying the result
        Assertions.assertEquals(institution, result);
    }

    @Test
    public void testDeleteInstitution() {
        Long id = 1L;
        institutionService.deleteInstitution(id);
        Mockito.verify(institutionRepository).deleteById(id);
    }

    @Test
    public void testGetInstitutions() {
        // Mocking behavior of repository
        List<Institution> institutions = new ArrayList<>();
        institutions.add(new Institution());
        Mockito.when(institutionRepository.findAll()).thenReturn(institutions);

        // Calling service method
        List<Institution> result = institutionService.getInstitutions();

        // Verifying the result
        Assertions.assertEquals(institutions, result);
    }

    @Test
    public void testGetInstitutionsEmptyList() {
        // Mocking behavior of repository
        Mockito.when(institutionRepository.findAll()).thenReturn(new ArrayList<>());

        // Calling service method and expecting an exception
        Assertions.assertThrows(EntityNotFoundException.class, () -> institutionService.getInstitutions());
    }

    @Test
    public void testGetActiveInstitutions() {
        // Mocking behavior of repository
        List<Institution> institutions = new ArrayList<>();
        institutions.add(new Institution());
        Mockito.when(institutionRepository.findAllByStatus(true)).thenReturn(institutions);

        // Calling service method
        List<Institution> result = institutionService.getActiveInstitutions();

        // Verifying the result
        Assertions.assertEquals(institutions, result);
    }
}
