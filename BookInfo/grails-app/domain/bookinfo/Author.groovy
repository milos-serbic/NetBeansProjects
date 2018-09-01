package bookinfo

class Author {

    static constraints = {
    }
    static hasMany = [books: Book]
    
    String name
    List<Book> books
}
