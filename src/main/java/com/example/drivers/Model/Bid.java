package com.example.drivers.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor


public class Bid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @NotNull(message = "offerId can not be null")
    @Column(columnDefinition = "int not null")
    private Integer offerId;

//    @NotNull(message = "driverId can not be null")
    @Column(columnDefinition = "int not null")
    private Integer driverId;

//    @NotEmpty(message = "status can not be null or empty")
    @Pattern(regexp = "(?i)^(pending|accepted|rejected)$",
            message = "Status must be pending, accepted, or rejected")
    @Column(columnDefinition = "varchar(20) not null")
    private String status;

    @Positive(message = "price must be a positive number")
    @NotNull(message = "price can not be null")
    @Column(columnDefinition = "double not null")
    private Double price;




}