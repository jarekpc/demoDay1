package com.example.demoDay1.app.model;

import com.example.demoDay1.app.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookInfo {
    private String title;
    private String author;
    private double price;
    private long id;

    public BookInfo(Book book) {
        this.title = book.getTitle();
        this.author = book.getAuthor().getLastName() + " " + book.getAuthor().getFirstName();
        this.price = book.getPrice();
        this.id = book.getId();
    }
}
