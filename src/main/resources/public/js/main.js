var app = angular.module('app', []);

app.controller('mainCtrl', function ($http, $scope) {
    $scope.checkPhone = function (number) {
        $scope.advertisements = null;
        var url = "http://localhost:8080/get/phone/?number=" + number;
        $http.get(url)
            .then(function (result) {
                    $scope.advertisements = JSON.parse(JSON.stringify(result["data"]));
                    $scope.isData = false;
                },
                function () {
                    $scope.isData = true;
                });
    }
    $scope.checkVin = function (vin) {
        $scope.advertisements = null;
        var url = "http://localhost:8080/get/vin/?vin=" + vin;
        $http.get(url)
            .then(function (result) {
                    $scope.advertisements = JSON.parse(JSON.stringify(result["data"]));
                    $scope.isData = false;
                },
                function () {
                    $scope.isData = true;
                });
    }
    $scope.checkLicencePlate = function (plate) {
        $scope.advertisements = null;
        var url = "http://localhost:8080/get/plate/?plate=" + plate;
        $http.get(url)
            .then(function (result) {
                    $scope.advertisements = JSON.parse(JSON.stringify(result["data"]));
                    $scope.isData = false;
                },
                function () {
                    $scope.isData = true;
                });
    }
})
