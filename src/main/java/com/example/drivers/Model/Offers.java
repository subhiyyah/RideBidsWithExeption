package com.example.drivers.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Offers {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;



    @Positive(message = "userId must be a positive number")
    @Column(columnDefinition = "int not null")
    private Integer userId;

    @NotNull(message = "rangePriceFrom can not be null")
    @PositiveOrZero(message = "rangePriceFrom must be a positive number")
    @Column(columnDefinition = "double not null")
    private Double rangePriceFrom;

    @NotNull(message = "rangePriceTo can not be null")
    @PositiveOrZero(message = "rangePriceTo must be a positive number")
    @Column(columnDefinition = "double not null")
    private Double rangePriceTo;


    @NotEmpty(message = "locationFrom can not be null or empty")
    @Column(columnDefinition = "varchar(200) not null")
    private String locationFrom;

    @NotEmpty(message = "locationTo can not be null or empty")
    @Column(columnDefinition = "varchar(200) not null")
    private String locationTo;

   @NotNull(message = "distance can not be null")
    @Positive(message = "distance must be a positive number")
    @Column(columnDefinition = "double not null")
    private Double Distance;

    @NotNull(message = "TripStartTime can not be null")
    @Column(columnDefinition = "time not null")
    private LocalTime tripStartTime;

    @NotNull(message = "TripArrivalTime can not be null")
    @Column(columnDefinition = "time not null")
    private LocalTime tripArrivalTime;

//    private Double rangeTripTime;

    @NotNull(message = "AcceptableDelay can not be null")
    @PositiveOrZero(message = "AcceptableDelay must be a positive number or zero")
    @Column(columnDefinition = "int not null")
    private Integer acceptableDelay;

    @NotNull(message = "StartDate can not be null")
    @FutureOrPresent(message = "StartDate must be today or in the future")
    @Column(columnDefinition = "date not null")
    private LocalDate startDate;

    @NotNull(message = "EndDate can not be null")
    @FutureOrPresent(message = "EndDate must be in the future or today")
    @Column(columnDefinition = "date not null")
    private LocalDate endDate;

    @NotNull(message = "working Day Per Week can not be null")
    @Min(value = 1, message = "working Day Per Week must be at least 1")
    @Max(value = 7, message = "working Day Per Week must be at most 7")
    @Column(columnDefinition = "int not null")
    private Integer workingDayPerWeek;


    @NotBlank(message = "Enter off days or NONE")
    @Pattern(
            regexp = "(?i)^(?:NONE|(?:SATURDAY|SUNDAY|MONDAY|TUESDAY|WEDNESDAY|THURSDAY|FRIDAY)(?:,(?:SATURDAY|SUNDAY|MONDAY|TUESDAY|WEDNESDAY|THURSDAY|FRIDAY))*)$",
            message = "Enter weekdays separated by commas, or NONE"
    )
    @Column(columnDefinition = "varchar(200) not null")

    private String offDays;

//    @NotEmpty(message = "Status can not be null")
    @Pattern(
            regexp = "(?i)^(pending|accepted|rejected)$",
            message = "Status must be either 'pending', 'accepted', or 'rejected'"
    )
    private String status = "pending"; // Default value is "pending"
}