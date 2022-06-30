package com.huyraw.demo.controller;


import com.huyraw.demo.model.Book;
import com.huyraw.demo.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping(path = "api/book")
@Tag(name = "book")
public class BookController {


    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the book",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Book.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Book not found",
                    content = @Content)})
    @GetMapping(value = "/")
    public List<Book> getBooks() {
        return bookService.getAllBooks();
    }


    @GetMapping(path = "/{id}")
    @Operation(summary = "Get book by Id")
    public ResponseEntity<Book> getBookById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(bookService.getBookById(id), HttpStatus.OK);
    }


    @PostMapping(value = "/")
    @ResponseStatus()
    public ResponseEntity<Book> importNewBook(@RequestBody Book book) {
        Book book_ = bookService.addNewBook(book);
        if (book_ == null) {
            return new ResponseEntity<>(book_, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(book_, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteBook(@PathVariable("id") Long id) {

        bookService.deleteBook(id);
    }

    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateBook(
            @PathVariable("id") Long id,
            @RequestBody(required = false) Book book
    ) {
        bookService.updateBook(id, book);
    }
}
