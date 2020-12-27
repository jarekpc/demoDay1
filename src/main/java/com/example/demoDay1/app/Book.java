package com.example.demoDay1.app;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Book {

    @Id
    @GeneratedValue
    private long id;

    @NotNull
    @Size(min =1, message = "Title should have at least 1 character")
    private String title;

    @NotNull
    @Size(min=1, message = "Description should have at least 1 character")
    private String description;

    @NotNull
    @PositiveOrZero(message = "Price  should be greater than 0")
    private double price;

    @NotNull(message = "publishDate must be set")
    private Date publishDate;

    @NotNull
    @Size(min = 1, message = "path should have at least 1 character")
    private String path;

}
