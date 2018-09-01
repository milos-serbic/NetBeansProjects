'use strict';

var myApp = angular.module(
        'myApp',
        ['ngRoute', 'ngResource']).config(['$routeProvider',
    function ($routeProvider)
    {
        $routeProvider.when('/', {
            templateUrl: 'views/bookList.html', controller: 'BookListController'
        }).when('/bookList', {
            templateUrl: 'views/bookList.html', controller: 'BookListController'
        }).when('/bookDetails', {
            templateUrl: 'views/bookDetails.html', controller: 'BookDetailsController'
        }).otherwise({
            redirectTo: '/'
        });
    }]);
