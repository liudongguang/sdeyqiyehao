$(document).ready(function() {
    $('#monthly').monthpicker({
        years: [2016,2015, 2014, 2013, 2012, 2011],
        topOffset: 6,
        onMonthSelect: function(m, y) {
            console.log('Month: ' + m + ', year: ' + y);
        }
    })
})