angular.module('TP').controller('LearningGoalsModal', function($scope, learningGoals, close) {

    $scope.close = function() {
      close(true, 500);
    };

});
