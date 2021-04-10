package com.tui.proof.dto.entity;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

// This class simulate the entity of Databases
public class Flight {

    @Getter
    @Setter
    private String airportOrigin;

    @Getter
    @Setter
    private String airportDestination;

    @Getter
    @Setter
    private Date dateFrom;

    @Getter
    @Setter
    private Date dateTo;

    @Getter
    @Setter
    private int infants;

    @Getter
    @Setter
    private int children;

    @Getter
    @Setter
    private int adults;

    @Getter
    @Setter
    private String company;

    @Getter
    @Setter
    private String flightNumber;

    @Getter
    @Setter
    private Date date;

    @Getter
    @Setter
    private Date hour;

    @Getter
    @Setter
    private BigDecimal price;

    @Getter
    @Setter
    private Date creationDateInTheBooking;

    @Getter
    @Setter
    private Date confirmationDateInTheBooking;

    public Flight(String airportOrigin,
                  String airportDestination,
                  Date dateFrom,
                  Date dateTo,
                  int infants,
                  int children,
                  int adults,
                  String company,
                  String flightNumber,
                  Date date,
                  Date hour,
                  BigDecimal price,
                  Date creationDateInTheBooking,
                  Date confirmationDateInTheBooking) {
        this.airportOrigin = airportOrigin;
        this.airportDestination = airportDestination;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.infants = infants;
        this.children = children;
        this.adults = adults;
        this.company = company;
        this.flightNumber = flightNumber;
        this.date = date;
        this.hour = hour;
        this.price = price;
        this.creationDateInTheBooking = creationDateInTheBooking;
        this.confirmationDateInTheBooking = confirmationDateInTheBooking;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return infants == flight.infants && children == flight.children && adults == flight.adults && Objects.equals(airportOrigin, flight.airportOrigin) && Objects.equals(airportDestination, flight.airportDestination) && Objects.equals(dateFrom, flight.dateFrom) && Objects.equals(dateTo, flight.dateTo) && Objects.equals(company, flight.company) && Objects.equals(flightNumber, flight.flightNumber) && Objects.equals(date, flight.date) && Objects.equals(hour, flight.hour) && Objects.equals(price, flight.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(airportOrigin, airportDestination, dateFrom, dateTo, infants, children, adults, company, flightNumber, date, hour, price);
    }

    @Override
    public String toString() {
        return "Flight{" +
                "airportOrigin='" + airportOrigin + '\'' +
                ", airportDestination='" + airportDestination + '\'' +
                ", dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                ", infants=" + infants +
                ", children=" + children +
                ", adults=" + adults +
                ", company='" + company + '\'' +
                ", flightNumber='" + flightNumber + '\'' +
                ", date=" + date +
                ", hour=" + hour +
                ", price=" + price +
                '}';
    }
}
