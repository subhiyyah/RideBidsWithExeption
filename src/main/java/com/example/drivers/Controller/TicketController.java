package com.example.drivers.Controller;

import com.example.drivers.ApiResponse.ApiResponse;
import com.example.drivers.Model.Ticket;
import com.example.drivers.Service.TicketService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tickets")
@RequiredArgsConstructor

public class TicketController {
    private final TicketService ticketService;

    @GetMapping("/getAllTickets")
    public ResponseEntity<?> getAllTickets(){
        return ResponseEntity.status(200).body(new ApiResponse(""+ticketService.getAllTickets()));
    }

//    @PostMapping("/addTicket")
//    public ResponseEntity<?> addTicket(@PathVariable Integer bidId, @Valid @RequestBody Ticket ticket, Errors errors) {
//        if (errors.hasErrors()) {
//            String message = errors.getFieldError().getDefaultMessage();
//            return ResponseEntity.status(400).body(message);
//
//        }
//        boolean isAdded = ticketService.addTicket(bidId, ticket);
//        if (!isAdded) {
//            return ResponseEntity.status(400).body("Bid does not exist");
//        } else {
//            return ResponseEntity.status(200).body("Ticket added successfully");
//        }
//    }

    @DeleteMapping("/deleteTicket/{ticketId}")
    public ResponseEntity<?> deleteTicket(@PathVariable Integer ticketId) {
      ticketService.deleteTicket(ticketId);
            return ResponseEntity.status(200).body(new ApiResponse("Ticket deleted successfully"));
        }

    @PutMapping("/updateTicket/{ticketId}")
    public ResponseEntity<?> updateTicket(@PathVariable Integer ticketId, @Valid @RequestBody Ticket ticket) {
        ticketService.updateTicket(ticketId, ticket);
            return ResponseEntity.status(200).body(new ApiResponse("Ticket updated successfully"));
        }



}