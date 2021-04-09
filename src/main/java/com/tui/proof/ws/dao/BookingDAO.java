package com.tui.proof.ws.dao;

import com.tui.proof.dto.entity.Booking;
import com.tui.proof.dto.entity.Flight;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

// This class is use to simulate a DAO for Database
@Repository
public class BookingDAO {

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



}
