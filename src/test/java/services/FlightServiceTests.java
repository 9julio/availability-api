package services;

import com.tui.proof.dto.entity.Flight;
import com.tui.proof.dto.response.FlightResponse;
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
import java.util.Date;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class FlightServiceTests {

    @Mock
    public FlightDAO flightDAOMock;

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

}
