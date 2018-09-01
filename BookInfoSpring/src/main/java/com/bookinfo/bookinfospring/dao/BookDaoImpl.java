/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bookinfo.bookinfospring.dao;

import com.bookinfo.bookinfospring.model.Book;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Milos
 */
@Repository
public class BookDaoImpl implements BookDaoInterface {

    @Autowired
    @Qualifier("hibernate5AnnotatedSessionFactory")
    private SessionFactory sessionFactory;

    @Override
    public Book createBook(Book book) {
        sessionFactory.getCurrentSession().persist(book);
        return book;
    }

    @Override
    public void editBook(Book book) {
        sessionFactory.getCurrentSession().update(book);
    }

    @Override
    public void removeBook(Long id) {
        Book entity = (Book) sessionFactory.getCurrentSession().get(Book.class, id);
        sessionFactory.getCurrentSession().delete(entity);
    }

    @Override
    public Book findBook(Long id) {
        Book book = (Book) sessionFactory.getCurrentSession().get(Book.class, id);
        return book;
    }

    @Override
    public List<Book> findAllBooks() {
        return find(true, -1, -1);
    }

    @Override
    public List<Book> findRangeBooks(Integer max, Integer first) {
        return find(false, max, first);
    }

    @Override
    public String countBook() {
        Session session = this.sessionFactory.getCurrentSession();
        String count = ((Long) session.createQuery("SELECT count(*) from Book").uniqueResult()).toString();
        return count;
    }

    public List<Book> find(boolean all, int maxResults, int firstResult) {
        try {

            Session session = this.sessionFactory.getCurrentSession();
            Query query = session.createQuery("SELECT object(o) FROM Book AS o");

            if (!all) {
                query.setMaxResults(maxResults);
                query.setFirstResult(firstResult);
            }
            return query.getResultList();
        } finally {
        }
    }
}
