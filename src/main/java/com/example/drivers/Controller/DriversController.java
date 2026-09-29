package com.example.drivers.Controller;

import com.example.drivers.ApiResponse.ApiResponse;
import com.example.drivers.Model.Bid;
import com.example.drivers.Model.Drivers;
import com.example.drivers.Model.Offers;
import com.example.drivers.Service.BidService;
import com.example.drivers.Service.DriversService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/drivers")
@RequiredArgsConstructor

public class DriversController {

    private final DriversService driversService;
    private final BidService bidService;


    @GetMapping("/getAllDrivers")
    public ResponseEntity<?> getAllDrivers() {
        return ResponseEntity.status(200).body(driversService.getAllDrivers());
    }

    @GetMapping("/getDriverById/{id}")
    public ResponseEntity<?> getDriverById(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(driversService.getDriverById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<?> addDriver(@RequestBody @Valid Drivers driver) {
        driversService.addDrivers(driver);
        return ResponseEntity.status(200).body(new ApiResponse("Driver added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateDriver(@PathVariable Integer id, @RequestBody @Valid Drivers driver) {
    driversService.updateDrivers(id, driver);
        return ResponseEntity.status(200).body(new ApiResponse("Driver updated"));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteDriver(@PathVariable Integer id) {
    driversService.deleteDrivers(id);
        return ResponseEntity.status(200).body(new ApiResponse("Driver deleted"));
    }



    @GetMapping("/show-offers")
    public ResponseEntity<?> showOffers() {
        List<Offers> offersList = driversService.showOffers();
        return ResponseEntity.status(200).body(offersList);
    }


    @PostMapping("/add-bids/{driverId}")
    public ResponseEntity<?> addBids(@PathVariable Integer driverId, @RequestBody @Valid Bid bid) {
    driversService.addBids(driverId,bid);
            return ResponseEntity.status(200).body(new ApiResponse("add bid successfully"));

    }

    @GetMapping("/show-accepted-bids-by-driverid/{driverId}")
    public ResponseEntity<?> showAcceptedBidsByDriverId(@PathVariable Integer driverId) {
        List<Bid> bidList = bidService.showAcceptedBidsByDriverId(driverId);
        return ResponseEntity.status(200).body(bidList);
    }

     @PutMapping("/cancel-ticket-by-driver/{driverId}/{ticketId}")
    public ResponseEntity<?> cancelTicketByDriver(@PathVariable Integer driverId, @PathVariable Integer ticketId) {
       driversService.cancelTicketByDriver(driverId, ticketId);
            return ResponseEntity.status(200).body(new ApiResponse("Ticket cancelled by driver ,The amount has been refunded to the user."));
        }



    @PutMapping("/rate-user/{driverId}/{ticketId}/{rating}/{comment}")
    public ResponseEntity<?> addUserRating(@PathVariable Integer driverId, @PathVariable Integer ticketId, @PathVariable Double rating, @PathVariable String comment) {
     driversService.addUserRating(driverId, ticketId, rating, comment);
            return ResponseEntity.status(200).body(new ApiResponse(   "User rated successfully"));
        }


    @PostMapping("/add-complaint-by-driver/{driverId}/{ticketId}/{description}")
    public ResponseEntity<?> addComplaintByDriver(
            @PathVariable Integer driverId,
            @PathVariable Integer ticketId,
            @PathVariable String description) {

       driversService.addComplaintByDriver(driverId, ticketId, description);
            return ResponseEntity.status(200).body(new ApiResponse("Complaint submitted successfully by driver"));
        }


    @GetMapping("/get-driver-rate-by-id/{driverId}")
    public ResponseEntity<?>getDriverRateById(@PathVariable Integer driverId){
        Object result=driversService.getDriverRateById(driverId);
          return ResponseEntity.status(200).body(new ApiResponse(result.toString()));
    }

    @GetMapping("/get-user-rate-by-id/{userId}")
    public ResponseEntity<?>getUserRateById(@PathVariable Integer userId){
        Object result=driversService.getUserRateById(userId);

        return ResponseEntity.status(200).body(new ApiResponse(result.toString()));
    }

//getUserRateById
}