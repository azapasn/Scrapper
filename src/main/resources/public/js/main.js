var app = angular.module('app', []);

app.controller('mainCtrl', function ($http, $scope) {
    $scope.checkPhone = function (number) {
        var url = "http://localhost:8080/get/phone/?number=+370" + number;
        $http.get(url)
            .then(function (result) {
                    $scope.advertisements = JSON.parse(JSON.stringify(result["data"]));
                    console.log('success', JSON.parse(JSON.stringify(result["data"])));
                },
                function (result) {
                    console.log('error');
                });
        console.log(url);
    }
    $scope.checkVin = function (vin) {
        var url = "http://localhost:8080/get/vin/?vin=" + vin;
        $http.get(url)
            .then(function (result) {
                    $scope.advertisements = JSON.parse(JSON.stringify(result["data"]));
                    console.log('success', JSON.parse(JSON.stringify(result["data"])));
                },
                function (result) {
                    console.log('error');
                });
        console.log(url);
    }
})
