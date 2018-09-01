/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bookinfo.bookinfospring.service;

import com.bookinfo.bookinfospring.dao.BookDaoInterface;
import com.bookinfo.bookinfospring.model.Book;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Milos
 */
@Service
public class BookServiceImpl implements BookServiceInterface {

    @Autowired
    BookDaoInterface bookDao;

    @Override
    @Transactional
    public Book createBook(Book book) {
        return bookDao.createBook(book);
    }

    @Override
    @Transactional
    public void editBook(Book book) {
        bookDao.editBook(book);
    }

    @Override
    @Transactional
    public void removeBook(Long id) {
        bookDao.removeBook(id);
    }

    @Override
    @Transactional
    public Book findBook(Long id) {
        return bookDao.findBook(id);
    }

    @Override
    @Transactional
    public List<Book> findAllBooks() {
        return bookDao.findAllBooks();
    }

    @Override
    @Transactional
    public List<Book> findRangeBooks(Integer max, Integer first) {
        return bookDao.findRangeBooks(max, first);
    }

    @Override
    @Transactional
    public String countBook() {
        return bookDao.countBook();
    }
}
