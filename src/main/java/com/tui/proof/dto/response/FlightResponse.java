package com.tui.proof.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlightResponse that = (FlightResponse) o;
        return infants == that.infants && children == that.children && adults == that.adults && Objects.equals(airportOrigin, that.airportOrigin) && Objects.equals(airportDestination, that.airportDestination) && Objects.equals(dateFrom, that.dateFrom) && Objects.equals(dateTo, that.dateTo) && Objects.equals(company, that.company) && Objects.equals(flightNumber, that.flightNumber) && Objects.equals(date, that.date) && Objects.equals(hour, that.hour) && Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(airportOrigin, airportDestination, dateFrom, dateTo, infants, children, adults, company, flightNumber, date, hour, price);
    }

    @Override
    public String toString() {
        return "FlightResponse{" +
                "airportOrigin='" + airportOrigin + '\'' +
                ", airportDestination='" + airportDestination + '\'' +
                ", dateFrom='" + dateFrom + '\'' +
                ", dateTo='" + dateTo + '\'' +
                ", infants=" + infants +
                ", children=" + children +
                ", adults=" + adults +
                ", company='" + company + '\'' +
                ", flightNumber='" + flightNumber + '\'' +
                ", date='" + date + '\'' +
                ", hour='" + hour + '\'' +
                ", price=" + price +
                '}';
    }
}
