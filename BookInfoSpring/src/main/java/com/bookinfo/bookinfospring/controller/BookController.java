package com.bookinfo.bookinfospring.controller;

import com.bookinfo.bookinfospring.model.Book;
import com.bookinfo.bookinfospring.restservice.BookRESTFacade;
import com.bookinfo.bookinfospring.service.BookServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

/**
 * Sample controller for going to the home page with a message
 */
@Controller
public class BookController {

    private static final Logger logger = LoggerFactory
            .getLogger(BookController.class);

    @Autowired
    BookRESTFacade bookRESTFacade;

    @Autowired
    BookServiceInterface bookService;

    /**
     * Selects the home page and populates the model with a message
     *
     * @param model
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView bookList(ModelAndView model) {

        logger.info("Welcome home!");
        model.addObject("bookList", bookService.findAllBooks());
        model.addObject("book", new Book());
        return model;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable("id") Long id, ModelAndView model, HttpServletRequest request) {

        Book book = bookService.findBook(id);
        model.addObject("book", book);
        model.setViewName("/bookDetails");
        return model;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Long id, Model model, HttpServletRequest request) {

        bookService.removeBook(id);
        return "redirect:/book";
    }

    @RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
    public String saveOrUpdate(@ModelAttribute("book") Book book, Model model, HttpServletRequest request) {

        if (book.getId() != null) {
            bookService.editBook(book);
        } else {
            bookService.createBook(book);
        }
        return "redirect:/book";
    }
}
