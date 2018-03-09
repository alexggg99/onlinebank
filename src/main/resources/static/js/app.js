$( document ).ready(function() {
    var $filter = $('#transactionFilter');
    var str = window.location.href.match(/filter=.*/);
    if(str.length > 0) {
        str = str[0];
        $filter.val(str.substr(7));
    }
    $filter.keypress(function (e) {
        if (e.which == 13) {
            var href = window.location.href;
            href = href.replace(/filter=.*/g, 'filter=');
            href = href.replace(/page=\d/g, 'page=1');
            var val = e.currentTarget.value;
            window.location.href = href + val;
        }
    });
});