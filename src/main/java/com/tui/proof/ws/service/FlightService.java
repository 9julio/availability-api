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
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Log4j2
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

        List<Flight> flightResponseList = new ArrayList<Flight>();
        // Calculate the 15 minutes
        // Note: It is possible to calculate here or in the Database (the results will be clean), but for the test do here.
        for (Flight flight : flights) {

            Date actualDate = new Date();

            long difference = DateUtils.calculateDifferenceInMinutesBetweenDates(flight.getCreationDateInTheBooking(), actualDate);
            if (difference >= 15) {
                log.error(String.format("The flight with number %s has more 15 minutes duration.", flight.getFlightNumber()));
            } else {
                flightResponseList.add(flight);
            }
        }

        log.debug(String.format("The search of availability flight get %s", flightResponseList));

        // If don't search anything, the search is OK but without elements. Return an OK code but empty body
        // Map Entity DTO to Response DTO
        return MapperUtils.mapFlightEntityListToFlightResponseList(flightResponseList);
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

        log.debug(String.format("The search of bookings get %s", bookingList));

        // If don't search anything, the search is OK but without elements. Return an OK code but empty body
        return MapperUtils.mapBookingEntityListToBookingResponseList(bookingList);
    }

    public ResponseEntity addBooking(BookingRequest request) {
        // TODO: Do all the business logic for verify that is correct.

        for (FlightRequest flight : request.getFlights()) {

            // Validate that the date of the creation of the Flight for the client only have 15 minutes. If the Flight not check it, return an ERROR

            Date actualDate = new Date();

            long difference = DateUtils.calculateDifferenceInMinutesBetweenDates(DateUtils.convertFrontendDateToDateFormat(flight.getDateOfCreationInBooking()), actualDate);
            if (difference >= 15) {
                return ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("The availability of the flight has more than 15 minutes of duration!");
            }
        }

        bookingDAO.addBooking(MapperUtils.mapBookingRequestToBookingEntity(request));
        log.debug("Booking was added correctly!");

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    public ResponseEntity addFlightToBooking(Long bookingId, FlightRequest flightRequest) {
        // TODO: In this step must be search in Database the Booking with the Id exist and it is correct with the data,
        //  but in this case, I will add all the flights for the same Booking. In Database, the booking and flights will be join by a foreign key and
        //  Booking could be have 0 to a lot of Flights.

        bookingDAO.addFlightToBooking(bookingId, MapperUtils.mapFlightRequestToFlightEntity(flightRequest));
        log.debug(String.format("Flight was added correctly for the booking %s!", bookingId));

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    public ResponseEntity deleteAFlightInABooking(Long bookingId, Long flightId) {
        bookingDAO.deleteAFlightInABooking(bookingId, flightId);
        log.debug(String.format("Flight %s was deleted correctly for the booking %s!", flightId, bookingId));

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    public ResponseEntity deleteABooking(Long bookingId) {
        // TODO: In this step must be search in Database the Booking with the Id exist and it is correct with the data,
        //  but in this case, I will delete the first Booking. In Database, the booking and flights will be join by a foreign key and
        //  Booking could be have 0 to a lot of Flights and when Delete a Booking, previously must be delete the Flights.
        //  For example, If the booking don't exist, return a OK and don't delete anything.
        bookingDAO.deleteABooking(bookingId);
        log.debug(String.format("Booking %s was deleted correctly!", bookingId));

        return ResponseEntity.ok().build();
    }

    public ResponseEntity confirmABooking(Long bookingId) {

        // TODO: In this step must be search in Database the Booking with the Id exist and get it.
        Booking booking = new Booking(
                "Julio",
                "Fernandez",
                "myAddress",
                "myPostalCode",
                "Spain",
                "myEmail",
                Arrays.asList("654 789 123", "693 582 471"),
                new ArrayList<Flight>() {{
                    add(new Flight("Madrid", "London", new Date(), new Date(), 0, 0, 1,
                            "myCompany", "number1", new Date(), new Date(), BigDecimal.TEN, new Date(), null));
                }}
        );

        // Validate the data of the Request
        if (booking == null
                || booking.getName() == null
                || booking.getLastName() == null
                || booking.getAddress() == null
                || booking.getPostalCode() == null
                || booking.getCountry() == null
                || booking.getEmail() == null
                || booking.getTelephones() == null
                || booking.getFlights() == null
                || booking.getFlights().size() == 0) {

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("The booking must not be null or contain empty values!");
        }

        for (Flight flight : booking.getFlights()) {
            if (flight == null
                    || flight.getAirportOrigin() == null
                    || flight.getAirportDestination() == null
                    || flight.getDateFrom() == null
                    || flight.getDateTo() == null) {
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body("The flight must not be null or contain empty values!");
            }

            // Validate that the date of the creation of the Flight for the client only have 15 minutes. If the Flight not check it, return an ERROR

            Date actualDate = new Date();

            long difference = DateUtils.calculateDifferenceInMinutesBetweenDates(flight.getCreationDateInTheBooking(), actualDate);
            if (difference >= 15) {
                return ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("The availability of the flight has more than 15 minutes of duration!");
            }

            // Added the date of the confirmation
            flight.setConfirmationDateInTheBooking(actualDate);
        }

        log.debug(String.format("Booking %s was confirmed correctly!", bookingId));
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }
}
