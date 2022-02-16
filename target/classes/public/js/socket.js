


var socket = (function (){
    return{
        wSocket : function (url){
            var promise = $.get({
                url: url,
                contentType: "application/json"
            });
            promise.then(function(data){
                $('#imagen').html(data);
            }, function(error) {

            });
        }
    }
})();