package controllers;

import com.tui.proof.MainApplication;
import com.tui.proof.dto.request.BookingRequest;
import com.tui.proof.dto.request.FlightRequest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = MainApplication.class
)
@AutoConfigureMockMvc
public class FlightControllerTests {

    @LocalServerPort
    private int port;

    public TestRestTemplate restTemplate = new TestRestTemplate();

    @Before
    public void init() {
        restTemplate.getRestTemplate().setInterceptors(
                Collections.singletonList((request, body, execution) -> {
                    request.getHeaders()
                            .add("Authorization", "123");
                    return execution.execute(request, body);
                }));
    }

    @Test
    public void testOk_getAvailabilityFlights() {

//        ResponseEntity<List<FlightResponse>> response = restTemplate.exchange(
//                createURLWithPort("/flights"),
//                HttpMethod.GET,
//                null,
//                new ParameterizedTypeReference<List<FlightResponse>>(){});

        ResponseEntity<Object> response = restTemplate.exchange(
                createURLWithPort("/flights"),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Object>(){});

        Assert.assertNotNull(response);
        Assert.assertNotNull(response.getBody());
    }

    @Test
    public void testOk_addBooking() {

        HttpEntity<BookingRequest> entity = new HttpEntity<BookingRequest>(new BookingRequest(
                "Paco",
                "Rodrigo",
                "myAddress3",
                "myPostalCode",
                "Spain",
                "myEmail",
                Arrays.asList("854 345 567"),
                Arrays.asList(new FlightRequest(
                        "Malaga",
                        "Roma",
                        "2022-04-05",
                        "2022-04-09",
                        12,
                        45,
                        67
                ))
        ));


        ResponseEntity response = restTemplate.exchange(
                createURLWithPort("/bookings"),
                HttpMethod.POST,
                entity,
                new ParameterizedTypeReference<Object>(){});

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void testBadRequest_addBooking_withEmptyBody() {

        HttpEntity<BookingRequest> entity = new HttpEntity<BookingRequest>(new BookingRequest(
                null,
                "Rodrigo",
                "myAddress3",
                "myPostalCode",
                "Spain",
                "myEmail",
                Arrays.asList("854 345 567"),
                Arrays.asList(new FlightRequest(
                        "Malaga",
                        "Roma",
                        "2022-04-05",
                        "2022-04-09",
                        12,
                        45,
                        67
                ))
        ));

        ResponseEntity response = restTemplate.exchange(
                createURLWithPort("/bookings"),
                HttpMethod.POST,
                entity,
                new ParameterizedTypeReference<String>(){});

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testOk_addFlightToBooking() {

        HttpEntity<FlightRequest> entity = new HttpEntity<FlightRequest>(
                new FlightRequest(
                        "Malaga",
                        "Roma",
                        "2022-04-05",
                        "2022-04-09",
                        12,
                        45,
                        67
                )
        );

        ResponseEntity response = restTemplate.exchange(
                createURLWithPort("/bookings/1/flights"),
                HttpMethod.POST,
                entity,
                new ParameterizedTypeReference<Object>(){});

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void testOk_deleteAFlightInABooking() {

        ResponseEntity response = restTemplate.exchange(
                createURLWithPort("/bookings/1/flights/1"),
                HttpMethod.DELETE,
                null,
                new ParameterizedTypeReference<Object>(){});

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testOk_getBookings() {

//        ResponseEntity<List<BookingResponse>> response = restTemplate.exchange(
//                createURLWithPort("/bookings"),
//                HttpMethod.GET,
//                null,
//                new ParameterizedTypeReference<List<BookingResponse>>(){});

        ResponseEntity<Object> response = restTemplate.exchange(
                createURLWithPort("/bookings"),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Object>(){});

        Assert.assertNotNull(response);
        Assert.assertNotNull(response.getBody());
    }

    @Test
    public void testOk_deleteABooking() {

        ResponseEntity<Object> response = restTemplate.exchange(
                createURLWithPort("/bookings/1"),
                HttpMethod.DELETE,
                null,
                new ParameterizedTypeReference<Object>(){});

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testBadRequest_deleteABooking_withBadBookingId() {

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/bookings/aa"),
                HttpMethod.DELETE,
                null,
                new ParameterizedTypeReference<String>(){});

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testOk_confirmABooking() {

        ResponseEntity<Object> response = restTemplate.exchange(
                createURLWithPort("/bookings/1"),
                HttpMethod.PUT,
                null,
                new ParameterizedTypeReference<Object>(){});

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

}
