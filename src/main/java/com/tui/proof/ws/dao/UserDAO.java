package com.tui.proof.ws.dao;

import org.springframework.stereotype.Repository;

// This class is use to simulate a DAO for Database, must be extends of JPA Repository and create and interface
@Repository
public class UserDAO {

    public String logging(String user, String password) {
        return "123";
    }

}
