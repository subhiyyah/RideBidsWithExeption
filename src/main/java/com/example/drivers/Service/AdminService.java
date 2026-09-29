package com.example.drivers.Service;

import com.example.drivers.ApiResponse.ApiException;
import com.example.drivers.Model.*;
import com.example.drivers.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {
   private final AdminRepository adminRepository;
   private final ComplaintRepsitory complaintRepsitory;
   private final TicketRepository ticketRepository;
   private final DriversRepository driversRepository;
   private final UserRepository userRepository;

   private final TicketService ticketService;

    public List<Admin> getAllAdmin(){
        return adminRepository.findAll();
    }

    public void addAdmin(Admin admin){
        adminRepository.save(admin);
    }


    public void updateAdmin(Integer id , Admin admin){
        Admin oldAdmin= adminRepository.findAdminById(id);
        if(oldAdmin==null){
            throw new ApiException("id not found");
        }
        oldAdmin.setName(admin.getName());
        oldAdmin.setAge(admin.getAge());
        oldAdmin.setEmail(admin.getEmail());
        oldAdmin.setPhone(admin.getPhone());
        adminRepository.save(oldAdmin);
    }

    public void deleteAdmin(Integer adminId){
        Admin admin=adminRepository.findAdminById(adminId);
        if(admin==null){
            throw new ApiException(("id not found"));
        }
        adminRepository.delete(admin);
    }




    public Object complaintHandling(Integer adminId, Integer complaintId){
        Admin admin=adminRepository.findAdminById(adminId);

       if(admin==null){
           throw new ApiException("there are no admin in this id");
       }

        //اخليه يستقبل الادمن اي دي مو اليوزر

        Complaint complaint=complaintRepsitory.findComplaintById(complaintId);
        if(complaint==null){
            throw new ApiException("there are no admin in this id");

        }
        Ticket ticket=ticketRepository.findTicketById(complaint.getTicketId());
        double finalprice=ticket.getFinalPrice();
        User user=userRepository.findUserById(ticket.getUserId());



        Drivers drivers=driversRepository.findDriversById(ticket.getDriverId());

//        if(complaint.getDescription().contains("assault, hit, struck, punched, slapped, violence, fight, brawl, threat, threatened, weapon, knife, gun, kidnap, abduction, harassment, harassed, touched, sexual, suggestive, filmed, stalked, profanity, cursed, swore, insulted, abuse, reckless, speeding, red light, drunk, intoxicated, high, drugs, alcohol, accident, crashed, stole, theft, robbery, extortion, demanded cash, overcharged, fraud, scam, racist, racism, discrimination, slurs")) {
        if (complaint.getDescription() != null && complaint.getDescription().toLowerCase().contains("violence") || complaint.getDescription().toLowerCase().contains("harassment") || complaint.getDescription().toLowerCase().contains("racism") || complaint.getDescription().toLowerCase().contains("extortion")) {

            if (complaint.getRole().equalsIgnoreCase("user")) {

                drivers.setTotalProfits(drivers.getTotalProfits() - finalprice);
                drivers.setNumberOfTrips(drivers.getNumberOfTrips() - 1);
                user.setBalance(user.getBalance() + finalprice);
                user.setTotalPayment(user.getTotalPayment() - finalprice);

                userRepository.save(user);
                driversRepository.save(drivers);
                ticketService.deleteTicket(ticket.getId());
                ticketRepository.delete(ticket);

                complaint.setStatus("resolve");
                complaintRepsitory.save(complaint);

                return "The issue has been resolved, the ticket cancelled, and the full amount refunded to the customer.";

            }
            if (complaint.getRole().equalsIgnoreCase("driver")) {
                drivers.setCompensation(finalprice+drivers.getTotalProfits());
                driversRepository.save(drivers);
                ticketService.deleteTicket(ticket.getId());
                complaint.setStatus("resolve");
                complaintRepsitory.save(complaint);

                return "The issue has been resolved and the ticket cancelled; the ticket amount will be issued to you as compensation.";


            }
        }else {
            complaint.setStatus("closed");
            complaintRepsitory.save(complaint);
            return "The ticket has been closed; there is no need to cancel the reservation.";
        }

       return "\"The complaint has been closed without action; no policy violations were detected.";
    }
}