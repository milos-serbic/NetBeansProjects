package bookinfo

class BookController {
    static defaultAction = "list"
    
    def list() {         
        [bookList: Book.getAll()]
    }
    
    def show() { 
        def b = Book.get(params.id)
        render(view: "show", model: [book: b])
    }
    
    def update() {         
        def b = Book.get(params.id)  
        def a = Author.get(params.authorId)
        //b.properties = params
        a.name = params.author
        a.save(flush: true)
        b.title = params.title
        b.save(flush: true)
        redirect(action: 'list')
    }
    
    def save() {         
        def author = new Author(name: params.author).save(flush: true)
        def b = new Book(title: params.title, author: author)
        b.save(flush: true)
        redirect(action: 'list')
    }
    
    def delete() { 
        def b = Book.get(params.id)
        if (!b) {
            flash.message = "User not found for id ${params.id}"
            redirect(action: 'list')
        }
        else
        {
            b.delete(flush: true)
            redirect(action: 'list')
        }
    }
}
