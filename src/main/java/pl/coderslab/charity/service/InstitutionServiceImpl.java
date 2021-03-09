package pl.coderslab.charity.service;

import org.springframework.stereotype.Service;
import pl.coderslab.charity.model.Institution;
import pl.coderslab.charity.repository.InstitutionRepository;
import java.util.List;

@Service
public class InstitutionServiceImpl implements InstitutionService{

    private final InstitutionRepository institutionRepository;

    public InstitutionServiceImpl(InstitutionRepository institutionRepository) {
        this.institutionRepository = institutionRepository;
    }

    @Override
    public void add(Institution institution) {
        institutionRepository.save(institution);
    }

    @Override
    public void delete(Long id) {
        institutionRepository.deleteById(id);
    }

    @Override
    public List<Institution> getAll() {
        return institutionRepository.findAll();
    }

    @Override
    public Institution getById(Long id) {
        return institutionRepository.findById(id).get();
    }
}
