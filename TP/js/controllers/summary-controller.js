function summary_controller($scope, $filter, summaryAPI) {
    $scope.requestData = {
        studentId: undefined,
        dateStart: undefined,
        dateEnd: undefined
    };
    $scope.summaryData = [];
    $scope.totalSummaryData = undefined;
    $scope.summaryInterval = undefined;
    $scope.clearData = function() {
        $scope.summaryData = [];
        $scope.totalSummaryData = undefined;
        $scope.summaryInterval = undefined;
    }
    $scope.requestSummary = function(requestData) {
        requestDateFormat = "yyyy-MM-dd";
        dateStart = requestData.dateStart;
        dateEndOriginal = new Date(requestData.dateEnd.valueOf()+86400000);
        if (dateEndOriginal - dateStart >= 1209600000) {
            $scope.summaryInterval = "month";
            while (dateStart.valueOf() + 2592000000 <= dateEndOriginal) {
                dateEnd = dateStart.valueOf() + 2592000000;
                if (dateEnd > dateEndOriginal) {
                    dateEnd = dateEndOriginal;
                }
                summaryAPI.getSummary(requestData.studentId, $filter('date')(dateStart, requestDateFormat), $filter('date')(dateEnd, requestDateFormat)).success(function(data) {
                    $scope.summaryData.push(data);
                });
                dateStart = new Date(dateStart.valueOf()+2592000000);
            }
        }
        else if (dateEndOriginal - dateStart >= 5011200000) {
            $scope.summaryInterval = "week";
            while (dateStart.valueOf() + 604800000 <= dateEndOriginal) {
                dateEnd = dateStart.valueOf() + 604800000;
                if (dateEnd > dateEndOriginal) {
                    dateEnd = dateEndOriginal;
                }
                summaryAPI.getSummary(requestData.studentId, $filter('date')(dateStart, requestDateFormat), $filter('date')(dateEnd, requestDateFormat)).success(function(data){
                    $scope.summaryData.push(data);
                });
                dateStart = new Date(dateStart.valueOf()+604800000);
            }
        }
        else if (dateEndOriginal - dateStart > 172800000) {
            $scope.summaryInterval = "day";
            while (dateStart.valueOf() + 86400000 <= dateEndOriginal) {
                dateEnd = dateStart.valueOf() + 86400000;
                if (dateEnd > dateEndOriginal) {
                    dateEnd = dateEndOriginal;
                }
                summaryAPI.getSummary(requestData.studentId, $filter('date')(dateStart, requestDateFormat), $filter('date')(dateEnd, requestDateFormat)).success(function(data) {
                    $scope.summaryData.push(data);
                });
                console.log(dateStart);
                dateStart = new Date(dateStart.valueOf()+86400000);
                console.log(dateStart);
            }
        }
        summaryAPI.getSummary(requestData.studentId, $filter('date')(requestData.dateStart, requestDateFormat), $filter('date')(dateEndOriginal, requestDateFormat)).success(function(data){
            $scope.totalSummaryData = data;
        });
    };
}
