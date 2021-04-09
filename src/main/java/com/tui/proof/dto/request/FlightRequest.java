package com.tui.proof.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Objects;

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
    private Integer infants;

    @Getter
    @Setter
    private Integer children;

    @Getter
    @Setter
    private Integer adults;

    public FlightRequest(String airportOrigin, String airportDestination, String dateFrom, String dateTo, Integer infants, Integer children, Integer adults) {
        this.airportOrigin = airportOrigin;
        this.airportDestination = airportDestination;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.infants = infants;
        this.children = children;
        this.adults = adults;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlightRequest that = (FlightRequest) o;
        return Objects.equals(airportOrigin, that.airportOrigin) && Objects.equals(airportDestination, that.airportDestination) && Objects.equals(dateFrom, that.dateFrom) && Objects.equals(dateTo, that.dateTo) && Objects.equals(infants, that.infants) && Objects.equals(children, that.children) && Objects.equals(adults, that.adults);
    }

    @Override
    public int hashCode() {
        return Objects.hash(airportOrigin, airportDestination, dateFrom, dateTo, infants, children, adults);
    }

    @Override
    public String toString() {
        return "FlightRequest{" +
                "airportOrigin='" + airportOrigin + '\'' +
                ", airportDestination='" + airportDestination + '\'' +
                ", dateFrom='" + dateFrom + '\'' +
                ", dateTo='" + dateTo + '\'' +
                ", infants=" + infants +
                ", children=" + children +
                ", adults=" + adults +
                '}';
    }
}
