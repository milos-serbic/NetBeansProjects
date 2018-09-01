/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bookinfo;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Milos
 */
@Local
public interface BookInfoBeanLocal {
    
    public void addOrEditBook(Book book);

    public void addOrEditAuthor(Author author);

    public void removeBook(Book book);

    public void removeAuthor(Author author);

    public List<Book> getAllBook();

    public List<Author> getAllAuthor();
    
    public Book findBook(Long id);
    
    public Author findAuthor(Long id);
}
