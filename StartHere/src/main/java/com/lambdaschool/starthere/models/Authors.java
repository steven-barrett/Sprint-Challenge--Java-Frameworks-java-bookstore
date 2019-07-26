package com.lambdaschool.starthere.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@ApiModel(value = "authors", description =  "A total list of authors")
@Entity
@Table(name = "authors")
public class Authors extends Auditable
{
    @ApiModelProperty(name = "authorid", value = "primary key for author", required = true, example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long authorid;

    @ApiModelProperty(name = "lastname", value = "Author last name", required = true, example = "King")
    private String lastname;

    @ApiModelProperty(name = "firstname", value = "Author first name", required = true, example = "Stephen")
    private String firstname;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "bookauthors",
            joinColumns = {@JoinColumn(name = "authorid")},
            inverseJoinColumns = {@JoinColumn(name = "bookid")})
    @JsonIgnoreProperties("authors")
    private List<Book> books = new ArrayList<>();

    public Authors()
    {
    }

    public Authors(String lastname, String firstname)
    {
        this.lastname = lastname;
        this.firstname = firstname;
    }

    public long getAuthorid()
    {
        return authorid;
    }

    public void setAuthorid(long authorid)
    {
        this.authorid = authorid;
    }

    public String getLastname()
    {
        return lastname;
    }

    public void setLastname(String lastname)
    {
        this.lastname = lastname;
    }

    public String getFirstname()
    {
        return firstname;
    }

    public void setFirstname(String firstname)
    {
        this.firstname = firstname;
    }

    public List<Book> getBooks()
    {
        return books;
    }

    public void setBooks(List<Book> books)
    {
        this.books = books;
    }
}
