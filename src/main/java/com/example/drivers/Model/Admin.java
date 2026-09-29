package com.example.drivers.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data@AllArgsConstructor
@Entity
@NoArgsConstructor
@Table(name="admin")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

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
    private int age;

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
    private String email;
}