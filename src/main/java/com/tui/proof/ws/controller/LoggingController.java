package com.tui.proof.ws.controller;

import com.tui.proof.dto.request.UserRequest;
import com.tui.proof.ws.service.LoggingService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
public class LoggingController {

  @Autowired
  public LoggingService loggingService;

  @RequestMapping(
          value = { "/logging" },
          method = RequestMethod.POST,
          produces = MediaType.APPLICATION_JSON_VALUE,
          consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity logging(@RequestBody UserRequest request) {

    if (request.getUser() == null || request.getUser().length() == 0) {
      log.error("The user must not be null or empty!");
      return ResponseEntity
              .status(HttpStatus.BAD_REQUEST)
              .body("The user must not be null or empty!");
    }

    if (request.getPassword() == null || request.getPassword().length() == 0) {
      log.error("The password must not be null or empty!");
      return ResponseEntity
              .status(HttpStatus.BAD_REQUEST)
              .body("The password must not be null or empty!");
    }

    ResponseEntity response = loggingService.logging(request);
    log.debug("Logging ok!");

    return response;
  }

}
