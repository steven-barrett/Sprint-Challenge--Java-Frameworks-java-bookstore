package com.lambdaschool.starthere.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@ApiModel(value = "Course", description =  "All the courses students can take")
@Entity
@Table(name = "book")
public class Book extends Auditable
{
    @ApiModelProperty(name = "bookid", value = "primary key for book", required = true, example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long bookid;

    @ApiModelProperty(name = "booktitle", value = "Book Title", required = true, example = "Physics")
    private String booktitle;

    @ApiModelProperty(name = "ISBN", value = "International Standard Book Number(ISBN) of the book", required = true, example = "1984036782")
    private String ISBN;

    @ApiModelProperty(name = "copy", value = "copywrite - the year the book was published", required = true, example = "1978")
    private int copy;

    @ManyToMany
    @JoinTable(name = "bookauthors",
            joinColumns = {@JoinColumn(name = "bookid")},
            inverseJoinColumns = {@JoinColumn(name = "authorid")})
    @JsonIgnoreProperties("books")
    private List<Authors> authors = new ArrayList<>();

    public Book()
    {
    }

    public Book(String booktitle, String ISBN, int copy)
    {
        this.booktitle = booktitle;
        this.ISBN = ISBN;
        this.copy = copy;
    }

    public long getBookid()
    {
        return bookid;
    }

    public void setBookid(long bookid)
    {
        this.bookid = bookid;
    }

    public String getBooktitle()
    {
        return booktitle;
    }

    public void setBooktitle(String booktitle)
    {
        this.booktitle = booktitle;
    }

    public String getISBN()
    {
        return ISBN;
    }

    public void setISBN(String ISBN)
    {
        this.ISBN = ISBN;
    }

    public int getCopy()
    {
        return copy;
    }

    public void setCopy(int copy)
    {
        this.copy = copy;
    }

    public List<Authors> getAuthors()
    {
        return authors;
    }

    public void setAuthors(List<Authors> authors)
    {
        this.authors = authors;
    }
}
