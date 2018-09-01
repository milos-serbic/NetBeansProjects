/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bookinfo.bookinfospring.restservice;

import com.bookinfo.bookinfospring.model.Book;
import java.net.URI;
import java.util.List;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Milos
 */
@Path("book")
@Repository
public class BookRESTFacade {

//    @PersistenceContext(unitName = "dataSource")
//    protected EntityManager entityManager;
    
    @Autowired
    @Qualifier("hibernate5AnnotatedSessionFactory")
    private SessionFactory sessionFactory;

    public BookRESTFacade() {
    }

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Transactional
    public Response create(Book entity) {
        //entityManager.persist(entity);
        sessionFactory.getCurrentSession().persist(entity);
        return Response.created(URI.create(entity.getId().toString())).build();
    }

    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Transactional
    public void edit(Book entity) {
        //entityManager.merge(entity);
        sessionFactory.getCurrentSession().update(entity);
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public void remove(@PathParam("id") Long id) {
        //Book entity = entityManager.getReference(Book.class, id);
        //entityManager.remove(entity);
        Book entity = (Book) sessionFactory.getCurrentSession().get(Book.class, id);
        sessionFactory.getCurrentSession().delete(entity);
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Transactional
    public Book find(@PathParam("id") Long id) {
        //return entityManager.find(Book.class, id);
        Book book = (Book) sessionFactory.getCurrentSession().get(Book.class, id);
        return book;
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Transactional
    public List<Book> findAll() {
        return find(true, -1, -1);
//        Session session = this.sessionFactory.getCurrentSession();
//        List<Book> bookList = session.createQuery("from Book").list();
//        return bookList;
    }

    @GET
    @Path("{max}/{first}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Transactional
    public List<Book> findRange(@PathParam("max") Integer max, @PathParam("first") Integer first) {
        return find(false, max, first);
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    @Transactional
    public String count() {
        try {
//            Query query = entityManager.createQuery("SELECT count(o) FROM Book AS o");
//            return query.getSingleResult().toString();

            Session session = this.sessionFactory.getCurrentSession();
            String count = ((Long) session.createQuery("SELECT count(*) from Book").uniqueResult()).toString();
            return count;
        } finally {
            //entityManager.close();
        }
    }

    public List<Book> find(boolean all, int maxResults, int firstResult) {
        try {
            //Query query = entityManager.createQuery("SELECT object(o) FROM Book AS o");
            Session session = this.sessionFactory.getCurrentSession();
            Query query = session.createQuery("SELECT object(o) FROM Book AS o");

            if (!all) {
                query.setMaxResults(maxResults);
                query.setFirstResult(firstResult);
            }
            return query.getResultList();
        } finally {
            //entityManager.close();
        }
    }
}
