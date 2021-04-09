package com.tui.proof.ws.controller;

import com.tui.proof.dto.request.FlightRequest;
import com.tui.proof.dto.response.FlightResponse;
import com.tui.proof.utils.Utils;
import com.tui.proof.ws.service.FlightService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Log4j2
@RestController
public class FlightController {

  @Autowired
  public FlightService flightService;

  @GetMapping("/flights")
  public ResponseEntity<List<FlightResponse>> getAvailabilityFlights(FlightRequest request) {

    // FlightRequest can be null, it is indicate that the user do not filter by criterias.

    // Validate the format of the Dates
    Date dateFrom = null;
    Date dateTo = null;
    try {

      if (request.getDateFrom() != null) {
        dateFrom = new SimpleDateFormat(Utils.DATE_FORMAT).parse(request.getDateFrom());
      }
      if (request.getDateTo() != null) {
        dateTo = new SimpleDateFormat(Utils.DATE_FORMAT).parse(request.getDateTo());
      }

    } catch (ParseException e) {
      return new ResponseEntity<List<FlightResponse>>(HttpStatus.BAD_REQUEST);
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

}
