package com.tui.proof.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookingRequest that = (BookingRequest) o;
        return Objects.equals(name, that.name) && Objects.equals(lastName, that.lastName) && Objects.equals(address, that.address) && Objects.equals(postalCode, that.postalCode) && Objects.equals(country, that.country) && Objects.equals(email, that.email) && Objects.equals(telephones, that.telephones) && Objects.equals(flights, that.flights);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lastName, address, postalCode, country, email, telephones, flights);
    }

    @Override
    public String toString() {
        return "BookingRequest{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", country='" + country + '\'' +
                ", email='" + email + '\'' +
                ", telephones=" + telephones +
                ", flights=" + flights +
                '}';
    }
}
