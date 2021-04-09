package com.tui.proof.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class BookingRequest {

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
    private List<FlightRequest> flights;

    public BookingRequest(String name, String lastName, String address, String postalCode, String country, String email, List<String> telephones, List<FlightRequest> flights) {
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
