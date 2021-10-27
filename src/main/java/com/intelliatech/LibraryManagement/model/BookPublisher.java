package com.intelliatech.LibraryManagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class BookPublisher {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "publisher_id")
    private long PublisherId;
    @Column(name = "publisher_name")
    private String publisherName;
    @Column(name = "email")
    private String email;
    @OneToOne
    private Address address;

}
