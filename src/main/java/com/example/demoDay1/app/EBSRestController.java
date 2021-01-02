package com.example.demoDay1.app;

import com.example.demoDay1.error.BookNotFoundException;
import com.example.demoDay1.service.EbookStoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@Api(description = "RESTFull webservice")
public class EBSRestController {

    private final EbookStoreService ebookStoreService;

    public EBSRestController(EbookStoreService ebookStoreService) {
        this.ebookStoreService = ebookStoreService;
    }

    @ApiOperation(value = "retrieves all books", response = List.class)
    @GetMapping(value = "/rest/books", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<Book> getBooks() {
        return ebookStoreService.getBooks();
    }

    @ApiOperation(value = "retrieves a certain book")
    @GetMapping(value = "/rest/book/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Optional<Book> getBook(@ApiParam("Id of the book") @PathVariable Long id) {
        Optional<Book> foundBook = ebookStoreService.findById(id);
        if(!foundBook.isPresent()){
            throw  new BookNotFoundException("id-"+id);
        }
        return foundBook;
    }

    @PostMapping(value = "/rest/book", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ApiOperation(value = "created book")
//    @ApiResponse(code = {@ApiResponse(code = 201, message = "created Book")})
    @ApiResponse(responseCode = "201", description = "creat Book:)")
    public ResponseEntity<Object> newBook(@RequestBody Book newBook) {
        Book saveBook = ebookStoreService.savedBook(newBook);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saveBook.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/rest/book/{id}")
    public void deleteBook(@PathVariable Long id) {
        ebookStoreService.deleteBookById(id);
    }

    @PutMapping(value = "/rest/book/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Object> udpateBook(@RequestBody Book book, @PathVariable Long id) {
        Optional<Book> bookOptional = ebookStoreService.findById(id);
        if (!bookOptional.isPresent())
            throw new BookNotFoundException("id-"+id);

        book.setId(id);
        ebookStoreService.savedBook(book);

        return ResponseEntity.noContent().build();

    }


}
