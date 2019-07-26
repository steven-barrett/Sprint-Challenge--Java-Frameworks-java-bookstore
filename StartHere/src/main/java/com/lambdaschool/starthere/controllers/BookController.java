package com.lambdaschool.starthere.controllers;

import com.lambdaschool.starthere.models.Book;
import com.lambdaschool.starthere.models.ErrorDetail;
import com.lambdaschool.starthere.services.BookService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/books")
public class BookController
{
    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookService bookService;

    @ApiOperation(value = "returns all Courses with Paging Ability",
            responseContainer = "List")
    @ApiImplicitParams({@ApiImplicitParam(name = "page",
            dataType = "integer",
            paramType = "query",
            value = "Results page you want to retrieve (0..N)"), @ApiImplicitParam(name = "size",
            dataType = "integer",
            paramType = "query",
            value = "Number of records per page."), @ApiImplicitParam(name = "sort",
            allowMultiple = true,
            dataType = "string",
            paramType = "query",
            value = "Sorting criteria in the format: property(,asc|desc). " + "Default sort order is ascending. " + "Multiple sort criteria are supported.")})

    @GetMapping(value = "/books", produces = {"application/json"})
    public ResponseEntity<?> listAllBooks(HttpServletRequest request, @PageableDefault(page = 0, size = 6)
            Pageable pageable)
    {
        logger.info(request.getMethod() + " " + request.getRequestURI() + " accessed");

        List<Book> myBooks = bookService.findAll(pageable);
        return new ResponseEntity<>(myBooks, HttpStatus.OK);
    }

    @ApiOperation(value = "Updates an existing book",
            response = Book.class)
    @ApiResponses(value = {@ApiResponse(code = 201,
            message = "Book Updated",
            response = void.class), @ApiResponse(code = 500,
            message = "Error updating book",
            response = ErrorDetail.class)})
    @PutMapping(value = "/book/{bookid}")
    public ResponseEntity<?> addBookToAuthor(
            HttpServletRequest request,
            @RequestBody
                    Book updateBook,
            @ApiParam(value = "The student id",
                    required = true,
                    example = "1")
            @PathVariable
                    long bookid)
    {
        logger.info(request.getMethod() + " " + request.getRequestURI() + " accessed");

        bookService.update(updateBook, bookid);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @ApiOperation(value = "Deletes an existing book",
            response = void.class)
    @ApiResponses(value = {@ApiResponse(code = 201,
            message = "Book Deleted",
            response = void.class), @ApiResponse(code = 500,
            message = "Error deleting book",
            response = ErrorDetail.class)})
    @DeleteMapping("/delete/{bookid}")
    public ResponseEntity<?> deleteBookById(
            @ApiParam(value = "The book id",
                    required = true,
                    example = "1")
            @PathVariable
                    long bookid,
            HttpServletRequest request)
    {
        logger.info(request.getMethod() + " " + request.getRequestURI() + " accessed");

        bookService.delete(bookid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
