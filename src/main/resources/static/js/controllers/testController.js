/**
 * Created by bharatbatra on 3/1/16.
 */
testApp.controller("testController", function($scope, $log, $http)
{

    $http.get("/alert/getAlert").success(function(data){
        $log.info(JSON.stringify(data));
    });

});