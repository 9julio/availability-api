package com.tui.proof.ws.dao;

import com.tui.proof.dto.entity.Flight;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// This class is use to simulate a DAO for Database
@Repository
public class FlightDAO {

    @Getter
    @Setter
    private List<Flight> flights = new ArrayList<Flight>(){{
        add(new Flight("Madrid", "London", new Date(), new Date(), 0, 0, 1, "myCompany", "number1", new Date(), new Date(), BigDecimal.TEN));
        add(new Flight("London", "Madrid", new Date(), new Date(), 1, 4, 34, "myCompany2", "number2", new Date(), new Date(), BigDecimal.ONE));
    }};


    public List<Flight> getAvailabilityFlights(String airportOrigin, String airportDestination, Date dateFrom, Date dateTo, int infants, int children, int adults) {
        // TODO: In this step will be the communication with the Database for filters to get the availabilities flights with the criteria filters.
        return getFlights();
    }
}
