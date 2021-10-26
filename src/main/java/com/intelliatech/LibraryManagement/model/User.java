package com.intelliatech.LibraryManagement.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private long userId;
    @Column(name = "username",unique = true)
    private String username;
    @Column(name = "username_password")
    private String password;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "user_email",unique = true)
    private String email;
    @Column(name = "user_mobile_number",unique = true)
    private String mobileNumber;
    @Column(name = "user_gender")
    private String gender;
    @Column(name = "user_date_of_birth")
    private String dateOfBirth;
    @Column(name = "user_registration_date")
    private String registrationDate;
    @Column(name = "active_status")
    private boolean isActive;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Address address;



}
