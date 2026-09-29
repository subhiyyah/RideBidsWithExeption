package com.example.drivers.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Builder
@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Drivers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
//
//    @Positive(message = "OfferId must be a positive number")
//    @Column(columnDefinition = "int ")
//    private Integer OfferId;

    @NotEmpty(message = "name can not be null")
    @Size(min=1, message = "name must be greater than 1 character")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Name must contain letters only")
    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String name;

    @NotNull(message = "age can not be a null value")
    @Min(value = 18, message = "min age in this system is 18")
    @Positive(message = "age must be a number and positive")
    @Column(columnDefinition = "INT NOT NULL")
    private Integer age;


    @NotBlank(message = "phone cannot be blank")
    @Pattern(
            regexp = "05[0-9]{8}",
            message = "phone must start with 05 and contain 10 digits"
    )
    @Column(columnDefinition = "varchar(10) not null")
    private String phone;

    @NotEmpty(message="email can not be empty")
    @Email(message= " email must have a @ ")
    @Column(columnDefinition = "varchar(100) not null")
    private String Email;


    @NotEmpty(message = "nationality can not be null")
    @Size(min=2, message = "nationality must be greater than 1 character")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Nationality must contain letters only")
    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String nationality;

    @Pattern(
            regexp = "(?i)^(active|inactive|pending)$",
            message = "active must be either 'active', 'inactive', or 'pending'"
    )
    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String active="active";


    @PositiveOrZero
    @Min(value = 0 ,message = "min value is 0")
    @Max(value = 5,message = "max value is 5")
    @Column(columnDefinition = "double not null ")
    private Double rate;

    @PositiveOrZero(message = "NumberOfTrips must be a positive number or zero")
    @Column(columnDefinition = "int not null ")
    private Integer NumberOfTrips;

@PositiveOrZero(
        message = "compensation must be a positive number or zero")
    @Column(columnDefinition = "double not null ")
    private Double compensation=0.0;

    @PositiveOrZero(message = "NumberOfCancellation must be a positive number or zero")
    @Column(columnDefinition = "int not null ")
    private Integer NumberOfCancellation=0;


    @PositiveOrZero(message = "totalProfits must be a positive number or zero")
    @Column(columnDefinition = "double not null ")
    private Double totalProfits=0.0;


    @PositiveOrZero(message = "number of rate must be positive or zero")
    @Column(columnDefinition ="int not null" )
    private Integer numberOfRate=0;
}