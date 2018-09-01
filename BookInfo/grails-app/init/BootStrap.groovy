import bookinfo.Book
import bookinfo.Author
class BootStrap {

    def init = { 
        servletContext ->
        Author a1 = new Author(name: "Stephen King").save(flush: true)
        new Book(author: a1, title:"The Shining").save(flush: true)
        Author a2 = new Author(name: "James Patterson").save(flush: true)
        new Book(author: a2 ,title:"Along Came a Spider").save(flush: true)
    }
    def destroy = {
    }
}
