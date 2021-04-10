package com.tui.proof.ws.controller;

import com.tui.proof.dto.request.BookingRequest;
import com.tui.proof.dto.request.FlightRequest;
import com.tui.proof.dto.response.BookingResponse;
import com.tui.proof.utils.NumberUtils;
import com.tui.proof.ws.service.FlightService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@Log4j2
@RestController
public class FlightController {

  @Autowired
  public FlightService flightService;

  @GetMapping("/flights")
  public ResponseEntity getAvailabilityFlights(FlightRequest request) {

    log.info(String.format("getAvailabilityFlights with request %s", request));
    // FlightRequest can be null, it is indicate that the user do not filter by criterias.

    // If don't search anything, the search is OK but without elements. Return an OK code but empty body
    return ResponseEntity.ok(flightService.getAvailabilityFlights(request));
  }

  @GetMapping("/bookings")
  public ResponseEntity getBookings(BookingRequest request) {

    log.info(String.format("getBookings with request %s", request));
    // BookingRequest can be null, it is indicate that the user do not filter by criterias.

    List<BookingResponse> response = flightService.getBookings(request);

    // If don't search anything, the search is OK but without elements. Return an OK code but empty body
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @PostMapping("/bookings")
  public ResponseEntity addBooking(@RequestBody @NotNull BookingRequest request) {

    log.info(String.format("addBooking with request %s", request));
    // Validate the data of the Request
    if (request == null
            || request.getName() == null
            || request.getLastName() == null
            || request.getAddress() == null
            || request.getPostalCode() == null
            || request.getCountry() == null
            || request.getEmail() == null
            || request.getTelephones() == null
            || (request.getFlights() == null || request.getFlights().size() == 0)) {

      log.error("The booking request must not be null or contain empty values!");

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
              || flightRequest.getAdults() == null

              // This date must be add for the front-end with the format yyyy-MM-dd-HH:mm
              || flightRequest.getDateOfCreationInBooking() == null) {

        log.error("The flight request must not be null or contain empty values!");

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("The flight request must not be null or contain empty values!");
      }
    }

    return flightService.addBooking(request);
  }

  @DeleteMapping("/bookings/{bookingId}")
  public ResponseEntity deleteABooking(@PathVariable("bookingId") String bookingId) {

    log.info(String.format("deleteABooking with bookingId %s", bookingId));
    // Validate the path
    if (bookingId == null) {

      log.error("The bookingId must not be null or contain empty values!");

      return ResponseEntity
              .status(HttpStatus.NOT_FOUND)
              .body("The bookingId must not be null or contain empty values!");
    }

    Long booking = NumberUtils.convertStringNumberToLong(bookingId);
    if (booking == null) {

      log.error("The bookingId must not be null or contain empty values!");

      return ResponseEntity
              .status(HttpStatus.BAD_REQUEST)
              .body("The bookingId must not be null or contain empty values!");
    }

    return flightService.deleteABooking(booking);
  }

  @PutMapping("/bookings/{bookingId}")
  public ResponseEntity confirmABooking(@PathVariable("bookingId") String bookingId) {

    log.info(String.format("confirmABooking with bookingId %s", bookingId));

    // Validate the path
    if (bookingId == null) {

      log.error("The bookingId must not be null or contain empty values!");

      return ResponseEntity
              .status(HttpStatus.NOT_FOUND)
              .body("The bookingId must not be null or contain empty values!");
    }

    Long booking = NumberUtils.convertStringNumberToLong(bookingId);
    if (booking == null) {

      log.error("The bookingId must not be null or contain empty values!");

      return ResponseEntity
              .status(HttpStatus.BAD_REQUEST)
              .body("The bookingId must not be null or contain empty values!");
    }

    return flightService.confirmABooking(booking);
  }

  @PostMapping("/bookings/{bookingId}/flights")
  public ResponseEntity addFlightToBooking(@PathVariable("bookingId") String bookingId,
                                           @RequestBody @NotNull FlightRequest flightRequest) {

    log.info(String.format("addFlightToBooking with bookingId %s and request %s", bookingId, flightRequest));

    // Validate the path
    if (bookingId == null) {

      log.error("The bookingId must not be null or contain empty values!");

      return ResponseEntity
              .status(HttpStatus.NOT_FOUND)
              .body("The bookingId must not be null or contain empty values!");
    }

    Long booking = NumberUtils.convertStringNumberToLong(bookingId);
    if (booking == null) {

      log.error("The bookingId must not be null or contain empty values!");

      return ResponseEntity
              .status(HttpStatus.BAD_REQUEST)
              .body("The bookingId must not be null or contain empty values!");
    }

    if (flightRequest == null) {

      log.error("The flight request must not be null or contain empty values!");
      return ResponseEntity
              .status(HttpStatus.BAD_REQUEST)
              .body("The flight request must not be null or contain empty values!");
    }

    return flightService.addFlightToBooking(booking, flightRequest);
  }

  @DeleteMapping("/bookings/{bookingId}/flights/{flightId}")
  public ResponseEntity deleteAFlightInABooking(@PathVariable("bookingId") String bookingId,
                                                @PathVariable("flightId") String flightId) {

    log.info(String.format("deleteAFlightInABooking with bookingId %s and flightId %s", bookingId, flightId));

    // Validate the path
    if (bookingId == null) {

      log.error("The bookingId must not be null or contain empty values!");

      return ResponseEntity
              .status(HttpStatus.NOT_FOUND)
              .body("The bookingId must not be null or contain empty values!");
    }

    Long booking = NumberUtils.convertStringNumberToLong(bookingId);
    if (booking == null) {

      log.error("The bookingId must not be null or contain empty values!");

      return ResponseEntity
              .status(HttpStatus.BAD_REQUEST)
              .body("The bookingId must not be null or contain empty values!");
    }

    if (flightId == null) {

      log.error("The flightId must not be null or contain empty values!");

      return ResponseEntity
              .status(HttpStatus.NOT_FOUND)
              .body("The flightId must not be null or contain empty values!");
    }

    Long flight = NumberUtils.convertStringNumberToLong(flightId);
    if (flight == null) {

      log.error("The flightId must not be null or contain empty values!");

      return ResponseEntity
              .status(HttpStatus.BAD_REQUEST)
              .body("The flightId must not be null or contain empty values!");
    }

    return flightService.deleteAFlightInABooking(booking, flight);
  }

}
