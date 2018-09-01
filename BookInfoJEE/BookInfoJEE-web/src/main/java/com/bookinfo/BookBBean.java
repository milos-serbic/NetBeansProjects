/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bookinfo;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Milos
 */
@Named
@RequestScoped
public class BookBBean { 
 
    private Long bookId;
    private String bookTitle;
    private String bookAuthor;

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    Client client;
    WebTarget target;

    @PostConstruct
    public void init() {
        client = ClientBuilder.newClient();
        FacesContext context = FacesContext.getCurrentInstance();
        String path = context.getExternalContext().getRequestContextPath();
        target = client.target("http://localhost:8080" + path + "/bookinfo/book");
    }

    @PreDestroy
    public void destroy() {
        client.close();
    }

    public Book[] getAllBook() {
        Book[] list = target.request().get(Book[].class);
        return list;
    }

    public String getBook(Long id) {
        Book b = target.path("{bookId}").resolveTemplate("bookId", id).request().get(Book.class);
        bookId = b.getId();
        bookTitle = b.getTitle();
        bookAuthor = b.getAuthorId().getName();
        
        return "bookDetails";
    }

    public String editBook() {     
        Book b = target.path("{bookId}").resolveTemplate("bookId", bookId).request().get(Book.class);
        b.setTitle(bookTitle);
        b.getAuthorId().setName(bookAuthor);
        target.path("{bookId}").resolveTemplate("bookId", bookId).request().put(Entity.entity(b, MediaType.APPLICATION_XML));
        return "bookList";
    }

    public void deleteBook(Long id) {
        target.path("{bookId}").resolveTemplate("bookId", id).request().delete();
    }

    public String saveBook() {
        Author a = new Author();
        a.setName(bookAuthor);

        Book b = new Book();
        b.setTitle(bookTitle);
        b.setAuthorId(a);

        target.request().post(Entity.entity(b, MediaType.APPLICATION_XML));
        return "bookList";
    }
}
