package com.tui.proof.ws.dao;

import com.tui.proof.dto.entity.Booking;
import com.tui.proof.dto.entity.Flight;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

// This class is use to simulate a DAO for Database
@Repository
public class BookingDAO {

    // In this class validate that the entity exist, conflicts, inserts, updates, deletes...

    @Getter
    @Setter
    private List<Booking> bookings = new ArrayList<Booking>(){{
        add(new Booking(
                "Julio",
                "Fernandez",
                "myAddress",
                "myPostalCode",
                "Spain",
                "myEmail",
                Arrays.asList("654 789 123", "693 582 471"),
                new ArrayList<Flight>() {{
                    add(new Flight("Madrid", "London", new Date(), new Date(), 0, 0, 1, "myCompany", "number1", new Date(), new Date(), BigDecimal.TEN));
                }}
        ));

        add(new Booking(
                "Maria",
                "Peco",
                "myAddress",
                "myPostalCode",
                "Spain",
                "myEmail",
                Arrays.asList("654 789 123", "693 582 471"),
                new ArrayList<Flight>() {{
                    add(new Flight("Madrid", "London", new Date(), new Date(), 0, 0, 1, "myCompany2", "number2", new Date(), new Date(), BigDecimal.TEN));
                }}
        ));
    }};

    public void addBooking(Booking booking) {
        // TODO: In this step will be the communication with the Database for insert the booking and verify that no conflict with others bookings.
        bookings.add(booking);
    }

    public List<Booking> getAllBookings() {
        return getBookings();
    }

    public void addFlightToBooking(Long bookingId, Flight flight) {
        // The zero will be the BookingId in Database
        bookings.get(0).getFlights().add(flight);
    }

    public void deleteAFlightInABooking(Long bookingId, Long flightId) {
        // The first zero will be the BookingId in Database and the second 0 is the flightId
        bookings.get(0).getFlights().remove(0);
    }
}
