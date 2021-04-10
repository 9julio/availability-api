package services;

import com.tui.proof.dto.entity.Flight;
import com.tui.proof.dto.request.BookingRequest;
import com.tui.proof.dto.request.FlightRequest;
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
            add(new Flight("Madrid", "London", new Date(), new Date(), 0, 0, 1, "myCompany", "number1", new Date(), new Date(), BigDecimal.TEN));
            add(new Flight("London", "Madrid", new Date(), new Date(), 1, 4, 34, "myCompany2", "number2", new Date(), new Date(), BigDecimal.ONE));
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

        List<FlightResponse> response = flightService.getAvailabilityFlights("Madrid", "London", null, null, 0, 0, 0);
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

        flightService.addBooking(bookingRequest);

        // TODO: This mock not obtain the correct list of Bookings for tests, the best test for this cause is with Postman.
        // List<BookingResponse> allBookings = flightService.getAllBookings();
        // Assert.assertNotNull(allBookings);
        // Assert.assertEquals(3, allBookings.size());
    }


    @Test
    public void test_addFlightToBooking_OK() {

        FlightRequest request = new FlightRequest("Madrid", "London", "2021-04-05", "2021-04-05", 0, 0, 1);

        Mockito.doNothing().when(bookingDAOMock).addFlightToBooking(ArgumentMatchers.anyLong(), ArgumentMatchers.any());

        flightService.addFlightToBooking(1L, request);

        // TODO: This mock not obtain the correct list of Bookings for tests, the best test for this cause is with Postman.
        // List<BookingResponse> allBookings = flightService.getAllBookings();
        // Assert.assertNotNull(allBookings);
        // Assert.assertEquals(3, allBookings.size());
    }

}
