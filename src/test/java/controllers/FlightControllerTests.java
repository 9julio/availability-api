package controllers;

import com.tui.proof.MainApplication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

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
    public void testOk() {

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

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

}
