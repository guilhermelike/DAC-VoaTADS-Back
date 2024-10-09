package com.voatads.customer.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customer", schema = "customer")
@Data
public class Customer {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    @JoinColumn(name="id_address")
    private Address address;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "miles")
    private double miles;
}
