package com.example.drivers.Repository;

import com.example.drivers.Model.Offers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface OffersRepository extends JpaRepository<Offers, Integer> {
    Offers findOffersById(Integer id);

    List<Offers> findOffersByUserId(Integer userId);

    List<Offers> findAllOffersByUserId(Integer userId);
}