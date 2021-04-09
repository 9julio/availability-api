package com.tui.proof.utils;

import com.tui.proof.dto.entity.Booking;
import com.tui.proof.dto.entity.Flight;
import com.tui.proof.dto.request.BookingRequest;
import com.tui.proof.dto.request.FlightRequest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Utils {

    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final String HOUR_FORMAT = "HH:ss";

    public static String convertDateToDateFormat(Date date) {
        return date == null ? null : new SimpleDateFormat(DATE_FORMAT).format(date);
    }

    public static String convertDateToHourFormat(Date date) {
        return date == null ? null : new SimpleDateFormat(HOUR_FORMAT).format(date);
    }

    public static Date convertStringDateToDateWithFormat(String date) {
        try {
            return date == null ? null : new SimpleDateFormat(DATE_FORMAT).parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

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
        return  bookingRequest == null ? null : new Booking(
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
                convertStringDateToDateWithFormat(flightRequest.getDateFrom()),
                convertStringDateToDateWithFormat(flightRequest.getDateTo()),
                flightRequest.getInfants() == null ? 0 : flightRequest.getInfants(),
                flightRequest.getChildren() == null ? 0 : flightRequest.getChildren(),
                flightRequest.getAdults() == null ? 0 : flightRequest.getAdults(),
                null,
                null,
                null,
                null,
                null
        );
    }

}
