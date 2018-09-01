myApp.controller(
        'BookDetailsController',
        [
            '$scope',
            '$filter',
            '$location',
            '$rootScope',
            'BookService',
            function ($scope, $filter, $location, $rootScope, BookService)
            {
                
                $scope.updateBook = function ()
                {
                    BookService.saveOrUpdateBook(
                            $rootScope.selectedBook,
                            function (response)
                            {                                
                                $location.path("/bookList");
                                alert('Updated!');
                            },
                            function (response)
                            {
                                alert('Error!');
                            });
                };
                
            }]);