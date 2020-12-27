package com.example.demoDay1.service;

import com.example.demoDay1.app.Book;
import com.example.demoDay1.app.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EbookStoreService {

    //    private List<Book> allBooks = Stream.of(
//            new Book(1,"Abc","testowa",0.0, Date.valueOf("2020-10-11"),"")
//    ).collect(Collectors.toList());
    private final BookRepository bookRepository;

    public EbookStoreService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> findById(Long id){
        return bookRepository.findById(id);
    }

    public Book savedBook(Book saved){
        return bookRepository.save(saved);
    }

    public void deleteBookById(Long id){
        bookRepository.deleteById(id);
    }


}
