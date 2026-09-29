package com.example.drivers.Controller;

import com.example.drivers.Model.Vehicle;
import com.example.drivers.Repository.VehicleRepository;
import com.example.drivers.Service.VehicleAiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/vehicle-ai")
@RequiredArgsConstructor
public class VehicleAiController {

    private final VehicleRepository vehicleRepository;
    private final VehicleAiService vehicleAiService;

    @GetMapping("/description/{vehicleId}")
    public String getDescription(@PathVariable Integer vehicleId) {
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Vehicle not found"
                ));

        return vehicleAiService.describeVehicle(vehicle);
    }
}