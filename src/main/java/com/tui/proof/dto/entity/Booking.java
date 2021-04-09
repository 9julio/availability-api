package com.tui.proof.dto.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

// This class simulate the entity of Databases
public class Booking {

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String lastName;

    @Getter
    @Setter
    private String address;

    @Getter
    @Setter
    private String postalCode;

    @Getter
    @Setter
    private String country;

    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private List<String> telephones;

    @Getter
    @Setter
    private List<Flight> flights;

    public Booking(String name, String lastName, String address, String postalCode, String country, String email, List<String> telephones, List<Flight> flights) {
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.postalCode = postalCode;
        this.country = country;
        this.email = email;
        this.telephones = telephones;
        this.flights = flights;
    }

}
