var app = angular.module('TP', [ 'ngRoute', 'controllers']);
app.run(function ($rootScope) {
    
});
app.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/summary', {
        templateUrl: 'view/summary.html'
    }).otherwise('/summary');
}]);