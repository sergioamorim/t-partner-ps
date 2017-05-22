var app = angular.module('TP', [ 'ngRoute', 'controllers', 'angularModalService']);
app.run(function ($rootScope) {
    
});
app.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/summary', {
        templateUrl: 'view/summary.html'
    }).otherwise('/summary');
}]);
