package com.example.drivers.Service;

import com.example.drivers.ApiResponse.ApiException;
import com.example.drivers.ApiResponse.ApiResponse;
import com.example.drivers.Model.*;
import com.example.drivers.Repository.ComplaintRepsitory;
import com.example.drivers.Repository.DriversRepository;
import com.example.drivers.Repository.TicketRepository;
import com.example.drivers.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Driver;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DriversService {
    private final DriversRepository driversRepository;
    private final OffersService offersService;
    private final BidService bidService;
    private final UserRepository userRepository;
    private final TicketRepository ticketRepository;
    private final ComplaintRepsitory complaintRepsitory;

    public void addDrivers(Drivers driver) {

        driver.setName(driver.getName());
        driver.setEmail(driver.getEmail());
        driver.setPhone(driver.getPhone());
        driver.setNationality(driver.getNationality());


        driver.setActive("pending");
        driver.setCompensation(0.0);
        driver.setNumberOfTrips(0);
        driver.setTotalProfits(0.0);
        driver.setNumberOfCancellation(0);
        driver.setRate(0.0);
        driver.setNumberOfRate(0);


        driversRepository.save(driver);
    }

    public Drivers getDriverById(Integer id) {
        // Implementation for getting a driver by ID
        return driversRepository.findDriversById(id);
    }

    public List<Drivers> getAllDrivers() {
        return driversRepository.findAll();
    }

    public void updateDrivers(Integer id, Drivers driver) {
        Drivers driver1 = driversRepository.findById(id).orElse(null);
        if (driver1 == null) {
            throw new ApiException("id not found");
        } else {
            driver1.setName(driver.getName());
            driver1.setAge(driver.getAge());
            driver1.setPhone(driver.getPhone());
            driver1.setRate(driver.getRate());
            driver1.setNationality(driver.getNationality());
            driver1.setActive(driver.getActive());
            driver1.setNumberOfCancellation(driver.getNumberOfCancellation());
            driver1.setCompensation(driver.getCompensation());
            driver1.setTotalProfits(driver.getTotalProfits());
            driver1.setNumberOfTrips(driver.getNumberOfTrips());
            driversRepository.save(driver1);
        }

    }

    public void deleteDrivers(Integer id) {
        Drivers driver = driversRepository.findDriversById(id);
        if (driver == null) {
              throw new ApiException("id not found");  }
                    driversRepository.delete(driver);
    }




    public List<Offers>showOffers(){
      List <Offers> offersList= offersService.getAllOffers();
       if(offersList.isEmpty()){
           throw new ApiException("offer list is empty");
       }
        if(offersList==null){
            throw new ApiException("offer list is null ");
        }
        return offersList;
    }

    public void addBids(Integer driverId , Bid bids){
        if(!driversRepository.existsById(driverId)){

          throw new ApiException("driver id does not exists");
        }
        if(!driverId.equals(bids.getDriverId())){
            throw new ApiException("driver id not equal with bid driver id");

        }
        bidService.addBid(bids.getOfferId(),bids);

    }



    public void cancelTicketByDriver(Integer driverId, Integer ticketId) {
        Ticket ticket = ticketRepository.findTicketById(ticketId);


        if (ticket == null) {
            throw new ApiException("Ticket not found");
        }

        if (ticket.getDriverId() == null || !ticket.getDriverId().equals(driverId)) {
            throw new ApiException("Unauthorized action for this ticket");
        }

        if ("CANCELLED".equalsIgnoreCase(ticket.getStatus())) {
            throw new ApiException("Ticket is already cancelled");
        }

        User user = userRepository.findUserById(ticket.getUserId());
        if (user == null) {
            throw new ApiException("User not found");
        }
        Drivers drivers=driversRepository.findDriversById(driverId);
        if (drivers == null) {
            throw new ApiException("Driver not found");
        }



        // إعادة المبلغ كاملاً للمستخدم
        double fullRefund = ticket.getFinalPrice();
        user.setBalance(user.getBalance() + fullRefund);
        ticket.setStatus("CANCELLED");



        // اضافات عدد مرات الالغاء للسائق
        //تقليل عدد الرحلات للسائق
        drivers.setNumberOfCancellation(drivers.getNumberOfCancellation()+1);
        drivers.setNumberOfTrips(drivers.getNumberOfTrips()-1);
        drivers.setCompensation(ticket.getFinalPrice()+drivers.getCompensation());

        driversRepository.save(drivers);
        userRepository.save(user);
        ticketRepository.save(ticket);


    }


    public void addUserRating(Integer driverId, Integer ticketId, Double rating, String comment) {
        // 1. التحقق من وجود التذكرة
        Ticket ticket = ticketRepository.findTicketById(ticketId);
        if (ticket == null) {
            throw new ApiException("Ticket not found");

        }

        // 2. التحقق من أن التذكرة تابعة للسائق
        if (ticket.getDriverId() == null || !ticket.getDriverId().equals(driverId)) {
            throw new ApiException( "Unauthorized action for this ticket");

        }

        // 3. التحقق من عدم تقييم المستخدم سابقاً في هذه التذكرة
        if (ticket.getDriverRatingForUser() != null) {
            throw new ApiException("You have already rated the user for this ticket");

        }
        // 1. التحقق المبدئي من نطاق التقييم
        if (rating == null || rating < 1.0 || rating > 5.0) {
         throw new ApiException("Rating must be between 1.0 and 5.0");
        }

        // 4. حفظ تقييم السائق للمستخدم في التذكرة
        ticket.setDriverRatingForUser(rating);
        ticket.setDriverCommentForUser(comment);
        ticketRepository.save(ticket);

        // 5. جلب المستخدم وتحديث تقييمه بالمعادلة البسيطة
        User user = userRepository.findUserById(ticket.getUserId());
        if (user == null) {
            throw new ApiException("User not found");

        }

        Integer previousRatings = user.getNumberOfRate();
        if (previousRatings == null) {
            previousRatings = 0;
        }

        double currentRating = 0.0;
        if (user.getRate() != null) {
            currentRating = user.getRate();
        }

        double newAverageRating =
                ((currentRating * previousRatings) + rating) / (previousRatings + 1);

        user.setRate(newAverageRating);
        user.setNumberOfRate(previousRatings + 1);

        userRepository.save(user);

    }




    public void addComplaintByDriver(Integer driverId, Integer ticketId, String description) {
        // 1. التحقق من وجود التذكرة
        Ticket ticket = ticketRepository.findTicketById(ticketId);
        if (ticket == null) {
            throw new ApiException("Ticket not found");
        }

        // 2. التحقق من أن التذكرة تابعة للسائق
        if (ticket.getDriverId() == null || !ticket.getDriverId().equals(driverId)) {
            throw new ApiException("Unauthorized action for this ticket");
        }
        if (description == null || description.trim().isEmpty()) {
            throw new ApiException("Description cannot be empty");
        }


        // 3. إنشاء كيان الشكوى وحفظه
        Complaint complaint = new Complaint();
        complaint.setTicketId(ticketId);
        complaint.setRole("driver");
        complaint.setDescription(description);
        complaint.setStatus("pending");

        complaintRepsitory.save(complaint);


    }


    public Object getDriverRateById(Integer driverId){
        Drivers driver=driversRepository.findDriversById(driverId);
        if(driver==null){
          throw new ApiException("driver id not found");

        }
        String rating=" " +driver.getRate();
        return rating;
    }

    public Object getUserRateById(Integer userId){
        User user=userRepository.findUserById(userId);
        if(user==null){
            throw new ApiException( "users id not foud");
        }
        String rating =" "+user.getRate();
        return rating;
    }


}