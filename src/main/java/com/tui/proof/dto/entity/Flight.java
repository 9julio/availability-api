package com.tui.proof.dto.entity;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

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

    public Flight(String airportOrigin, String airportDestination, Date dateFrom, Date dateTo, int infants, int children, int adults, String company, String flightNumber, Date date, Date hour, BigDecimal price) {
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
    }
}
