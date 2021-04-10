package controllers;

import com.tui.proof.MainApplication;
import com.tui.proof.dto.request.BookingRequest;
import com.tui.proof.dto.request.FlightRequest;
import org.junit.Assert;
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
                Object.class);

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

}
