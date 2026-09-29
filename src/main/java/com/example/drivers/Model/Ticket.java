package com.example.drivers.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer Id;

    @Positive(message = "userId must be a positive number")
    @NotNull(message = "userId can not be null")
    @Column(columnDefinition = "int not null")
    private Integer userId;

    @Positive(message = "userId must be a positive number")
    @NotNull(message = "userId can not be null")
    @Column(columnDefinition = "int not null")
    private Integer driverId;

    @Positive(message = "bidId must be a positive number")
    @NotNull(message = "bidId can not be null")
    @Column(columnDefinition = "int not null")
    private Integer bidId;

    @NotNull(message = "finalPrice can not be null")
    @Positive(message = "finalPrice must be a positive number")
    @Column(columnDefinition = "double not null")
    private Double finalPrice;

    @Pattern(regexp = "(?i)^(pending|completed|cancelled)$",
            message = "Status must be pending, completed, or cancelled")
    @Column(columnDefinition = "varchar(20) not null")
    private String status="pending";


    @Column(name = "booking_date", nullable = false, updatable = false)
    private LocalDateTime bookingDate = LocalDateTime.now();

    @NotNull(message = "Start date is required")
    @FutureOrPresent(message = "Start date must be today or in the future")
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @NotNull(message = "End date is required")
    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    // Rating & Feedback fields
    @DecimalMin(value = "1.0", message = "Rating must be at least 1.0")
    @Column(name = "user_rating_for_driver")
    private Double userRatingForDriver;

    @Column(name = "user_comment_for_driver", length = 500)
    private String userCommentForDriver;

    @DecimalMin(value = "1.0", message = "Rating must be at least 1.0")
    @Column(name = "driver_rating_for_user")
    private Double driverRatingForUser;

    @Column(name = "driver_comment_for_user", length = 500)
    private String driverCommentForUser;


}