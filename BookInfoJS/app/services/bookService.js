myApp.factory('BookService', [
    '$resource',
    function ($resource)
    {
        var restUrl = 'http://localhost:8080/BookInfoJEE-web/bookinfo';
        return $resource('', {}, {
            getBookList: {
                method: "GET", params: {}, url: restUrl + '/book', isArray: true
            },
            getBookById: {
                method: "GET",
                params: {
                    id: "@id"
                }, url: restUrl + '/book/:id',
                isArray: false
            },
            saveOrUpdateBook: {
                method: "POST", url: restUrl + "/book", isArray: false
            },
            deleteBookById: {
                method: "DELETE",
                params: {
                    id: "@id"
                }, url: restUrl + '/book/:id',
                isArray: false
            }            
        });
    }]);
