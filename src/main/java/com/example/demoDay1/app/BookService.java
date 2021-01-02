package com.example.demoDay1.app;

public interface BookService {

    void deleteBook(long id);

    void addBookToAuthor(Book b, Person p);

    void updateBook(Book b);
}
