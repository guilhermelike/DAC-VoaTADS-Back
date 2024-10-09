package com.voatads.customer.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "address", schema = "customer")
public class Address {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "street")
    private String street;

    @Column(name = "complement")
    private String complement;

    @Column(name = "zip")
    private String zip;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;
}
