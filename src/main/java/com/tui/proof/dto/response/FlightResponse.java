package com.tui.proof.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

public class FlightResponse {

    @Getter
    @Setter
    private String airportOrigin;

    @Getter
    @Setter
    private String airportDestination;

    @Getter
    @Setter
    private String dateFrom;

    @Getter
    @Setter
    private String dateTo;

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
    private String date;

    @Getter
    @Setter
    private String hour;

    @Getter
    @Setter
    private BigDecimal price;

    public FlightResponse(String airportOrigin, String airportDestination, String dateFrom, String dateTo, int infants, int children, int adults, String company, String flightNumber, String date, String hour, BigDecimal price) {
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
