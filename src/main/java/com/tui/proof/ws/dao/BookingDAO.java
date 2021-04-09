package com.tui.proof.ws.dao;

import com.tui.proof.dto.entity.Booking;
import com.tui.proof.dto.entity.Flight;
import com.tui.proof.dto.request.BookingRequest;
import com.tui.proof.utils.Utils;
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
                Arrays.asList(new Flight("Madrid", "London", new Date(), new Date(), 0, 0, 1, "myCompany", "number1", new Date(), new Date(), BigDecimal.TEN))
        ));

        add(new Booking(
                "Maria",
                "Peco",
                "myAddress",
                "myPostalCode",
                "Spain",
                "myEmail",
                Arrays.asList("654 789 123", "693 582 471"),
                Arrays.asList(new Flight("Madrid", "London", new Date(), new Date(), 0, 0, 1, "myCompany2", "number2", new Date(), new Date(), BigDecimal.TEN))
        ));
    }};

    public void addBooking(BookingRequest request) {
        // Map Request DTO to Entity because the Entities only must be use in DAOs
        Booking booking = Utils.mapBookingRequestToBookingEntity(request);

        // TODO: In this step will be the communication with the Database for insert the booking and verify that no conflict with others bookings.
        bookings.add(booking);
    }
}
