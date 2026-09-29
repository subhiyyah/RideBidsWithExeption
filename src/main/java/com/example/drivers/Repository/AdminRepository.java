package com.example.drivers.Repository;

import com.example.drivers.Model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository  extends JpaRepository<Admin,Integer> {
    Admin findAdminById(Integer id);
}