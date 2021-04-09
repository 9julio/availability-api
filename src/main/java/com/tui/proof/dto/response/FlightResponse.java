package com.tui.proof.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

public class FlightResponse {

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

    public FlightResponse(String company, String flightNumber, Date date, Date hour, BigDecimal price) {
        this.company = company;
        this.flightNumber = flightNumber;
        this.date = date;
        this.hour = hour;
        this.price = price;
    }

}
