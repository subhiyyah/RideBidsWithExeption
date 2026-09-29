package com.example.drivers.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Complaint {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "ticketId can not be null")
    @Column(columnDefinition = "int not null")
    private Integer ticketId;
    @NotEmpty(message = "role can not be null or empty")
    @Pattern(regexp = "(?i)^(user|admin|driver)$",
            message = "Role must be user, admin, or driver")
    @Column(columnDefinition = "varchar(50)")
    private String role;

    @NotEmpty(message = "description can not be null or empty")
    @Column(columnDefinition = "varchar(255)")
    private String Description;

    @Pattern(regexp = "(?i)^(pending|resolved|closed)$",
            message = "Status must be pending, resolved, or closed")
    @Column(columnDefinition = "varchar(50)")
    private String status;



}