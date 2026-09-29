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
@Table(name = "users") // <-- أضف هذا السطر لتجنب التعارض مع الكلمات المحجوزة
public class User {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

//   @NotNull(message = "offersId can not be null value")
//   @Column(columnDefinition = "int not null")
//   private Integer offersId;

    @NotEmpty(message = "name can not be empty or null")
    @Size(max=20 , message = "max value of name is 20 character")
    @Pattern(
            regexp = "^[a-zA-Z]+$",
            message = "Username must contain letters only")

    @Column(columnDefinition = "varchar(20) not null")
    private String name;


    @NotNull(message = "age can not be null value")
    @Positive(message = "age must be a positive number")
    @Column(columnDefinition = "int not null")
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


    @PositiveOrZero
    @Builder.Default
    @Min(value = 0 ,message = "min value is 0")
    @Max(value = 5,message = "max value is 5")
    @Column(columnDefinition = "double not null ")
    private Double rate=0.0;


//    //patter for user of admin
//    @NotEmpty(message = "role can not be empty")
//    @Pattern(
//            regexp = "(?i)^(user|admin)$",
//            message = "Role must be either User or Admin"
//    )
//    @Column(columnDefinition = "varchar(20) not null")
//    private String role="user";

 @Pattern(
         regexp = "^(active|non-active|pending)$",
         message = "status must be active, non-active, or pending"
 )
 @Column(    columnDefinition = "varchar(10) not null")
    private String active;

    @Builder.Default
    @PositiveOrZero(message = "balance must be positive value")
    @Column(columnDefinition = "double not null")
    private Double balance=0.0;

    @Builder.Default
    @PositiveOrZero(message = "deposit must be a positive value ")
    @Column(columnDefinition = "double not null")
    private Double deposit=0.0;

    @Builder.Default
    @PositiveOrZero(message = "cancellation must pe a positive value ")
    @Column(columnDefinition = "int not null")
    private Integer numberOfCancellation=0;

    @Builder.Default
    @PositiveOrZero(message = "total payment must be a positive value or zero")
    @Column(columnDefinition = "double not null")

    private Double totalPayment=0.0;

    @Builder.Default
    @PositiveOrZero(message = "number of rate must be positive or zero")
    @Column(columnDefinition ="int not null" )
    private Integer numberOfRate=0;


}