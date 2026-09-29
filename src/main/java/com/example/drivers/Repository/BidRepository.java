package com.example.drivers.Repository;

import com.example.drivers.Model.Bid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface BidRepository extends JpaRepository<Bid, Integer> {
    Bid findBidById(Integer id);

    List<Bid> findByOfferId(Integer offerId);



    List<Bid> findByDriverIdAndStatus(Integer driverId, String accepted);
}