package com.tui.proof.ws.service;

import com.tui.proof.dto.entity.Flight;
import com.tui.proof.dto.request.BookingRequest;
import com.tui.proof.dto.request.FlightRequest;
import com.tui.proof.dto.response.BookingResponse;
import com.tui.proof.dto.response.FlightResponse;
import com.tui.proof.utils.DateUtils;
import com.tui.proof.utils.MapperUtils;
import com.tui.proof.ws.dao.BookingDAO;
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

    @Autowired
    public BookingDAO bookingDAO;

    public List<FlightResponse> getAvailabilityFlights(String airportOrigin, String airportDestination, Date dateFrom, Date dateTo, Integer infants, Integer children, Integer adults) {
        List<FlightResponse> response = new ArrayList<FlightResponse>();

        List<Flight> flights = flightDAO.getAvailabilityFlights(airportOrigin, airportDestination, dateFrom, dateTo, infants, children, adults);

        // Map Entity DTO to Response DTO
        for (Flight flight : flights) {
            response.add(new FlightResponse(
                    flight.getAirportOrigin(),
                    flight.getAirportDestination(),
                    DateUtils.convertDateToDateFormat(flight.getDateFrom()),
                    DateUtils.convertDateToDateFormat(flight.getDateTo()),
                    flight.getInfants(),
                    flight.getChildren(),
                    flight.getAdults(),
                    flight.getCompany(),
                    flight.getFlightNumber(),
                    DateUtils.convertDateToDateFormat(flight.getDate()),
                    DateUtils.convertDateToHourFormat(flight.getHour()),
                    flight.getPrice()
            ));
        }

        return response;
    }

    public void addBooking(BookingRequest request) {
        // TODO: Do all the business logic for verify that is correct.
        bookingDAO.addBooking(MapperUtils.mapBookingRequestToBookingEntity(request));
    }

    public List<BookingResponse> getAllBookings() {
        return MapperUtils.mapBookingEntityListToBookingResponseList(bookingDAO.getAllBookings());
    }

    public void addFlightToBooking(Long bookingId, FlightRequest flightRequest) {
        // TODO: In this step must be search in Database the Booking with the Id exist and it is correct with the data,
        //  but in this case, I will add all the flights for the same Booking. In Database, the booking and flights will be join by a foreign key and
        //  Booking could be have 0 to a lot of Flights.

        bookingDAO.addFlightToBooking(bookingId, MapperUtils.mapFlightRequestToFlightEntity(flightRequest));
    }

    public void deleteAFlightInABooking(Long bookingId, Long flightId) {
        bookingDAO.deleteAFlightInABooking(bookingId, flightId);
    }
}
