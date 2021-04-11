package controllers;

import com.tui.proof.MainApplication;
import com.tui.proof.dto.request.UserRequest;
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

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = MainApplication.class
)
@AutoConfigureMockMvc
public class LoggingControllerTests {

    @LocalServerPort
    private int port;

    public TestRestTemplate restTemplate = new TestRestTemplate();

    @Test
    public void testOk_logging() {

        HttpEntity<UserRequest> entity = new HttpEntity<UserRequest>(new UserRequest(
                "user",
                "password"
        ));

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/logging"),
                HttpMethod.POST,
                entity,
                new ParameterizedTypeReference<String>(){});

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assert.assertNotNull(response.getBody());
    }

    @Test
    public void testBadRequest_logging_without_User() {

        HttpEntity<UserRequest> entity = new HttpEntity<UserRequest>(new UserRequest(
                null,
                "password"
        ));

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/logging"),
                HttpMethod.POST,
                entity,
                new ParameterizedTypeReference<String>(){});

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }


    @Test
    public void testBadRequest_logging_without_Password() {

        HttpEntity<UserRequest> entity = new HttpEntity<UserRequest>(new UserRequest(
                "user",
                null
        ));

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/logging"),
                HttpMethod.POST,
                entity,
                new ParameterizedTypeReference<String>(){});

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

}
