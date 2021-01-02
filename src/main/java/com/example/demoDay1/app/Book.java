package com.example.demoDay1.app;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
//@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "book")
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "p_id", nullable = true)
    @JsonIgnore
    private Person author;

    @Column(name = "bfile", nullable = false)
    @Lob
    private byte[] bfiles;

    public Book(@NotNull @Size(min = 1, message = "Title should have at least 1 character") String title, @NotNull @Size(min = 1, message = "Description should have at least 1 character") String description, @NotNull @PositiveOrZero(message = "Price  should be greater than 0") double price, @NotNull(message = "publishDate must be set") Date publishDate, @NotNull @Size(min = 1, message = "path should have at least 1 character") String path, byte[] bfiles) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.publishDate = publishDate;
        this.path = path;
        this.bfiles = bfiles;
    }
}
