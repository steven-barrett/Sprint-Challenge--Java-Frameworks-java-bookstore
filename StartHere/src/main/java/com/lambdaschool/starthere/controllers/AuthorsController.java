package com.lambdaschool.starthere.controllers;

import com.lambdaschool.starthere.models.Authors;
import com.lambdaschool.starthere.models.Book;
import com.lambdaschool.starthere.models.ErrorDetail;
import com.lambdaschool.starthere.services.AuthorsService;
import com.lambdaschool.starthere.services.BookService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/authors")
public class AuthorsController
{
    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private AuthorsService authorsService;

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

    @GetMapping(value = "/authors", produces = {"application/json"})
    public ResponseEntity<?> listAllBooks(HttpServletRequest request, @PageableDefault(page = 0, size = 6)
            Pageable pageable)
    {
        logger.info(request.getMethod() + " " + request.getRequestURI() + " accessed");

        List<Authors> myAuthors = authorsService.findAll(pageable);
        return new ResponseEntity<>(myAuthors, HttpStatus.OK);
    }

    @ApiOperation(value = "Adds an existing book to an existing author",
            response = void.class)
    @ApiResponses(value = {@ApiResponse(code = 201,
            message = "Book Added",
            response = void.class), @ApiResponse(code = 500,
            message = "Error adding book to author",
            response = ErrorDetail.class)})
    @PostMapping(value = "/addbook/{authorid}/{bookid}")
    public ResponseEntity<?> addBookToAuthor(HttpServletRequest request,
                                             @ApiParam(value = "The author id",
                                                     required = true,
                                                     example = "1")
                                             @PathVariable long authorid,
                                             @ApiParam(value = "The book id",
                                                     required = true,
                                                     example = "1")
                                             @PathVariable long bookid)
    {
        logger.info(request.getMethod() + " " + request.getRequestURI() + " accessed");

        // Add the book to the author
        Book currentBook = bookService.findById(bookid);
        Authors currentAuthor = authorsService.findById(authorid);

        currentAuthor.getBooks().add(currentBook);
        authorsService.save(currentAuthor);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
