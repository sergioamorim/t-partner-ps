angular.module('TP').filter('milliSecondsToTimeString', function() {
  return function(milliseconds) {
    var oneSecond = 1000;
    var oneMinute = oneSecond * 60;
    var oneHour = oneMinute * 60;
    var oneDay = oneHour * 24;
    if (isNaN(milliseconds)) {
        milliseconds = 0;
    }
    var seconds = Math.floor((milliseconds % oneMinute) / oneSecond);
    var minutes = Math.floor((milliseconds % oneHour) / oneMinute);
    var hours = Math.floor((milliseconds % oneDay) / oneHour);
    var days = Math.floor(milliseconds / oneDay);

    var timeString = '';
    if (days !== 0) {
        timeString += (days !== 1) ? (days + ' days ') : (days + ' day ');
    }
    if (hours !== 0) {
        timeString += (hours !== 1) ? (hours + ' hours ') : (hours + ' hour ');
    }
    if (minutes !== 0) {
        timeString += (minutes !== 1) ? (minutes + ' minutes ') : (minutes + ' minute ');
    }
    if (seconds !== 0 || milliseconds < 1000) {
        timeString += (seconds !== 1) ? (seconds + ' seconds ') : (seconds + ' second ');
    }

    return timeString;
  };
});
