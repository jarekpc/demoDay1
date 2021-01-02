package com.example.demoDay1.app;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "person")
@Getter
@Setter
@JsonIgnoreProperties({"createdBooks", "orders"})
public class Person implements Serializable {

    private static final String ROLE_AUTHOR = "AUTHOR";
    private static final String ROLE_CUSTOMER = "CUSTOMER";

    @NotNull
    @Size(min = 1, message = "Username mustn't be empty")
    private String username;

    @NotNull
    @Size(min = 5, message = "password mustn; be empty and should have at leaste 5 characters")
    private String password;

    @NotNull
    @Size(min = 1, message = "firstame mustn't be empty")
    @Column(name = "firstname", length = 30, nullable = false)
    private String firstName;

    @NotNull
    @Size(min = 1, message = "lastname mustn't be empty")
    @Column(name = "lastname", length = 50, nullable = false)
    private String lastName;

    @Column(name = "userrole", length = 20, nullable = false)
    private String userRole;

    @Id
    @GeneratedValue
    @Column(name = "ID", nullable = false)
    private long id;

    @OneToMany(mappedBy = "author")
    private List<Book> createdBooks = new ArrayList<>();

    @OneToMany(mappedBy = "customer")
    private List<Order> orders = new ArrayList<>();

    public Person() {
    }

    public Person(@NotNull @Size(min = 1, message = "Username mustn't be empty") String username, @NotNull @Size(min = 5, message = "password mustn; be empty and should have at leaste 5 characters") String password, @NotNull @Size(min = 1, message = "firstame mustn't be empty") String firstName, @NotNull @Size(min = 1, message = "lastname mustn't be empty") String lastName, String userRole) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userRole = userRole;
    }
}
