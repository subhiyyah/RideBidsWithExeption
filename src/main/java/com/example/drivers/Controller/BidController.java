package com.example.drivers.Controller;

import com.example.drivers.ApiResponse.ApiResponse;
import com.example.drivers.Model.Bid;
import com.example.drivers.Service.BidService;
import com.example.drivers.Service.DriversService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/bids")
@RequiredArgsConstructor
public class BidController {

    private final BidService bidService;
    private final DriversService driversService;


    @PostMapping("/createBid")
    public ResponseEntity<?> addBid(@PathVariable Integer offerId, @RequestBody @Valid Bid bid) {

    bidService.addBid(offerId, bid);
    return ResponseEntity.status(200).body(new ApiResponse("Bid created successfully"));

    }

    @DeleteMapping("/deleteBid/{bidId}")
    public ResponseEntity<?> deleteBid(@PathVariable Integer bidId) {
     bidService.deleteBid(bidId);
            return ResponseEntity.status(200).body(new ApiResponse("Bid deleted successfully"));
    }


    @PutMapping("/updateBid/{bidId}")
    public ResponseEntity<?> updateBid(@PathVariable Integer bidId, @RequestBody @Valid Bid bid) {

       bidService.updateBid(bidId, bid);
        return ResponseEntity.status(200).body(new ApiResponse("Bid updated successfully"));
    }


    @GetMapping("/getAllBids")
    public ResponseEntity<?> getAllBids() {
        return ResponseEntity.status(200).body(bidService.getAllBids());
    }



}//end class