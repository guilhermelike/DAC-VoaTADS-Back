package com.voatads.customer.dto;

import java.io.Serializable;
import java.util.UUID;

public class CreateCustomerDTO implements Serializable {
    private UUID id;
    private String cpf;
    private String name;
    private String email;
    private String street;
    private String number;
    private String complement;
    private String zipCode;
    private String city;
    private String state;
    private Double miles;
    private String password;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Double getMiles() {
        return miles;
    }

    public void setMiles(Double miles) {
        this.miles = miles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}