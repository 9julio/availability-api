package com.tui.proof.dto.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return Objects.equals(name, booking.name) && Objects.equals(lastName, booking.lastName) && Objects.equals(address, booking.address) && Objects.equals(postalCode, booking.postalCode) && Objects.equals(country, booking.country) && Objects.equals(email, booking.email) && Objects.equals(telephones, booking.telephones) && Objects.equals(flights, booking.flights);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lastName, address, postalCode, country, email, telephones, flights);
    }

    @Override
    public String toString() {
        return "Booking{" +
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
