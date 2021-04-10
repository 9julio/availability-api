package com.tui.proof.utils;

import com.tui.proof.dto.entity.Booking;
import com.tui.proof.dto.entity.Flight;
import com.tui.proof.dto.request.BookingRequest;
import com.tui.proof.dto.request.FlightRequest;
import com.tui.proof.dto.response.BookingResponse;
import com.tui.proof.dto.response.FlightResponse;

import java.util.ArrayList;
import java.util.List;

public class MapperUtils {

    public static List<Booking> mapBookingRequestListToBookingEntityList(List<BookingRequest> bookingRequestList) {
        if (bookingRequestList == null) {
            return null;
        }

        List<Booking> bookingList = new ArrayList<Booking>();
        for (BookingRequest bookingRequest : bookingRequestList) {
            bookingList.add(mapBookingRequestToBookingEntity(bookingRequest));
        }

        return bookingList;
    }

    public static Booking mapBookingRequestToBookingEntity(BookingRequest bookingRequest) {
        return bookingRequest == null ? null : new Booking(
                bookingRequest.getName(),
                bookingRequest.getLastName(),
                bookingRequest.getAddress(),
                bookingRequest.getPostalCode(),
                bookingRequest.getCountry(),
                bookingRequest.getEmail(),
                bookingRequest.getTelephones(),
                mapFlightRequestListToFlightEntityList(bookingRequest.getFlights())
        );
    }

    public static List<BookingResponse> mapBookingEntityListToBookingResponseList(List<Booking> bookingList) {
        if (bookingList == null) {
            return null;
        }

        List<BookingResponse> bookingResponseList = new ArrayList<BookingResponse>();
        for (Booking booking : bookingList) {
            bookingResponseList.add(mapBookingEntityToBookingResponse(booking));
        }

        return bookingResponseList;
    }

    public static BookingResponse mapBookingEntityToBookingResponse(Booking booking) {
        return booking == null ? null : new BookingResponse(
                booking.getName(),
                booking.getLastName(),
                booking.getAddress(),
                booking.getPostalCode(),
                booking.getCountry(),
                booking.getEmail(),
                booking.getTelephones(),
                mapFlightEntityListToFlightResponseList(booking.getFlights())
        );
    }

    public static List<Flight> mapFlightRequestListToFlightEntityList(List<FlightRequest> flightRequestList) {
        if (flightRequestList == null) {
            return null;
        }

        List<Flight> flightList = new ArrayList<Flight>();
        for (FlightRequest flightRequest : flightRequestList) {
            flightList.add(mapFlightRequestToFlightEntity(flightRequest));
        }

        return flightList;
    }

    public static Flight mapFlightRequestToFlightEntity(FlightRequest flightRequest) {
        return flightRequest == null ? null : new Flight(
                flightRequest.getAirportOrigin(),
                flightRequest.getAirportDestination(),
                DateUtils.convertStringDateToDateWithFormat(flightRequest.getDateFrom()),
                DateUtils.convertStringDateToDateWithFormat(flightRequest.getDateTo()),
                flightRequest.getInfants() == null ? 0 : flightRequest.getInfants(),
                flightRequest.getChildren() == null ? 0 : flightRequest.getChildren(),
                flightRequest.getAdults() == null ? 0 : flightRequest.getAdults(),
                null,
                null,
                null,
                null,
                null,
                null,
                null
        );
    }

    public static List<FlightResponse> mapFlightEntityListToFlightResponseList(List<Flight> flightList) {
        if (flightList == null) {
            return null;
        }

        List<FlightResponse> flightResponseList = new ArrayList<FlightResponse>();
        for (Flight flight : flightList) {
            flightResponseList.add(mapFlightEntityToFlightResponse(flight));
        }

        return flightResponseList;
    }

    public static FlightResponse mapFlightEntityToFlightResponse(Flight flight) {
        return flight == null ? null : new FlightResponse(
                flight.getAirportOrigin(),
                flight.getAirportDestination(),
                DateUtils.convertDateToDateFormat(flight.getDateFrom()),
                DateUtils.convertDateToDateFormat(flight.getDateTo()),
                flight.getInfants(),
                flight.getChildren(),
                flight.getAdults(),
                null,
                null,
                null,
                null,
                null
        );
    }

}
