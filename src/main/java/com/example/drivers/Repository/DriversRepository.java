package com.example.drivers.Repository;

import com.example.drivers.Model.Drivers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Driver;
@Repository
public interface DriversRepository extends JpaRepository<Drivers,Integer> {
    Drivers findDriversById(Integer Id);
}