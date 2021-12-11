angular.module('app',[]).controller('mainController', function ($scope, $http) {
    const contextPath = 'http://localhost:2021';

    fillTable = function(){
         $http.get(contextPath + '/get/')
                 .then(function(response) {
                     $scope.students = response.data;
                 });
    }

    fillTable();

    $scope.add = function (){
        var new_name = document.getElementById("new_name").value;
        var new_mark = document.getElementById("new_mark").value;
        $http({
             url: contextPath + '/add/',
             method: 'POST',
             data: {
                 name: new_name,
                 mark: new_mark
             }
        }).then(function (response) {
                document.getElementById("new_name").value = "";
                document.getElementById("new_mark").value = "";
                fillTable();
            });
    };

    $scope.update = function (id, name){
            var new_mark = document.getElementById(id).value;
            $http({
                 url: contextPath + '/update/',
                 method: 'PUT',
                 data: {
                     id: id,
                     name: name,
                     mark: new_mark
                 }
            }).then(function (response) {
                    fillTable();
                });
        };

    $scope.deleteStudent = function (id){
            $http({
                 url: contextPath + '/delete/' + id,
                 method: 'DELETE',
            }).then(function (response) {
                    fillTable();
                });
        };
    });