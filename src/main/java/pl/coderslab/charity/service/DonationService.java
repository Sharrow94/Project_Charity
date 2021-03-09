package pl.coderslab.charity.service;

import pl.coderslab.charity.model.Donation;

import java.util.List;

public interface DonationService {

    void add(Donation donation);
    void delete(Long id);
    List<Donation> getAll();
    Donation getById(Long id);
    Long getSumBags();
    Long countDonations();
}
