package pl.coderslab.charity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.charity.model.Donation;
import pl.coderslab.charity.model.Users;

import java.util.List;
import java.util.Optional;

@Repository
public interface DonationRepository extends JpaRepository<Donation, Long> {

    @Query("select sum(d.quantity) as bagsSum from Donation d")
    Optional<Long> getSumBags();

    @Query("select count(d.id) as sum from Donation d")
    Long countDonations();

    @Query("select d from Donation d where d.user=?1 order by d.received asc,d.pickUpDate asc")
    List<Donation> findAllByUser(Users user);
}
