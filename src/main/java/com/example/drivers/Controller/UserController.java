package com.example.drivers.Controller;

import com.example.drivers.ApiResponse.ApiResponse;
import com.example.drivers.Model.Bid;
import com.example.drivers.Model.Offers;
import com.example.drivers.Model.Ticket;
import com.example.drivers.Model.User;
import com.example.drivers.Service.BidService;
import com.example.drivers.Service.OffersService;
import com.example.drivers.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
 public class UserController {
    private final UserService userService;
    private final BidService bidService;
    private final OffersService offersService;


    @GetMapping("/getAllUser")
    public ResponseEntity<?> getAllUser(){
        return ResponseEntity.status(200).body(new ApiResponse(""+userService.getAllUsers()));
    }

    @PostMapping("/addUser")
    public ResponseEntity<?> addUser(@RequestBody @Valid User user ){
        userService.addUser(user);
        return ResponseEntity.status(200).body(new ApiResponse("User added"));
    }



    @PutMapping("/updateUser/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Integer id, @RequestBody @Valid User user){
       userService.updateUser(id, user);
        return ResponseEntity.status(200).body(new ApiResponse("User updated"));
    }


@DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id){
        userService.deletUser(id);
        return ResponseEntity.status(200).body(new ApiResponse("user deleted"));
    }

@PutMapping("/setBalance/{id}/{balance}")
public ResponseEntity<?>setBalance(@PathVariable  Integer id, @PathVariable double balance){
        userService.setBalance(id,balance);
        return ResponseEntity.status(200).body(new ApiResponse("set balance successfully"));
}


@GetMapping("/checkaUserExists/{id}")
    public ResponseEntity<?> checkUserExists(@PathVariable Integer id){
    userService.checkUserExists(id);
            return ResponseEntity.status(200).body(new ApiResponse("User exists"));
    }

    @GetMapping("/find-all-offers-by-userid/{userId}")
    public ResponseEntity<?>findAllOffersByUserId(@PathVariable Integer userId){
     List<Offers> offersList= userService.findAllOffersByUserId(userId);
     return ResponseEntity.status(200).body(offersList);
    }

    @DeleteMapping("/delete-offers-by-userid-offersid-and-user-id/{offersId}/{userId}")
    public ResponseEntity<?>deleteOffersByUserIdAndOfferId(@PathVariable Integer offersId, @PathVariable Integer userId){
       userService.deleteOffersByUserIdAndOfferId(userId,offersId);
        return ResponseEntity.status(200).body(new ApiResponse("offers deleted"));
    }


    @PostMapping("/add-offers-by-userid/{userId}")
    public ResponseEntity<?>addOffersByUserId(@PathVariable Integer userId,@RequestBody @Valid Offers offers){
      offersService.createOffer(userId,offers);
            return ResponseEntity.status(200).body(new ApiResponse("Offer created successfully"));
    }



    @GetMapping("/show-bids-by-offerid/{offersId}")
 public ResponseEntity<?>ShowBidsByOffersId(@PathVariable Integer offersId){
        List<Bid> bidList=bidService.getBidsByOfferId(offersId);

        return ResponseEntity.status(200).body(new ApiResponse(bidList.toString()));
 }

 @GetMapping("/chose-bid/{bidId}")
 public ResponseEntity<?>ChoseBidByBidId( @PathVariable Integer bidId){
  bidService.ChoseBidByBidId(bidId);
            return ResponseEntity.status(200).body(new ApiResponse("chose bid successfully"));
        }




    @PutMapping("/book-ticket/{userId}/{bidId}")
    public ResponseEntity<?> bookTicket(@PathVariable Integer userId, @PathVariable Integer bidId) {
       userService.bookTicket(userId, bidId);

            return ResponseEntity.status(200).body(new ApiResponse("Ticket booked successfully"));
        }



    @PutMapping("/cancel-ticket-by-userid/{userId}/{ticketId}")
    public ResponseEntity<?>cancelTicketByUser(@PathVariable Integer userId,@PathVariable Integer ticketId){
   userService.cancelTicketByUser(userId,ticketId);
            return ResponseEntity.status(200).body(new ApiResponse( "Ticket cancelled by user successfully"));
        }




    @PutMapping("/rate-driver/{userId}/{ticketId}/{rating}/{comment}")
    public ResponseEntity <?>addDriverRating(
            @PathVariable Integer userId, @PathVariable Integer ticketId, @PathVariable Double rating, @PathVariable String comment) {

       userService.addDriverRating(userId, ticketId, rating, comment);
            return ResponseEntity.status(200).body(new ApiResponse("add driver rating successfully"));
        }




    @PostMapping("/add-complaint-by-user/{userId}/{ticketId}/{description}")
    public ResponseEntity <?>addComplaintByUser(
            @PathVariable Integer userId,
            @PathVariable Integer ticketId,
            @PathVariable String description) {
         userService.addComplaintByUser(userId, ticketId, description);
            return ResponseEntity.status(201).body(new ApiResponse("Complaint submitted successfully by user"));
        }



    @GetMapping("/get-user-rate-by-id/{userId}")
    public ResponseEntity<?>getUserRateById(@PathVariable Integer userId){
        Object result=userService.getUserRateById(userId);
        return ResponseEntity.status(200).body(new ApiResponse(result.toString()));
    }


    @GetMapping("/get-driver-rate-by-id/{driverId}")
    public ResponseEntity<?>getDriverRateById(@PathVariable Integer driverId){
        Object result=userService.getDriverRateById(driverId);
        return ResponseEntity.status(200).body(new ApiResponse(result.toString()));
    }

    @GetMapping("/get-all-ticket-by-user-id/{userId}")
    public ResponseEntity<?>getAllTicketByUserId(@PathVariable Integer userId ){
        List<Ticket> tickets=userService.getAllTicketByUserId(userId);
        return ResponseEntity.status(200).body(new ApiResponse(" "+tickets));
    }
//    @PutMapping("/complaint-handling/{userId}/{complaintId}")
//    public ResponseEntity<?>complaintHandling(@PathVariable Integer userId, @PathVariable Integer complaintId){
//        String result=userService.complaintHandling(userId,complaintId);
//        if(result.equalsIgnoreCase("there are no user in this id")){
//            ResponseEntity.status(400).body(new ApiResponse(result));
//        }
//        if(result.equalsIgnoreCase("This user does not have the authority to process the complaint")){
//            ResponseEntity.status(400).body(new ApiResponse(result));
//        }
//        if(result.equalsIgnoreCase("there are no complaint in this id")){
//            ResponseEntity.status(400).body(new ApiResponse(result));
//        }
//        return ResponseEntity.status(200).body(new ApiResponse(result));
//    }



}//end class