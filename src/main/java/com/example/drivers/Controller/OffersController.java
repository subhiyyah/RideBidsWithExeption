package com.example.drivers.Controller;

import com.example.drivers.ApiResponse.ApiResponse;
import com.example.drivers.Model.Offers;
import com.example.drivers.Service.OffersService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/offers")
@RequiredArgsConstructor
public class OffersController {
    private final OffersService offersService;


    @PostMapping("/createOffer/{userId}")
    public ResponseEntity<?> createOffer(@PathVariable Integer userId, @Valid @RequestBody Offers offers){
        offersService.createOffer(userId, offers);
         return ResponseEntity.status(200).body(new ApiResponse("Offer created successfully"));
    }

    @GetMapping("/getAllOffers")
    public ResponseEntity<?> getAllOffers(){
        return ResponseEntity.status(200).body(offersService.getAllOffers());
}

    @DeleteMapping("/deleteOffer/{offerId}")
    public ResponseEntity<?> deleteOffer(@PathVariable Integer offerId){
      offersService.deleteOffer(offerId);
            return ResponseEntity.status(200).body(new ApiResponse("Offer deleted successfully"));
        }


    @PutMapping("/updateOffer/{offerId}")
    public ResponseEntity<?> updateOffer(@PathVariable Integer offerId, @Valid @RequestBody Offers offers){
       offersService.updateOffer(offerId, offers);
            return ResponseEntity.status(200).body(new ApiResponse("Offer updated successfully"));
    }

    @GetMapping("/getoffers-by-userid/{userId}")
    public ResponseEntity<?>getOffersByUserId(@PathVariable Integer userId){
        List<Offers> offersList= offersService.getOffersByUserId(userId);
        return ResponseEntity.status(200).body(new ApiResponse(""+offersList));
    }

    @GetMapping("/existOffersById/{offerId}")
    public ResponseEntity<?>existOffersById(@PathVariable Integer offerId){
        offersService.existOffersById(offerId);
        return ResponseEntity.status(200).body(new ApiResponse("offered Id existed"));

    }





}//end of class