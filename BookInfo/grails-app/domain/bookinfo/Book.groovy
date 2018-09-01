package bookinfo

class Book {

    static constraints = {
    }
    static belongsTo = [author: Author]
    
    String title 
    Author author
}
