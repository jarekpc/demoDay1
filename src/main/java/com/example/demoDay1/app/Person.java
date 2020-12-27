package com.example.demoDay1.app;

import lombok.Data;

@Data
public class Person {

    private static final String ROLE_AUTHOR = "AUTHOR";
    private static final String ROLE_CUSTOMER = "CUSTOMER";

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String userRole;


}
