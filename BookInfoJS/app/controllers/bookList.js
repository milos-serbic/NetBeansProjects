myApp.controller(
        'BookListController',
        [
            '$scope',
            '$filter',
            '$location',
            '$rootScope',
            'BookService',
            function ($scope, $filter, $location, $rootScope, BookService)
            {
                $scope.getList = function () {
                    BookService.getBookList(function (response)
                    {
                        $scope.bookList = response;
                    }, function (response)
                    {
                        alert('Error!');
                    });
                };
                $scope.getList();

                $scope.editBook = function (book)
                {
                    BookService.getBookById(
                            {id: book.id},
                            function (response)
                            {
                                $rootScope.selectedBook = response;
                                $location.path("/bookDetails");
                            },
                            function (response)
                            {
                                alert('Error!');
                            });
                };

                $scope.deleteBook = function (book)
                {
                    BookService.deleteBookById(
                            {id: book.id},
                            function (response)
                            {
                                $scope.getList();
                                alert('Deleted!');
                            },
                            function (response)
                            {
                                alert('Error!');
                            });
                };

                $scope.newBook = {};
                $scope.newBook.authorId = {};
                $scope.saveBook = function ()
                {
                    BookService.saveOrUpdateBook(
                            $scope.newBook,
                            function (response)
                            {
                                $scope.getList();
                                alert('Saved!');
                            },
                            function (response)
                            {
                                alert('Error!');
                            });
                };

            }]);