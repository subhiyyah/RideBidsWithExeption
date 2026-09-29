package com.example.drivers.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor

public class Vehicle {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;


    @NotEmpty(message = "name can not be null or empty")
    @Pattern(
            regexp = "^[\\p{L}]+$",
            message = "Name must contain letters only"
    )
    @Column(columnDefinition = "varchar(20) not null")
    private String name;



    @NotEmpty(message = "model can not be null or empty")
    @Pattern(
            regexp = "^[\\p{L}0-9]+$",
            message = "Model must contain letters and numbers only"
    )
    @Column(columnDefinition = "varchar(20) not null")
    private String model;



     @NotEmpty(message = "productionYear can not be null or empty")
    @Pattern(
            regexp = "^(19|20)\\d{2}$",
            message = "Production year must be a valid year in the format YYYY"
    )
     @Column(columnDefinition = "varchar(4) not null")
    private String productionYear;



     @NotNull(message = "vehicleCapacity can not be null")
     @Positive(message = "vehicleCapacity must be a positive number")
     @Min(value = 1, message = "vehicleCapacity must be at least 1")
     @Column(columnDefinition = "int")
    private Integer vehicleCapacity;



     @Min(value = 0, message = "vehicleRate must be a non-negative number")
     @Max(value=5, message = "vehicleRate must be between 0 and 5")
    @Column(columnDefinition = "int")
    private Integer vehicleRate;

     @NotEmpty(message = "color can not be null or empty")
     @Column(columnDefinition = "varchar(20) not null")
     private String color;


     @AssertTrue(message = "eligible must be true or false")
    @Column(columnDefinition = "boolean")
    private Boolean eligible;


}