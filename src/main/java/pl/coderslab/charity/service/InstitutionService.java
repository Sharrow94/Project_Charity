package pl.coderslab.charity.service;

import pl.coderslab.charity.model.Institution;

import java.util.List;

public interface InstitutionService {

    void add(Institution institution);
    void delete(Long id);
    List<Institution> getAll();
    Institution getById(Long id);

}
