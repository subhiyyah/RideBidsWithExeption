package com.example.drivers.Service;

import com.example.drivers.ApiResponse.ApiException;
import com.example.drivers.Model.Vehicle;
import com.example.drivers.Repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class VehicleService {

    private final VehicleRepository vehicleRepository;


    public List<Vehicle> getAllVehicles() {
       return vehicleRepository.findAll();
    }


    public boolean addVehicle(Vehicle vehicle) {

            int year = Integer.parseInt(vehicle.getProductionYear());

            if (year > 2024) {
                vehicle.setVehicleRate(5);
            } else if (year >= 2021) {
                vehicle.setVehicleRate(4);
            } else if (year >= 2017) {
                vehicle.setVehicleRate(3);
            } else {
                vehicle.setVehicleRate(2);
            }
            vehicleRepository.save(vehicle);
            return true;
    }


    public boolean checkVehicleExists(Integer vehicleId) {
        return vehicleRepository.existsById(vehicleId);
    }

    public void deleteVehicle(Integer id) {
        if(vehicleRepository.existsById(id)) {
            vehicleRepository.deleteById(id);
        }
        throw new ApiException("id not found");

    }

    public void updateVehicle(Integer id, Vehicle vehicle) {
        if(vehicleRepository.existsById(id)) {
            Vehicle existingVehicle = vehicleRepository.findById(id).orElse(null);
            if(existingVehicle != null) {
                existingVehicle.setName(vehicle.getName());
                existingVehicle.setModel(vehicle.getModel());
                existingVehicle.setProductionYear(vehicle.getProductionYear());
                existingVehicle.setVehicleCapacity(vehicle.getVehicleCapacity());
                existingVehicle.setVehicleRate(vehicle.getVehicleRate());
                existingVehicle.setEligible(vehicle.getEligible());
                existingVehicle.setColor(vehicle.getColor());

                vehicleRepository.save(existingVehicle);
            }
        }
        throw new ApiException("id not found ");
    }

}