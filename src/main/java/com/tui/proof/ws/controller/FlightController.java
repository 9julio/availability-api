package com.tui.proof.ws.controller;

import com.tui.proof.dto.request.BookingRequest;
import com.tui.proof.dto.request.FlightRequest;
import com.tui.proof.utils.DateUtils;
import com.tui.proof.ws.service.FlightService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Log4j2
@RestController
public class FlightController {

  @Autowired
  public FlightService flightService;

  @GetMapping("/flights")
  public ResponseEntity getAvailabilityFlights(FlightRequest request) {

    // FlightRequest can be null, it is indicate that the user do not filter by criterias.

    // Validate the format of the Dates
    Date dateFrom = null;
    if (request.getDateFrom() != null) {
      dateFrom = DateUtils.convertStringDateToDateWithFormat(request.getDateFrom());
    }

    Date dateTo = null;
    if (request.getDateTo() != null) {
      dateTo = DateUtils.convertStringDateToDateWithFormat(request.getDateTo());
    }

    return ResponseEntity.ok(flightService.getAvailabilityFlights(
            request.getAirportOrigin(),
            request.getAirportDestination(),
            dateFrom,
            dateTo,
            request.getInfants(),
            request.getChildren(),
            request.getAdults()
    ));
  }

  @PostMapping("/bookings")
  public ResponseEntity addBooking(@RequestBody BookingRequest request) {

    // Validate the data of the Request
    if (request == null
            || request.getName() == null
            || request.getLastName() == null
            || request.getAddress() == null
            || request.getPostalCode() == null
            || request.getCountry() == null
            || request.getEmail() == null
            || request.getTelephones() == null
            || request.getFlights() == null
            || request.getFlights().size() == 0) {

      return ResponseEntity
              .status(HttpStatus.BAD_REQUEST)
              .body("The booking request must not be null or contain empty values!");
    }

    for (FlightRequest flightRequest : request.getFlights()) {
      if (flightRequest == null
              || flightRequest.getAirportOrigin() == null
              || flightRequest.getAirportDestination() == null
              || flightRequest.getDateFrom() == null
              || flightRequest.getDateTo() == null
              || flightRequest.getInfants() == null
              || flightRequest.getChildren() == null
              || flightRequest.getAdults() == null) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("The flight request must not be null or contain empty values!");
      }
    }

    flightService.addBooking(request);

    return ResponseEntity.status(HttpStatus.OK).build();
  }


  @PostMapping("/bookings/{bookingId}/flights")
  public ResponseEntity addBooking(@PathVariable("bookingId") String bookingId,
                                   @RequestBody FlightRequest flightRequest) {

    // Validate the path
    if (bookingId == null) {
      return ResponseEntity
              .status(HttpStatus.NOT_FOUND)
              .body("The bookingId must not be null or contain empty values!");
    }

    Long booking = Long.valueOf(bookingId);
    if (booking == null) {
      return ResponseEntity
              .status(HttpStatus.BAD_REQUEST)
              .body("The bookingId must not be null or contain empty values!");
    }

    if (flightRequest == null) {
      return ResponseEntity
              .status(HttpStatus.BAD_REQUEST)
              .body("The flight request must not be null or contain empty values!");
    }

    flightService.addFlightToBooking(booking, flightRequest);

    return ResponseEntity.status(HttpStatus.OK).build();
  }

}
