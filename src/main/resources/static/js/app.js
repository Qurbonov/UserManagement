/**
 * Created by qurbonov on 10/14/2015.
 */
'use strict';
var app = angular.module('app', ['ngRoute', 'file-model', 'ngCookies', 'ngResource', 'ngFileUpload']);

app.config(['$routeProvider',
    function ($routeProvider) {
        $routeProvider
            //.when('/', {
            //    templateUrl: '/pages/home.html'
            //})
            .when('/', {
                controller: 'HomeController',
                templateUrl: 'home/home.view.html',
                controllerAs: 'vm'
            })
            .when('/login', {
                controller: 'LoginController',
                templateUrl: 'login/login.view.html',
                controllerAs: 'vm'

            }).when('/register', {
                controller: 'RegisterController',
                templateUrl: 'register/register.view.html',
                controllerAs: 'vm'
            })
            .when('/users', {
                templateUrl: 'pages/users.html'
            })
            .when('/depts', {
                templateUrl: 'pages/departments.html'
            })
            .when('/uploadeds', {
                templateUrl: 'pages/uploadeds.html'
            })
            .when('/about', {
                templateUrl: 'pages/about.html'
            }).when('/about', {
                templateUrl: 'pages/about.html'
            }).when('/test', {
                templateUrl: 'test.html'
            })
            .otherwise({redirectTo: '/'});
    }])
;


app.run(function ($rootScope, $location, $cookieStore, $http) {
    // keep user logged in after page refresh
    $rootScope.globals = $cookieStore.get('globals') || {};
    if ($rootScope.globals.currentUser) {
        $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.globals.currentUser.authdata; // jshint ignore:line
    }

    $rootScope.$on('$locationChangeStart', function (event, next, current) {
        // redirect to login page if not logged in and trying to access a restricted page
        var restrictedPage = $.inArray($location.path(), ['/login', '/register']) === -1;
        var loggedIn = $rootScope.globals.currentUser;
        if (restrictedPage && !loggedIn) {
            $location.path('/login');
        }
    });
});

app.controller('userCtrl', function ($scope, $http) {
    $http.get("http://localhost:81/api/dvs/")
        .success(function (response) {
            $scope.data = response;
        })
        .error(function () {
            console.log("rest is offline");
        });
});

app.controller('departmentCtrl', function ($scope, $http) {
    $http.get("http://localhost:81/api/dept/")
        .success(function (response) {
            $scope.dept = response;
        })
        .error(function () {
            console.log("rest is offline");
        });
});

app.controller('fileCtrl', function ($scope, $http, $resource) {
    $scope.file = null;
    $scope.$watch('file', function (newVal) {
        if (newVal) {
            var reader = new FileReader();
            reader.onload = function (evt) {
                var content = evt.target.result;
                var fileName = newVal.name
                var res = $http.post('/user/new1', {content: content, fileName: fileName});
                res.success(function (content, status) {
                    $scope.message = content;
                });
                res.error(function (content, status) {
                });
            };
            reader.readAsBinaryString(newVal);
        }
    })
});
