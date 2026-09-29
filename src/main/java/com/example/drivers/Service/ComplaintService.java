package com.example.drivers.Service;

import com.example.drivers.ApiResponse.ApiException;
import com.example.drivers.Model.Complaint;
import com.example.drivers.Repository.ComplaintRepsitory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ComplaintService {

    private final ComplaintRepsitory complaintRepsitory;

    public List<Complaint> getAllComplaints() {
        return complaintRepsitory.findAll();
    }

    public void addComplaint(Complaint complaint) {
        complaint.setStatus("pending");
        complaintRepsitory.save(complaint);
    }

    public void updateComplaint(Integer id, Complaint complaint) {
        Complaint oldComplaint = complaintRepsitory.findComplaintById(id);
        if (oldComplaint == null) {
            throw new ApiException("complaint id not found");
        }
        oldComplaint.setDescription(complaint.getDescription());
        oldComplaint.setStatus(complaint.getStatus());
        oldComplaint.setTicketId(complaint.getTicketId());
        oldComplaint.setRole(complaint.getRole());

        complaintRepsitory.save(oldComplaint);
    }


    public void deleteComplaint(Integer id) {
        Complaint oldComplaint = complaintRepsitory.findComplaintById(id);
        if (oldComplaint == null) {
            throw new ApiException("id not found");
        }
        complaintRepsitory.delete(oldComplaint);
    }
}//end of class