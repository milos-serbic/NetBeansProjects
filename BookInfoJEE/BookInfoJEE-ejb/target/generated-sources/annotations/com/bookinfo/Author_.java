package com.bookinfo;

import com.bookinfo.Book;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-07-09T09:22:56")
@StaticMetamodel(Author.class)
public class Author_ { 

    public static volatile SingularAttribute<Author, String> name;
    public static volatile SingularAttribute<Author, Long> id;
    public static volatile SingularAttribute<Author, Long> version;
    public static volatile ListAttribute<Author, Book> bookList;

}