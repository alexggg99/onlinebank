$( document ).ready(function() {
    var $accountFrom = $('#accountFrom');
    var $accountTo = $('#accountTo');
    console.log('sout');
    $accountFrom.change(function() {
        toogleWarning();
    });
    $accountTo.change(function() {
        toogleWarning();
    })
    function toogleWarning() {
        var curr1 = $accountFrom.val().substr(1, 3)
        var curr2 = $accountTo.val().substr(1, 3)
        if (curr1 !== curr2) {
            $('#exchange').show();
        } else {
            $('#exchange').hide();
        }
    }
});

