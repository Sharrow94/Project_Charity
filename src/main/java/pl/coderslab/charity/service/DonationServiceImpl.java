package pl.coderslab.charity.service;

import org.springframework.stereotype.Service;
import pl.coderslab.charity.model.Donation;
import pl.coderslab.charity.model.Users;
import pl.coderslab.charity.repository.DonationRepository;
import java.util.List;
import java.util.Optional;

@Service
public class DonationServiceImpl implements DonationService {

    private final DonationRepository donationRepository;

    public DonationServiceImpl(DonationRepository donationRepository) {
        this.donationRepository = donationRepository;
    }

    @Override
    public void add(Donation donation) {
        donationRepository.save(donation);
    }

    @Override
    public void delete(Long id) {
        donationRepository.deleteById(id);
    }

    @Override
    public List<Donation> getAll() {
        return donationRepository.findAll();
    }

    @Override
    public Donation getById(Long id) {
        return donationRepository.findById(id).get();
    }

    @Override
    public Long getSumBags() {
        return donationRepository.getSumBags().orElse(0L);
    }

    @Override
    public Long countDonations() {
        return donationRepository.countDonations();
    }

    @Override
    public List<Donation> findAllByUser(Users user) {
        return donationRepository.findAllByUser(user);
    }
}
