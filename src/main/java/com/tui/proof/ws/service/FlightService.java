package com.tui.proof.ws.service;

import com.tui.proof.dto.entity.Flight;
import com.tui.proof.dto.response.FlightResponse;
import com.tui.proof.utils.Utils;
import com.tui.proof.ws.dao.FlightDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class FlightService {

    @Autowired
    public FlightDAO flightDAO;

    public List<FlightResponse> getAvailabilityFlights(String airportOrigin, String airportDestination, Date dateFrom, Date dateTo, Integer infants, Integer children, Integer adults) {
        List<FlightResponse> response = new ArrayList<FlightResponse>();

        List<Flight> flights = flightDAO.getAvailabilityFlights(airportOrigin, airportDestination, dateFrom, dateTo, infants, children, adults);

        // Map Entity DTO to Response DTO
        for (Flight flight : flights) {
            response.add(new FlightResponse(
                    flight.getAirportOrigin(),
                    flight.getAirportDestination(),
                    Utils.convertDateToDateFormat(flight.getDateFrom()),
                    Utils.convertDateToDateFormat(flight.getDateTo()),
                    flight.getInfants(),
                    flight.getChildren(),
                    flight.getAdults(),
                    flight.getCompany(),
                    flight.getFlightNumber(),
                    Utils.convertDateToDateFormat(flight.getDate()),
                    Utils.convertDateToHourFormat(flight.getHour()),
                    flight.getPrice()
            ));
        }

        return response;
    }

}
