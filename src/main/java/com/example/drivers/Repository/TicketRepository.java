package com.example.drivers.Repository;

import com.example.drivers.Model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    Ticket findTicketById(Integer id);
    boolean existsByBidId(Integer bidId);

    List<Ticket> findByUserId(Integer userId);
}