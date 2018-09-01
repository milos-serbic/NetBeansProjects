/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bookinfo.bookinfospring.restservice;

import javax.persistence.EntityManager;
import com.bookinfo.bookinfospring.model.Author;
import java.net.URI;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.PersistenceContext;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Milos
 */
@Path("author")
@Repository
public class AuthorRESTFacade {

//    @PersistenceContext(unitName = "dataSource")
//    protected EntityManager entityManager;
    @Autowired
    @Qualifier("hibernate5AnnotatedSessionFactory")
    private SessionFactory sessionFactory;

    public AuthorRESTFacade() {
    }

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Transactional
    public Response create(Author entity) {
        //entityManager.persist(entity);
        sessionFactory.getCurrentSession().persist(entity);
        return Response.created(URI.create(entity.getId().toString())).build();
    }

    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Transactional
    public void edit(Author entity) {
        //entityManager.merge(entity);
        sessionFactory.getCurrentSession().update(entity);
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public void remove(@PathParam("id") Long id) {
//        Author entity = entityManager.getReference(Author.class, id);
//        entityManager.remove(entity);

        Author entity = (Author) sessionFactory.getCurrentSession().get(Author.class, id);
        sessionFactory.getCurrentSession().delete(entity);
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Transactional
    public Author find(@PathParam("id") Long id) {
        //return entityManager.find(Author.class, id);
        Author author = (Author) sessionFactory.getCurrentSession().get(Author.class, id);
        return author;
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Transactional
    public List<Author> findAll() {
        return find(true, -1, -1);

//        Session session = this.sessionFactory.getCurrentSession();
//        List<Author> authorList = session.createQuery("from Author").list();
//        return authorList;
    }

    @GET
    @Path("{max}/{first}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Transactional
    public List<Author> findRange(@PathParam("max") Integer max, @PathParam("first") Integer first) {
        return find(false, max, first);
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    @Transactional
    public String count() {
        try {
//            Query query = entityManager.createQuery("SELECT count(o) FROM Author AS o");
//            return query.getSingleResult().toString();

            Session session = this.sessionFactory.getCurrentSession();
            String count = ((Long) session.createQuery("SELECT count(*) from Author").uniqueResult()).toString();
            return count;
        } finally {
//            entityManager.close();
        }
    }

    private List<Author> find(boolean all, int maxResults, int firstResult) {
        try {
//            Query query = entityManager.createQuery("SELECT object(o) FROM Author AS o");
            Session session = this.sessionFactory.getCurrentSession();
            Query query = session.createQuery("SELECT object(o) FROM Author AS o");
            if (!all) {
                query.setMaxResults(maxResults);
                query.setFirstResult(firstResult);
            }
            return query.getResultList();
        } finally {
//            entityManager.close();
        }
    }
}
