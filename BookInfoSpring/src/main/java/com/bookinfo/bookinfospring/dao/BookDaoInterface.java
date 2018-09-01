/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bookinfo.bookinfospring.dao;

import com.bookinfo.bookinfospring.model.Book;
import java.util.List;

/**
 *
 * @author Milos
 */

public interface BookDaoInterface {
    
    public Book createBook(Book book);
    public void editBook(Book book);
    public void removeBook(Long id);
    public Book findBook(Long id);
    public List<Book> findAllBooks();
    public List<Book> findRangeBooks(Integer max, Integer first);
    public String countBook(); 
}
