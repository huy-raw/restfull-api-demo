package com.huyraw.demo.book;


import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping(path = "api/book")
public class BookController {


    private final  BookService bookService;

    @Autowired
    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the book",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Book.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Book not found",
                    content = @Content) })
    @GetMapping(value = "/")
    public List<Book> getBooks() {
        return bookService.getAllBooks();
    }


    @GetMapping(path = "/{id}")
    public Book getBookById(@PathVariable("id")  Long id) {
        return bookService.getBookById(id);
    }


    @PostMapping(value = "/")
    @ResponseStatus
    public Book importNewBook(@RequestBody Book book){
        return bookService.addNewBook(book);

    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus
    public void deleteBook(@PathVariable("id") Long id){
        bookService.deleteBook(id);
    }

    @PutMapping(path = "/{id}")
    public void updateBook(
        @PathVariable("id") Long id,
        @RequestBody(required = false) Book book
    ){
        bookService.updateBook(id, book);
    }
}
