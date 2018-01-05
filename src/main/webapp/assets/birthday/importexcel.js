$(document).ready(function() {
    ajaxFormInitial($("#subfileform"),function (data,status) {
        layer.alert(data);
    });
})