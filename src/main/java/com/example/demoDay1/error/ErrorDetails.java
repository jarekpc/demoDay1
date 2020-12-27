package com.example.demoDay1.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ErrorDetails {
    private Date timestamp;
    private String message;
    private String details;
}
