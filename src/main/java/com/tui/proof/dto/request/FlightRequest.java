package com.tui.proof.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class FlightRequest {

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

    public FlightRequest(String airportOrigin, String airportDestination, String dateFrom, String dateTo, int infants, int children, int adults) {
        this.airportOrigin = airportOrigin;
        this.airportDestination = airportDestination;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.infants = infants;
        this.children = children;
        this.adults = adults;
    }

}
