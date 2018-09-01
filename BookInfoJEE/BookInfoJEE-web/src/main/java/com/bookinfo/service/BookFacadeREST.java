 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bookinfo.service;

import com.bookinfo.Book;
import com.bookinfo.BookInfoBeanLocal;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
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

/**
 *
 * @author Milos
 */
@Stateless
@Path("book")
public class BookFacadeREST extends AbstractFacade<Book> {
      
    @Inject
    BookInfoBeanLocal bookInfoBeanLocal;
    
    @PersistenceContext(unitName = "bookInfoWeb")
    private EntityManager em;

    public BookFacadeREST() {
        super(Book.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Book entity) {
//        super.create(entity);
        bookInfoBeanLocal.addOrEditBook(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Long id, Book entity) {
//        super.edit(entity);
        bookInfoBeanLocal.addOrEditBook(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
//        super.remove(super.find(id));
        bookInfoBeanLocal.removeBook(bookInfoBeanLocal.findBook(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Book find(@PathParam("id") Long id) {
//        return super.find(id);
                
        return bookInfoBeanLocal.findBook(id);
    }
    
//    @GET    
//    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
//    public Response findAllT() {//       
//        return Response.ok("", MediaType.APPLICATION_JSON).build();//
//        return bookInfoBeanLocal.getAllBook();
//    }
    
    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Book> findAll() {
//        return super.findAll();     

        List<Book> t = bookInfoBeanLocal.getAllBook();
        return t;
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Book> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
