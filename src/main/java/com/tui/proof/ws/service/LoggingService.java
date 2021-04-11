package com.tui.proof.ws.service;

import com.tui.proof.dto.request.UserRequest;
import com.tui.proof.ws.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class LoggingService {

    @Autowired
    public UserDAO userDAO;

    public ResponseEntity logging(UserRequest request) {
        userDAO.logging(request.getUser(), request.getPassword());
        return ResponseEntity.status(HttpStatus.CREATED).body("123");
    }

}
