package com.example.demoDay1.controller;

import com.example.demoDay1.app.Book;
import com.example.demoDay1.app.BookRepository;
import com.example.demoDay1.app.BookServiceImpl;
import com.example.demoDay1.config.MyUserPrincipal;
import io.swagger.annotations.Api;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/books")
@Api(value = "creates and retrieves books")
public class BookController {

    private final BookRepository bookRepository;

    private final BookServiceImpl bookservice;

    public BookController(BookRepository bookRepository, BookServiceImpl bookservice) {
        this.bookRepository = bookRepository;
        this.bookservice = bookservice;
    }

    @GetMapping("/list")
    public String getBooks(Model model, Principal p) {
        List<Book> allBooks = new ArrayList<>();
        if (p != null) {
            MyUserPrincipal myuserprincipal = (MyUserPrincipal) ((Authentication) p).getPrincipal();
            System.out.println("myuserprincipal " + myuserprincipal.getPerson().getUsername());

            if (myuserprincipal.getPerson() != null)
                allBooks = bookRepository.findBooksByAuthor(myuserprincipal.getPerson().getId());
            else
                allBooks = bookRepository.findAll();
            model.addAttribute("booklist", allBooks);
        } else {
            allBooks = bookRepository.findAll();
            model.addAttribute("booklist", allBooks);
        }
        return "books";
    }

    @GetMapping("/new")
    public String newBook(Model model) {
        model.addAttribute("book", new Book());
        return "book";
    }

    @PostMapping("/new")
    public String newBootWithFile(@ModelAttribute Book book, @RequestParam("user-file") MultipartFile multipartFile, Principal principal) throws IOException {
        MyUserPrincipal myUserPrincipal = (MyUserPrincipal) ((Authentication) principal).getPrincipal();
        book.setBfiles(multipartFile.getBytes());
        book.setPath(multipartFile.getOriginalFilename());
        book.setAuthor(myUserPrincipal.getPerson());
        bookRepository.save(book);

        return "redirect:/book/list";
    }

    @GetMapping("/update")
    public String getUpdateBookForm(Model model, @RequestParam("id") Long id) {
        Optional<Book> book = bookRepository.findById(id);

        if (book.isPresent())
            model.addAttribute("book", book.get());

        return "updatebook";
    }

    @PostMapping("/update")
    public String updateBook(@ModelAttribute Book book) {
        bookservice.updateBook(book);
        return "redirect:/books/list";
    }

    @PostMapping("/search")
    public String searchForBooks(Model model, @RequestParam("searching") String search) {
        List<Book> allBooks = new ArrayList<>();
        allBooks = bookRepository.findBooksByTitle(search);
        model.addAttribute("booklist", allBooks);
        return "books";
    }
}
