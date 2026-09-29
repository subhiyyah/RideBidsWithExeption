package com.example.drivers.Repository;

import com.example.drivers.Model.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComplaintRepsitory extends JpaRepository<Complaint, Integer> {
    Complaint findComplaintById(Integer id);
}