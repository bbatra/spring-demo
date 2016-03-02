/**
 * Created by bharatbatra on 3/1/16.
 */
var testApp = angular.module('testApp', ['ngResource','ngCookies', 'ngRoute', 'ngSanitize']);

//testController code
//testApp.controller("testController", function($scope, $log, $http)
//{
//
//    $http.get("/alert/hit").success(function(data){
//        $log.info(data);
//    });
//
//});

testApp.factory("NotificationService", function( $rootScope, $http, $log)
{
    var self = this;

    self.sendNotification = function( notification )
    {
        $http.post("/alert/notification", notification).success( function( data )
        {
            $rootScope.$broadcast( "notificationSent", data );
        })
        .error( function( data )
        {
            $log.warn("Problem calling url /alert/notification with object : " + JSON.stringify( notification ) );

        });
    };

    return self;
});


testApp.controller("TestController", function( $scope, $http, $log, NotificationService )
{
    $scope.message = "Hello angular";

    $scope.buttonPush = function()
    {
        $http.get("/alert/getAlert").success(function(data)
        {
            $log.info(JSON.stringify(data));
        });
    };

    $scope.alertTrigger = function()
    {

        var obj = { title: 'confused about subject right now'};
        NotificationService.sendNotification( obj );

    };

    $scope.$on('notificationSent', function( message, data )//listen for message 'notificationSent'
    {
        $log.info("Received in message handler : " + JSON.stringify( data ) );
    });

});