package services;

import com.tui.proof.dto.request.UserRequest;
import com.tui.proof.ws.dao.UserDAO;
import com.tui.proof.ws.service.LoggingService;
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

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class LoggingServiceTests {

    @Mock
    public UserDAO userDAOMock;

    @InjectMocks
    public LoggingService loggingService;

    @Test
    public void test_logging_OK() {

        Mockito.when(userDAOMock.logging(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.anyString()
        )).thenReturn("123");

        ResponseEntity response = loggingService.logging(new UserRequest("user", "password"));

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }
}
