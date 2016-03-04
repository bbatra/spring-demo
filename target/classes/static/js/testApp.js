/**
 * Created by bharatbatra on 3/1/16.
 */
var testApp = angular.module('testApp', ['ngResource','ngCookies', 'ngRoute', 'ngSanitize']);


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

    self.getAlert = function (notification)
    {
        $http.post("/alert/getAlert/3ebda201-503c-4009-943b-b9e17ee9bd54", notification).success(function(data)
        {
            $rootScope.$broadcast("gotSomeAlert", data);
        })
        .error(function(data)
        {
            $log.warn("Problem calling url /alert/getAlert/3ebda201-503c-4009-943b-b9e17ee9bd54 with object : "
                + JSON.stringify(notification));
        });


    }

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
        var message;
        if($scope.notification=="")
            message = 'confused about subject right now';
        else
            message = $scope.notification;
        var obj = { title: message};
        NotificationService.sendNotification( obj );
        NotificationService.getAlert(obj)
    };

    $scope.$on('notificationSent', function( message, data )//listen for message 'notificationSent'
    {
        $log.info("Received in message handler : " + JSON.stringify( data ) );
    });

    $scope.$on('gotSomeAlert', function(message, data)
    {
       $log.info("Received this alert : " + JSON.stringify(data)) ;
    });

});