package com.example.drivers.Service;

import com.example.drivers.ApiResponse.ApiException;
import com.example.drivers.Model.*;
import com.example.drivers.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;
    private final BidRepository bidRepository;
    private final OffersRepository offersRepository;
    private final UserRepository userRepository;
    private final DriversRepository driversRepository;

    private final EmailService emailService;


    public boolean checkTicketExists(Integer ticketId) {
        return ticketRepository.existsById(ticketId);
    }

    public void createTicket(Integer userId, Integer bidId) {
        Bid bid = bidRepository.findById(bidId).orElse(null);
        if (bid == null) {
            throw new ApiException("Bid does not exist");

        }

        if (!"accepted".equalsIgnoreCase(bid.getStatus())) {

            throw new ApiException("Bid is not accepted");

        }

        Offers offer = offersRepository.findById(bid.getOfferId()).orElse(null);
        if (offer == null) {
            throw new ApiException("Offer does not exist");
        }

        User user = userRepository.findUserById(userId);
        if (user == null) {
            throw new ApiException("User does not exist");
        }

        if (!userId.equals(offer.getUserId())) {
            throw new ApiException("This offer does not belong to this user");
        }

        Drivers driver = driversRepository.findById(bid.getDriverId()).orElse(null);
        if (driver == null) {
            throw new ApiException("Driver does not exist");
        }

        if (ticketRepository.existsByBidId(bidId)) {
            throw new ApiException("Ticket has already been booked for this bid");
        }

        // ==========================================
        // 1. التحقق من المدة والتواريخ (30 - 90 يوماً)
        // ==========================================
        if (offer.getStartDate() == null || offer.getEndDate() == null) {
            throw new ApiException("Offer dates (start or end) cannot be null");
        }

        if (offer.getEndDate().isBefore(offer.getStartDate())) {
            throw new ApiException("End date cannot be before Start date");
        }

        long durationInDays = java.time.temporal.ChronoUnit.DAYS.between(offer.getStartDate(), offer.getEndDate());

        if (durationInDays < 30) {

            throw new ApiException("Booking duration must be at least 30 days");
        }

        if (durationInDays > 90) {
            throw new ApiException("Booking duration cannot exceed 90 days");
        }

        // ==========================================
        // 2. التحقق من السعر والرصيد
        // ==========================================
        Double price = bid.getPrice();
        if (price == null || !Double.isFinite(price) || price <= 0) {
            throw new ApiException("Bid price is invalid");
        }

        if (user.getBalance() == null || user.getBalance() < price) {

            throw new ApiException("User balance is insufficient");
        }

        // خصم المبلغ وإضافة الأرباح للسائق
        user.setBalance(user.getBalance() - price);

        if (driver.getTotalProfits() == null) {
            driver.setTotalProfits(0.0);
        }
        driver.setTotalProfits(driver.getTotalProfits() + price);
        driver.setNumberOfTrips(driver.getNumberOfTrips()+1);
        userRepository.save(user);
        driversRepository.save(driver);

        // ==========================================
        // 3. إنشاء كائن التذكرة وحفظه
        // ==========================================
        Ticket ticket = new Ticket();
        ticket.setUserId(userId);
        ticket.setDriverId(driver.getId());
        ticket.setBidId(bidId);
        ticket.setFinalPrice(price);
        ticket.setStartDate(offer.getStartDate());
        ticket.setEndDate(offer.getEndDate());
        ticket.setStatus("completed");
        ticket.setBookingDate(java.time.LocalDateTime.now());//الوقت الحالي

        ticketRepository.save(ticket);
        emailService.sendEmail(user.getEmail(),"trip ticket add successfully","nice tripe with us \n \"User balance is insufficient\"");


    }

    public void deleteTicket(Integer ticketId) {
        if (!ticketRepository.existsById(ticketId)) {
            throw new ApiException("id not found");
        }
        ticketRepository.deleteById(ticketId);
    }

    public void updateTicket(Integer ticketId, Ticket ticket) {
        if (!ticketRepository.existsById(ticketId)) {
            throw new ApiException("id not found");

        }
        Ticket existingTicket = ticketRepository.findById(ticketId).orElse(null);
        if (existingTicket != null) {             
            existingTicket.setBidId(ticket.getBidId());
            existingTicket.setStatus(ticket.getStatus());
            existingTicket.setFinalPrice(ticket.getFinalPrice());
            ticketRepository.save(existingTicket);
        }
        throw new ApiException("id not found"); }

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }


}//end of class