angular.module("TP").factory("summaryAPI", function($http, config){
	
	var _getSummary = function(student_id, time_start, time_end){
		return $http.get(config.baseUrl+"/student/summary?student_id="+student_id+"&time_start="+time_start+"&time_end="+time_end);
	}
	
	return {
		getSummary: _getSummary
	}
});
