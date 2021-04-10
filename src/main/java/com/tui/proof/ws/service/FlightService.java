package com.tui.proof.ws.service;

import com.tui.proof.dto.entity.Booking;
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

import java.util.Arrays;
import java.util.List;

@Service
public class FlightService {

    @Autowired
    public FlightDAO flightDAO;

    @Autowired
    public BookingDAO bookingDAO;

    public List<FlightResponse> getAvailabilityFlights(FlightRequest flightRequest) {

        // TODO: In this search, if will be in Database the parameters will be a list for more possibilities to search,
        //  but for simple the code to the test, this is a unique element
        List<Flight> flights = flightDAO.getAvailabilityFlights(
                flightRequest.getAirportOrigin(),
                flightRequest.getAirportDestination(),
                DateUtils.convertStringDateToDateWithFormat(flightRequest.getDateFrom()),
                DateUtils.convertStringDateToDateWithFormat(flightRequest.getDateTo()),
                flightRequest.getInfants(),
                flightRequest.getChildren(),
                flightRequest.getAdults());

        // Map Entity DTO to Response DTO
        return MapperUtils.mapFlightEntityListToFlightResponseList(flights);
    }

    public List<BookingResponse> getBookings(BookingRequest request) {

        // TODO: In this search, if will be in Database the parameters will be a list for more possibilities to search,
        //  but for simple the code to the test, this is a unique element
        List<Booking> bookingList = bookingDAO.getBookings(
                request.getName(),
                request.getLastName(),
                request.getAddress(),
                request.getPostalCode(),
                request.getCountry(),
                request.getEmail(),
                request.getTelephones(),
                "airportOrigin",
                "airportDestination",
                "dateFrom",
                "dateTo",
                1,
                1,
                1
        );

        return MapperUtils.mapBookingEntityListToBookingResponseList(bookingList);
    }

    public List<BookingResponse> getAllBookings() {
        return MapperUtils.mapBookingEntityListToBookingResponseList(bookingDAO.getAllBookings());
    }

    public void addBooking(BookingRequest request) {
        // TODO: Do all the business logic for verify that is correct.
        bookingDAO.addBooking(MapperUtils.mapBookingRequestToBookingEntity(request));
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
