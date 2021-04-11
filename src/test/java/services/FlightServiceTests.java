package services;

import com.tui.proof.dto.entity.Booking;
import com.tui.proof.dto.entity.Flight;
import com.tui.proof.dto.request.BookingRequest;
import com.tui.proof.dto.request.FlightRequest;
import com.tui.proof.dto.response.BookingResponse;
import com.tui.proof.dto.response.FlightResponse;
import com.tui.proof.ws.dao.BookingDAO;
import com.tui.proof.ws.dao.FlightDAO;
import com.tui.proof.ws.service.FlightService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class FlightServiceTests {

    @Mock
    public FlightDAO flightDAOMock;

    @Mock
    public BookingDAO bookingDAOMock;

    @InjectMocks
    public FlightService flightService;

    @Test
    public void test_getAvailabilityFlights_OK() {

        List<Flight> flights = new ArrayList<Flight>(){{
            add(new Flight("Madrid", "London", new Date(), new Date(), 0, 0, 1, "myCompany", "number1", new Date(), new Date(), BigDecimal.TEN, new Date(), null));
            add(new Flight("London", "Madrid", new Date(), new Date(), 1, 4, 34, "myCompany2", "number2", new Date(), new Date(), BigDecimal.ONE, new Date(), null));
        }};

        Mockito.when(flightDAOMock.getAvailabilityFlights(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(),
                ArgumentMatchers.any(),
                ArgumentMatchers.anyInt(),
                ArgumentMatchers.anyInt(),
                ArgumentMatchers.anyInt()
        )).thenReturn(flights);

        List<FlightResponse> response = flightService.getAvailabilityFlights(new FlightRequest("Madrid", "London", null, null, 0, 0, 0));
        Assert.assertNotNull(response);
        Assert.assertEquals(2, response.size());
    }

    @Test
    public void test_addBooking_OK() {

        List<FlightRequest> flightRequestList = new ArrayList<FlightRequest>(){{
            add(new FlightRequest("Madrid", "London", "2021-04-05", "2021-04-05", 0, 0, 1));
            add(new FlightRequest("London", "Madrid", "2021-04-05", "2021-04-05", 1, 4, 34));
        }};

        BookingRequest bookingRequest = new BookingRequest(
                "myName",
                "myLastName",
                "myOtherAddress",
                "myPostalCode",
                "Spain",
                "myEmail",
                Arrays.asList("865 345 678"),
                flightRequestList);

        Mockito.doNothing().when(bookingDAOMock).addBooking(ArgumentMatchers.any());

        ResponseEntity response = flightService.addBooking(bookingRequest);
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.CREATED, response.getStatusCode());

        // TODO: This mock not obtain the correct list of Bookings for tests, the best test for this cause is with Postman.
        // List<BookingResponse> allBookings = flightService.getAllBookings();
        // Assert.assertNotNull(allBookings);
        // Assert.assertEquals(3, allBookings.size());
    }

    @Test
    public void test_addFlightToBooking_OK() {

        FlightRequest request = new FlightRequest("Madrid", "London", "2021-04-05", "2021-04-05", 0, 0, 1);

        Mockito.doNothing().when(bookingDAOMock).addFlightToBooking(ArgumentMatchers.anyLong(), ArgumentMatchers.any());

        ResponseEntity response = flightService.addFlightToBooking(1L, request);
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.CREATED, response.getStatusCode());

        // TODO: This mock not obtain the correct list of Bookings for tests, the best test for this cause is with Postman.
        // List<BookingResponse> allBookings = flightService.getAllBookings();
        // Assert.assertNotNull(allBookings);
        // Assert.assertEquals(3, allBookings.size());
    }

    @Test
    public void test_deleteAFlightInABooking_OK() {

        Mockito.doNothing().when(bookingDAOMock).deleteAFlightInABooking(ArgumentMatchers.anyLong(), ArgumentMatchers.anyLong());

        ResponseEntity response = flightService.deleteAFlightInABooking(1L, 1L);
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());

        // TODO: This mock not obtain the correct list of Bookings for tests, the best test for this cause is with Postman.
        // List<BookingResponse> allBookings = flightService.getAllBookings();
        // Assert.assertNotNull(allBookings);
        // Assert.assertEquals(2, allBookings.size());
    }

    @Test
    public void test_getBookings_OK() {

        List<Flight> flights = new ArrayList<Flight>(){{
            add(new Flight("Madrid", "London", new Date(), new Date(), 0, 0, 1, "myCompany", "number1", new Date(), new Date(), BigDecimal.TEN, new Date(), null));
            add(new Flight("London", "Madrid", new Date(), new Date(), 1, 4, 34, "myCompany2", "number2", new Date(), new Date(), BigDecimal.ONE, new Date(), null));
        }};

        List<Booking> bookings = new ArrayList<Booking>(){{
            add(new Booking(
                    "someName",
                    "someLastName",
                    "someAddress",
                    "somePostalCode",
                    "someCountry",
                    "someEmail",
                    Arrays.asList("765 234 456"),
                    flights
            ));
        }};

        Mockito.when(bookingDAOMock.getBookings(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.anyString(),
                ArgumentMatchers.anyString(),
                ArgumentMatchers.anyString(),
                ArgumentMatchers.anyString(),
                ArgumentMatchers.anyString(),
                ArgumentMatchers.anyList(),
                ArgumentMatchers.anyString(),
                ArgumentMatchers.anyString(),
                ArgumentMatchers.anyString(),
                ArgumentMatchers.anyString(),
                ArgumentMatchers.anyInt(),
                ArgumentMatchers.anyInt(),
                ArgumentMatchers.anyInt()
        )).thenReturn(bookings);

        BookingRequest bookingRequest = new BookingRequest(
                "someName",
                "someLastName",
                "someAddress",
                "somePostalCode",
                "someCountry",
                "someEmail",
                Arrays.asList("765 234 456"),
                null);

        List<BookingResponse> response = flightService.getBookings(bookingRequest);
        Assert.assertNotNull(response);
        Assert.assertEquals(1, response.size());
    }

    @Test
    public void test_deleteABooking_OK() {

        Mockito.doNothing().when(bookingDAOMock).deleteABooking(ArgumentMatchers.anyLong());

        ResponseEntity response = flightService.deleteABooking(1L);
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());

        // TODO: This mock not obtain the correct list of Bookings for tests, the best test for this cause is with Postman.
        // List<BookingResponse> allBookings = flightService.getAllBookings();
        // Assert.assertNotNull(allBookings);
        // Assert.assertEquals(1, allBookings.size());
    }

    @Test
    public void test_confirmABooking_OK() {
        ResponseEntity responseEntity = flightService.confirmABooking(1L);
        Assert.assertNotNull(responseEntity);
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

}
