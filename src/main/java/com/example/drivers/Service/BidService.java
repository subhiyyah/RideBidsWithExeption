package com.example.drivers.Service;

import com.example.drivers.ApiResponse.ApiException;
import com.example.drivers.ApiResponse.ApiResponse;
import com.example.drivers.Model.Bid;
import com.example.drivers.Model.Offers;
import com.example.drivers.Model.Ticket;
import com.example.drivers.Model.User;
import com.example.drivers.Repository.BidRepository;
import com.example.drivers.Repository.DriversRepository;
import com.example.drivers.Repository.OffersRepository;
import com.example.drivers.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BidService {
    private final BidRepository bidRepository;
    private final DriversRepository driversRepository;
    private final OffersRepository offerRepository;
    private final UserRepository userRepository;

    public void addBid( Integer offerId, Bid bid) {
        boolean driverExists = driversRepository.existsById(bid.getDriverId());
        if (!driverExists) {
            throw new ApiException("Driver does not exist");
        }
        boolean offerExists = offerRepository.existsById(offerId);
        if (!offerExists) {
            throw new ApiException("Offer does not exist");
        }
        bid.setDriverId(bid.getDriverId());
        bid.setStatus("pending");
        bidRepository.save(bid);
    }

    public void deleteBid(Integer id) {
        Bid oldBid = bidRepository.findById(id).orElse(null);
        if (oldBid == null) {
            throw new ApiException("id not found");
        }
        bidRepository.delete(oldBid);

    }

    public List<Bid> getAllBids() {
        return bidRepository.findAll();
    }

    public Bid getBidByBidId(Integer bidId){
       return bidRepository.findBidById(bidId);
    }

    public void updateBid(Integer id, Bid bid) {
        Bid oldBid = bidRepository.findById(id).orElse(null);
        if (oldBid == null) {
            throw new ApiException("bid id not found");
        }

        oldBid.setDriverId(bid.getDriverId());
        oldBid.setOfferId(bid.getOfferId());
        oldBid.setStatus(bid.getStatus());
        oldBid.setPrice(bid.getPrice());
        bidRepository.save(oldBid);
    }


    public List<Bid> getBidsByOfferId(Integer offerId) {
        if(!offerRepository.existsById(offerId)) {
            throw new ApiException("offer dose not exist");
        }
        List<Bid> bidList=bidRepository.findByOfferId(offerId);
        if (bidList == null) {
            throw new ApiException("Offer ID not found");
        }
        if(bidList.isEmpty()){
            throw new ApiException("bids not found in this offer id");
        }

        return bidList;
    }


    public List<Bid> showAcceptedBidsByDriverId(Integer driverId) {
        if(!driversRepository.existsById(driverId)) {
            // Driver does not exist
            throw new ApiException("Driver does not exist");
        }
        List<Bid>bidList=bidRepository.findByDriverIdAndStatus(driverId, "accepted");
        if (bidList.isEmpty()) {
            throw new ApiException("dont have any bid acceptable");
        }
        if(bidList==null){
            throw new ApiException("it is a null bid");
        }
        return bidList;
    }



    public void ChoseBidByBidId( Integer bidId){
       Bid bid1=getBidByBidId(bidId);
       if(bid1==null){

           throw new ApiException("bid id not found");
       }
       if(bid1.getStatus().equalsIgnoreCase("accepted")){
           throw new ApiException(" bid already accepted");
       }
            List<Bid> bids= getBidsByOfferId(bid1.getOfferId());
            for (Bid bid : bids) {
                if(bidId.equals(bid.getId())){
                    bid.setStatus("accepted");
                }
                else{
                bid.setStatus("rejected");

            }

        }bidRepository.saveAll(bids);
    }


    public Bid showAcceptBidByOfferId(Integer offerId){
        if(offerRepository.existsById(offerId)){
            List<Bid> bidList=getBidsByOfferId(offerId);
          for(int i = 0 ; i<bidList.size() ; i++){
              if(bidList.get(i).getStatus().equalsIgnoreCase("accepted")){
                  return bidList.get(i);
              }
          }
        }
        return null;
    }


    public String getUserRateById(Integer userId){
        User user=userRepository.findUserById(userId);
        if(user==null){
            return "users id not foud";
        }
        String rating =" "+user.getRate();
        return rating;
    }







}//end of class