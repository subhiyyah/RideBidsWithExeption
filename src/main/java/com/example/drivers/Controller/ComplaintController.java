package com.example.drivers.Controller;

import com.example.drivers.ApiResponse.ApiResponse;
import com.example.drivers.Model.Complaint;
import com.example.drivers.Service.ComplaintService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/complaints")
@RequiredArgsConstructor

public class ComplaintController {
    private final ComplaintService complaintService;
    @GetMapping("/get")
    public ResponseEntity<?> getAllComplaints() {
        return ResponseEntity.status(200).body(new ApiResponse(""+ complaintService.getAllComplaints()));
    }


    @PostMapping("/add")
    public ResponseEntity<?> addComplaint(@RequestBody @Valid Complaint complaint ) {
        complaintService.addComplaint(complaint);
        return ResponseEntity.status(200).body(new ApiResponse("Complaint added"));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateComplaint(@PathVariable Integer id, @RequestBody @Valid Complaint complaint) {
    complaintService.updateComplaint(id, complaint);
            return ResponseEntity.status(200).body(new ApiResponse("Complaint updated successfully"));
        }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteComplaint(@PathVariable Integer id) {
      complaintService.deleteComplaint(id);
            return ResponseEntity.status(200).body(new ApiResponse("Complaint deleted successfully"));
        }



}//end of class