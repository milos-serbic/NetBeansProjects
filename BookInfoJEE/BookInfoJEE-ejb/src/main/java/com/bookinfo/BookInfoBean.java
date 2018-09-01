/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bookinfo;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Milos
 */
@Named
@Stateless
public class BookInfoBean implements BookInfoBeanLocal {

    @PersistenceContext(unitName = "bookInfo")
    private EntityManager em;

    @Override
    public void addOrEditBook(Book book) {
        em.merge(book);
    }

    @Override
    public void addOrEditAuthor(Author author) {
        em.merge(author);
    }

    @Override
    public void removeBook(Book book) {
        em.remove(book);
    }

    @Override
    public void removeAuthor(Author author) {
        em.remove(author);
    }

    @Override
    public List<Book> getAllBook() {
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Book.class));
        return em.createQuery(cq).getResultList();
    }

    @Override
    public List<Author> getAllAuthor() {
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Author.class));
        return em.createQuery(cq).getResultList();
    }

    public Book findBook(Long id) {
        return em.find(Book.class, id);
    }

    public Author findAuthor(Long id) {
        return em.find(Author.class, id);
    }

}
